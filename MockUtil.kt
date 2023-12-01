import service.AddressService
import kotlin.random.Random

class MockUtil(val addressService: AddressService) {
    fun addMockData() {
        val firstNames = listOf("John", "Jane", "Alice", "Bob", "Charlie")
        val lastNames = listOf("Doe", "Johnson", "Smith", "Brown", "Taylor")
        val addresses = listOf("Kolkata WestBengal", "Delhi India", "Gurugram Haryana", "Bangalore Karnataka", "Hyderabad Telangana")

        firstNames.map{firstName->
            val lastName = lastNames.random()
            val address = addresses.random()
            val phoneNumber = generateRandomPhoneNumber()
            val addressDTO = AddressDTO(firstName, lastName,address,phoneNumber)
            addressService.addAddress(addressDTO)
        }


    }

    fun generateRandomPhoneNumber(): String {
        val random = Random(System.currentTimeMillis())
        return (1..10).joinToString("") { random.nextInt(0, 10).toString() }
    }

}