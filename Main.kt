import java.util.*

fun main(){
    val scanner = Scanner(System.`in`)
    val trieAddressRepository = TrieAddressRepository()
    val addressService = AddressService(trieAddressRepository)

    while (true) {
         printOptionMessage()
        when (scanner.nextInt()) {
            1 -> {
                handleAddAddress(scanner,addressService)
            }
            2 -> {
                print("Enter Name Prefix: ")
                val namePrefix = scanner.next()
                println("namePrefix:${namePrefix}")
            }
            3 -> {
                print("Enter Number Prefix: ")
                val numberPrefix = scanner.next()
                println("numberPrefix:${numberPrefix}")
            }
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
    println("2. Search by Name Prefix")
    println("3. Search by Number Prefix")
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