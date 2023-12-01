interface AddressRepository {
    fun addAddress(addressDTO: AddressDTO):String
    fun searchAddressByName( name:String):List<AddressDTO>
    fun searchAddressByPhone(name:String):List<AddressDTO>
}