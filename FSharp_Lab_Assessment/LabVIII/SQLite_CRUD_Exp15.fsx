
#r "nuget: Microsoft.Data.Sqlite"

open Microsoft.Data.Sqlite

// Connect to SQLite Database
let connection =
    new SqliteConnection("Data Source=students.db")

connection.Open()

// Create Table
let createTable =
    connection.CreateCommand()

createTable.CommandText <-
    "CREATE TABLE IF NOT EXISTS Students
     (Id INTEGER PRIMARY KEY, Name TEXT, Age INTEGER)"

createTable.ExecuteNonQuery() |> ignore

// Clear old records for repeatable practical output
let clear = connection.CreateCommand()
clear.CommandText <- "DELETE FROM Students"
clear.ExecuteNonQuery() |> ignore

// CREATE Operation
let insert = connection.CreateCommand()
insert.CommandText <-
    "INSERT INTO Students (Id, Name, Age) VALUES (1, 'Badal', 21)"
insert.ExecuteNonQuery() |> ignore

printfn "After CREATE:"
let read1 = connection.CreateCommand()
read1.CommandText <- "SELECT * FROM Students"
use reader1 = read1.ExecuteReader()

while reader1.Read() do
    printfn "%d %s %d"
        (reader1.GetInt32(0))
        (reader1.GetString(1))
        (reader1.GetInt32(2))

// UPDATE Operation
let update = connection.CreateCommand()
update.CommandText <- "UPDATE Students SET Age = 22 WHERE Id = 1"
update.ExecuteNonQuery() |> ignore

printfn "\nAfter UPDATE:"

let read2 = connection.CreateCommand()
read2.CommandText <- "SELECT * FROM Students"
use reader2 = read2.ExecuteReader()

while reader2.Read() do
    printfn "%d %s %d"
        (reader2.GetInt32(0))
        (reader2.GetString(1))
        (reader2.GetInt32(2))

// DELETE Operation
reader1.Close()
reader2.Close()

let delete = connection.CreateCommand()
delete.CommandText <- "DELETE FROM Students WHERE Id = 1"
delete.ExecuteNonQuery() |> ignore

printfn "\nAfter DELETE:"
let read3 = connection.CreateCommand()
read3.CommandText <- "SELECT * FROM Students"
use reader3 = read3.ExecuteReader()

if not (reader3.Read()) then
    printfn "No records found."

reader3.Close()
connection.Close()
