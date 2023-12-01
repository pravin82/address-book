class AddressService(private val addressRepository: AddressRepository) {
    fun addAddress(addressDTO: AddressDTO):String{
      return  addressRepository.addAddress(addressDTO)
    }
    fun searchAddressByName(namePrefix:String):List<AddressDTO>{
        return addressRepository.searchAddressByName(namePrefix)
    }
}