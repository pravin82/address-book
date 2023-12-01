import com.sun.net.httpserver.Authenticator.Success
import java.util.*

data  class TrieNode(
    val children:MutableMap<Char, TrieNode>,
    var addressDTO: AddressDTO?
)


class TrieAddressRepository:AddressRepository {
    private val nameTrieRoot = TrieNode(emptyMap<Char,TrieNode>().toMutableMap(),null)
    private val phoneTrieRoot = TrieNode(emptyMap<Char,TrieNode>().toMutableMap(),null)
    override fun addAddress(addressDTO: AddressDTO): String {
        addAddressInNameTrie(addressDTO)
        addAddressInPhoneTrie(addressDTO)

        return "SUCCESS"

    }

    private fun addAddressInNameTrie(addressDTO: AddressDTO){
        var node = nameTrieRoot
        val fullName = "${addressDTO.firstName} ${addressDTO.lastName}"
        for (char in fullName.lowercase()) {
            node = node.children.getOrPut(char) { TrieNode(emptyMap<Char,TrieNode>().toMutableMap(),null) }
        }
        node.addressDTO = addressDTO
    }

    private fun addAddressInPhoneTrie(addressDTO: AddressDTO){
        var node = phoneTrieRoot
        for (char in addressDTO.phoneNo) {
            node = node.children.getOrPut(char) { TrieNode(emptyMap<Char,TrieNode>().toMutableMap(),null) }
        }
        node.addressDTO = addressDTO

    }

    override fun searchAddressByName(namePrefix: String): List<AddressDTO> {
        var node = nameTrieRoot
        for (char in namePrefix.lowercase()) {
            node = node.children.get(char) ?: return emptyList()
        }
        return collectAddressList(node)
    }

    private fun collectAddressList(node:TrieNode):List<AddressDTO>{
        val results = mutableListOf<AddressDTO>()
        if(node.addressDTO != null) results.add(node.addressDTO!!)
        node.children.map{
            results.addAll(collectAddressList(it.value))
        }
        return results
    }

    override fun searchAddressByPhone(phone: String): List<AddressDTO> {
        var node = phoneTrieRoot
        for (char in phone.lowercase()){
            node = node.children.get(char) ?: return emptyList()
        }
       return collectAddressList(node)
    }


}


