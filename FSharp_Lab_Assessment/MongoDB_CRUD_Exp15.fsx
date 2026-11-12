#r "nuget: Microsoft.Data.Sqlite, 8.0.0"
#r "nuget: MongoDB.Driver, 2.28.0"

open System
open Microsoft.Data.Sqlite
open MongoDB.Bson
open MongoDB.Driver

// ==========================================
// PART 1: RELATIONAL DATABASE (SQLite)
// ==========================================

printfn "===== SQLITE CRUD OPERATIONS ====="

// Connect to SQLite
let connection = new SqliteConnection("Data Source=lab8.db")
connection.Open()

// Create Table
let createCmd = connection.CreateCommand()
createCmd.CommandText <-
    "CREATE TABLE IF NOT EXISTS Student
     (Id INTEGER PRIMARY KEY, Name TEXT, Age INTEGER)"
createCmd.ExecuteNonQuery() |> ignore

// CREATE
let insertCmd = connection.CreateCommand()
insertCmd.CommandText <-
    "INSERT OR REPLACE INTO Student VALUES (1, 'Krish', 20)"
insertCmd.ExecuteNonQuery() |> ignore

printfn "\nAfter Create:"

// Function to display records
let showSqlite() =
    let cmd = connection.CreateCommand()
    cmd.CommandText <- "SELECT * FROM Student"

    use reader = cmd.ExecuteReader()

    while reader.Read() do
        printfn "Id: %d, Name: %s, Age: %d"
            (reader.GetInt32(0))
            (reader.GetString(1))
            (reader.GetInt32(2))

showSqlite()

// READ
printfn "\nAfter Read:"
showSqlite()

// UPDATE
let updateCmd = connection.CreateCommand()
updateCmd.CommandText <-
    "UPDATE Student SET Age = 21 WHERE Id = 1"
updateCmd.ExecuteNonQuery() |> ignore

printfn "\nAfter Update:"
showSqlite()

// DELETE
let deleteCmd = connection.CreateCommand()
deleteCmd.CommandText <-
    "DELETE FROM Student WHERE Id = 1"
deleteCmd.ExecuteNonQuery() |> ignore

printfn "\nAfter Delete:"
showSqlite()

connection.Close()

// ==========================================
// PART 2: NOSQL DATABASE (MONGODB ATLAS)
// ==========================================

printfn "\n===== MONGODB CRUD OPERATIONS ====="

// MongoDB Atlas Connection
// Replace this with your NEW Atlas connection string
let connectionString =
mongodb+srv://<db_username>:pkIWy7ALmEEGbGMb@cluster0.7fid1km.mongodb.net/
let client = MongoClient(connectionString)

// Connect to Database
let database = client.GetDatabase("Lab8DB")

// Get Collection
let collection =
    database.GetCollection<BsonDocument>("Students")

// Student Filter
let filter =
    Builders<BsonDocument>.Filter.Eq("_id", 1)

// CREATE
let student =
    BsonDocument()
        .Add("_id", BsonInt32(1))
        .Add("Name", BsonString("BADAL"))
        .Add("Age", BsonInt32(20))

// Remove previous record to avoid duplicate ID
collection.DeleteOne(filter)
collection.InsertOne(student)

printfn "\nAfter Create:"
let createdData = collection.Find(filter).FirstOrDefault()
printfn "%s" (createdData.ToJson())

// READ
printfn "\nAfter Read:"
let readData = collection.Find(filter).FirstOrDefault()
printfn "%s" (readData.ToJson())

// UPDATE
let update =
    Builders<BsonDocument>.Update.Set("Age", 21)

collection.UpdateOne(filter, update)

printfn "\nAfter Update:"
let updatedData = collection.Find(filter).FirstOrDefault()
printfn "%s" (updatedData.ToJson())

// DELETE
collection.DeleteOne(filter)

printfn "\nAfter Delete:"
let deletedData = collection.Find(filter).FirstOrDefault()

if isNull deletedData then
    printfn "Student deleted successfully!"

printfn "\nCRUD Operations Completed!"