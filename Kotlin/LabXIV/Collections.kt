
data class Student(val name: String, val percentage: Double, val section: String)

fun main() {

    println("===== LIST =====")

    val readOnlyList = listOf(10, 20, 30, 40)
    println("Read-only List: $readOnlyList")
    println("Contains 20: ${readOnlyList.contains(20)}")
    println("Get index 1: ${readOnlyList[1]}")

    val mutableList = mutableListOf(10, 20, 30, 40)
    mutableList.add(50)
    mutableList.remove(20)
    println("Mutable List: $mutableList")
    println("Contains 30: ${mutableList.contains(30)}")
    println("Get index 1: ${mutableList[1]}")

    println("\n===== SET =====")

    val readOnlySet = setOf(10, 20, 30, 40)
    println("Read-only Set: $readOnlySet")
    println("Contains 20: ${readOnlySet.contains(20)}")

    val mutableSet = mutableSetOf(10, 20, 30, 40)
    mutableSet.add(50)
    mutableSet.remove(20)
    println("Mutable Set: $mutableSet")
    println("Contains 30: ${mutableSet.contains(30)}")

    println("\n===== MAP =====")

    val readOnlyMap = mapOf("A" to 90, "B" to 80, "C" to 70)
    println("Read-only Map: $readOnlyMap")
    println("Contains B: ${readOnlyMap.containsKey("B")}")
    println("Get B: ${readOnlyMap["B"]}")

    val mutableMap = mutableMapOf("A" to 90, "B" to 80, "C" to 70)
    mutableMap["D"] = 60
    mutableMap.remove("B")
    println("Mutable Map: $mutableMap")
    println("Contains C: ${mutableMap.containsKey("C")}")
    println("Get C: ${mutableMap["C"]}")

    println("\n===== MAP, FILTER, REDUCE =====")

    val numbers = listOf(1, 2, 3, 4, 5)

    val mapped = numbers.map { it * 2 }
    println("Map: $mapped")

    val filtered = numbers.filter { it > 2 }
    println("Filter: $filtered")

    val reduced = numbers.reduce { total, number -> total + number }
    println("Reduce: $reduced")

    println("\n===== SORT STUDENTS =====")

    val students = listOf(
        Student("Badal", 85.5, "A"),
        Student("Rajveer", 92.0, "B"),
        Student("Shruti", 78.5, "A"),
        Student("Aditya", 88.0, "B"),
        Student("Kishan", 95.0, "A"),
        Student("Shiv", 81.0, "B")
    )

    val sortedStudents = students.sortedByDescending { it.percentage }

    sortedStudents.forEach {
        println("${it.name} - ${it.percentage}% - Section ${it.section}")
    }

    println("\n===== GROUP BY =====")

    val groupedStudents = students.groupBy { it.section }

    groupedStudents.forEach { (section, studentList) ->
        println("Section $section:")
        studentList.forEach {
            println("${it.name} - ${it.percentage}%")
        }
    }
}
