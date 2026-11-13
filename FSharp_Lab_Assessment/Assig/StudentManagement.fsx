
#r "nuget: Microsoft.Data.Sqlite, 8.0.11"

open System
open Microsoft.Data.Sqlite


// Student record
type Student = {
    Id: int
    Name: string
    Course: string
    Marks: float
}

// Database connection
let connectionString = "Data Source=students.db"

// Create database and table
let createDatabase () =
    async {
        use connection = new SqliteConnection(connectionString)
        do! connection.OpenAsync() |> Async.AwaitTask

        use command = connection.CreateCommand()

        command.CommandText <-
            """
            CREATE TABLE IF NOT EXISTS Students (
                Id INTEGER PRIMARY KEY AUTOINCREMENT,
                Name TEXT NOT NULL,
                Course TEXT NOT NULL,
                Marks REAL NOT NULL
            )
            """

        let! _ = command.ExecuteNonQueryAsync() |> Async.AwaitTask
        printfn "Database initialized successfully!"
    }

// CREATE - Add Student
let addStudent name course marks =
    async {
        use connection = new SqliteConnection(connectionString)
        do! connection.OpenAsync() |> Async.AwaitTask

        use command = connection.CreateCommand()

        command.CommandText <-
            "INSERT INTO Students (Name, Course, Marks) VALUES ($name, $course, $marks)"

        command.Parameters.AddWithValue("$name", name) |> ignore
        command.Parameters.AddWithValue("$course", course) |> ignore
        command.Parameters.AddWithValue("$marks", marks) |> ignore
    

        let! result = command.ExecuteNonQueryAsync() |> Async.AwaitTask

        
        printfn "Student added successfully!"
    }

// READ - Display All Students
let getStudents () =
    async {
        use connection = new SqliteConnection(connectionString)
        do! connection.OpenAsync() |> Async.AwaitTask

        use command = connection.CreateCommand()
        command.CommandText <- "SELECT * FROM Students"

        use! reader = command.ExecuteReaderAsync() |> Async.AwaitTask

        let students = ResizeArray<Student>()

        while reader.Read() do
            students.Add({
                Id = reader.GetInt32(0)
                Name = reader.GetString(1)
                Course = reader.GetString(2)
                Marks = reader.GetDouble(3)
            })

        return students |> Seq.toList
    }

// UPDATE - Update Student Marks
let updateStudent id marks =
    async {
        use connection = new SqliteConnection(connectionString)
        do! connection.OpenAsync() |> Async.AwaitTask

        use command = connection.CreateCommand()

        command.CommandText <-
            "UPDATE Students SET Marks = $marks WHERE Id = $id"

        command.Parameters.AddWithValue("$marks", marks) |> ignore
        command.Parameters.AddWithValue("$id", id) |> ignore

        let! result = command.ExecuteNonQueryAsync() |> Async.AwaitTask

        if result > 0 then
            printfn "Student updated successfully!"
        else
            printfn "Student not found!"
    }

// DELETE - Remove Student
let deleteStudent id =
    async {
        use connection = new SqliteConnection(connectionString)
        do! connection.OpenAsync() |> Async.AwaitTask

        use command = connection.CreateCommand()

        command.CommandText <- "DELETE FROM Students WHERE Id = $id"
        command.Parameters.AddWithValue("$id", id) |> ignore

        let! result = command.ExecuteNonQueryAsync() |> Async.AwaitTask

        if result > 0 then
            printfn "Student deleted successfully!"
        else
            printfn "Student not found!"
    }

// Higher-order functions: map and filter
let processStudents students =
    printfn "\nAll Student Names:"

    students
    |> List.map (fun s -> s.Name)
    |> List.iter (printfn "%s")

    printfn "\nStudents scoring 60 or above:"

    students
    |> List.filter (fun s -> s.Marks >= 60.0)
    |> List.iter (fun s ->
        printfn "%s - %.2f" s.Name s.Marks
    )

// Pattern Matching
let checkResult marks =
    match marks with
    | m when m >= 90.0 -> "Excellent"
    | m when m >= 75.0 -> "Very Good"
    | m when m >= 60.0 -> "Good"
    | m when m >= 40.0 -> "Pass"
    | _ -> "Fail"

// Display Students
let displayStudents () =
    async {
        let! students = getStudents()

        printfn "\n----- STUDENT RECORDS -----"

        students
        |> List.iter (fun s ->
            printfn "ID: %d | Name: %s | Course: %s | Marks: %.2f | Result: %s"
                s.Id s.Name s.Course s.Marks (checkResult s.Marks)
        )

        processStudents students
    }

// Main Menu
let rec mainMenu () =
    printfn "\n===== STUDENT MANAGEMENT SYSTEM ====="
    printfn "1. Add Student"
    printfn "2. View Students"
    printfn "3. Update Student Marks"
    printfn "4. Delete Student"
    printfn "5. Exit"

    printf "Enter your choice: "

    match Console.ReadLine() with
    | "1" ->
        printf "Enter student name: "
        let name = Console.ReadLine()

        printf "Enter course: "
        let course = Console.ReadLine()

        printf "Enter marks: "

        match Double.TryParse(Console.ReadLine()) with
        | true, marks when marks >= 0.0 && marks <= 100.0 ->
            addStudent name course marks |> Async.RunSynchronously
        | _ ->
            printfn "Invalid marks! Enter a value between 0 and 100."

        mainMenu()

    | "2" ->
        displayStudents() |> Async.RunSynchronously
        mainMenu()

    | "3" ->
        printf "Enter student ID: "
        let id = Console.ReadLine()

        printf "Enter new marks: "
        let marks = Console.ReadLine()

        match Int32.TryParse(id), Double.TryParse(marks) with
        | (true, studentId), (true, score)
            when score >= 0.0 && score <= 100.0 ->
            updateStudent studentId score |> Async.RunSynchronously
        | _ ->
            printfn "Invalid ID or marks!"

        mainMenu()

    | "4" ->
        printf "Enter student ID to delete: "

        match Int32.TryParse(Console.ReadLine()) with
        | true, id ->
            deleteStudent id |> Async.RunSynchronously
        | _ ->
            printfn "Invalid student ID!"

        mainMenu()

    | "5" ->
        printfn "Thank you for using Student Management System!"

    | _ ->
        printfn "Invalid choice! Try again."
        mainMenu()

// Start Application
createDatabase() |> Async.RunSynchronously
mainMenu()
