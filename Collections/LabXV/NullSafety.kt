
fun main() {

    println("===== 1. NULLABLE AND NON-NULLABLE =====")

    val nonNullable: String = "Kotlin"
    val nullable: String? = null

    println("Non-nullable String: $nonNullable")
    println("Nullable String: $nullable")

    println("\nNon-nullable String cannot store null.")
    println("Nullable String can store null.")

    println("\n===== 2. SAFE CALL OPERATOR =====")

    val name: String? = "Badal"
    val nullName: String? = null

    println("Name length: ${name?.length}")
    println("Null name length: ${nullName?.length}")

    println("\n===== 3. ELVIS OPERATOR =====")

    val studentName: String? = null
    val result = studentName ?: "Default Student"

    println("Student name: $result")

    println("\n===== 4. NOT-NULL ASSERTION (!!) =====")

    val value: String? = null

    try {
        println("Length: ${value!!.length}")
    } catch (e: NullPointerException) {
        println("NullPointerException caught!")
        println("Cannot force unwrap a null value.")
    }
}
