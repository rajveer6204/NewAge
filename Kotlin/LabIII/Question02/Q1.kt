fun greet(name: String = "Student", age: Int = 20) {
    println("Name: $name, Age: $age")
}

fun displayInfo(name: String, age: Int, city: String) {
    println("Name: $name, Age: $age, City: $city")
}

fun printNumbers(vararg numbers: Int) {
    for (num in numbers) {
        print("$num ")
    }
    println()
}

fun applyOperation(numbers: List<Int>, operation: (Int) -> Int) {
    for (num in numbers) {
        print("${operation(num)} ")
    }
    println()
}

fun main() {
    greet()
    greet("Badal", 21)

    displayInfo(city = "Delhi", age = 21, name = "Badal")

    printNumbers(10, 20, 30, 40, 50)

    val numbers = listOf(1, 2, 3, 4, 5)
    applyOperation(numbers) { it * 2 }
}