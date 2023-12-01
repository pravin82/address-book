package repository

import AddressDTO

interface AddressRepository {
    fun addAddress(addressDTO: AddressDTO):String
    fun searchAddressByName( name:String):List<AddressDTO>
    fun searchAddressByPhone(phone:String):List<AddressDTO>
}