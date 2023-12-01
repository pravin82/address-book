import repository.TrieAddressRepository
import service.AddressService
import java.util.*

fun main(){
    val scanner = Scanner(System.`in`)
    val trieAddressRepository = TrieAddressRepository()
    val addressService = AddressService(trieAddressRepository)
    val mockUtil = MockUtil(addressService)
    mockUtil.addMockData()
    println("Mock data added")

    while (true) {
         printOptionMessage()
        when (try {
            scanner.nextInt()
        } catch (e: InputMismatchException) {
            println("Invalid input. Please enter a valid integer.")
            scanner.nextLine()

        }) {
            1 -> handleAddAddress(scanner,addressService)
            2 -> handleSearchByName(scanner,addressService)
            3 -> handleSearchByPhone(scanner,addressService)
            4 -> {
                println("Exiting...")
                return
            }
            else -> println("Invalid option. Please choose a valid option.")
        }
    }
}


private fun printOptionMessage(){
    println("\n1. Add Address Detail")
    println("2. Search by Name")
    println("3. Search by Phone")
    println("4. Exit")
    print("Choose an option: ")
}

private fun handleAddAddress(scanner:Scanner,addressService:AddressService){
    print("Enter First Name: ")
    val firstName = scanner.next()

    print("Enter Last Name: ")
    val lastName = scanner.next()
    scanner.nextLine()
    print("Enter Address: ")
    val address = scanner.nextLine()

    print("Enter Phone Number: ")
    val phoneNumber = scanner.next()
    val addressDTO = AddressDTO(
        firstName,
        lastName,
        address,
        phoneNumber
    )
    addressService.addAddress(addressDTO)

    println("Detail added successfully!. Detail:${addressDTO}")

}

private fun handleSearchByName(scanner: Scanner,addressService: AddressService){
    print("Enter Name to search: ")
    val namePrefix = scanner.next()
    val addressList = addressService.searchAddressByName(namePrefix)
    println("Search Results:${addressList.map{it.toString()}}")

}

private fun handleSearchByPhone(scanner: Scanner,addressService: AddressService){
    print("Enter Phone to search: ")
    val phone = scanner.next()
    val addressList = addressService.searchAddressByPhone(phone)
    println("Search Results:${addressList.map{it.toString()}}")

}