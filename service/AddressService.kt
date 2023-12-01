package service

import AddressDTO
import repository.AddressRepository

class AddressService(private val addressRepository: AddressRepository) {
    fun addAddress(addressDTO: AddressDTO):String{
      return  addressRepository.addAddress(addressDTO)
    }
    fun searchAddressByName(namePrefix:String):List<AddressDTO>{
        return addressRepository.searchAddressByName(namePrefix)
    }

    fun searchAddressByPhone(phonePrefix: String):List<AddressDTO>{
        return addressRepository.searchAddressByPhone(phonePrefix)
    }

}