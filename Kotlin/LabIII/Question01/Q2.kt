fun main() {
    val dept_code = "IT"
    val years = 6
    val salary = 50000.0

    val bonusPercentage = when {
        dept_code == "IT" && years > 5 -> 20
        dept_code == "HR" && years > 3 -> 15
        dept_code == "Finance" && years > 4 -> 18
        else -> 10
    }

    val bonus = salary * bonusPercentage / 100

    println("Department: $dept_code")
    println("Bonus Amount: $bonus")
}