import com.sun.net.httpserver.Authenticator.Success
import java.util.*

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
        for (char in fullName.lowercase(Locale.getDefault())) {
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

    override fun searchAddressByName(name: String): List<AddressDTO> {
        TODO("Not yet implemented")
    }

    override fun searchAddressByPhone(name: String): List<AddressDTO> {
        TODO("Not yet implemented")
    }

}

data  class TrieNode(
    val children:MutableMap<Char, TrieNode>,
    var addressDTO: AddressDTO?
)

