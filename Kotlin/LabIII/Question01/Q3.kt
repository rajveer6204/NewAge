fun main() {
    print("Enter an integer: ")
    val num = readLine()!!.toInt()

    var n = kotlin.math.abs(num)
    var sum = 0
    var count = 0

    while (n > 0) {
        sum += n % 10
        count++
        n /= 10
    }

    val average = if (count > 0) sum.toDouble() / count else 0.0

    println("Average of digits: $average")
}
