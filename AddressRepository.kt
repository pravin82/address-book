interface AddressRepository {
    fun addAddress():String
    fun searchAddressByName( name:String):List<AddressDTO>
    fun searchAddressByPhone(name:String):List<AddressDTO>
}