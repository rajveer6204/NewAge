printfn "MongoDB CRUD Program Started"

#r "nuget: MongoDB.Driver"

open MongoDB.Driver
open MongoDB.Bson

let client = MongoClient("mongodb://localhost:27017")
let database = client.GetDatabase("StudentDB")
let collection = database.GetCollection<BsonDocument>("Students")

printfn "MongoDB Connected Successfully!"

// CREATE
let student = BsonDocument()
student.Add("_id", BsonInt32(1))
student.Add("name", BsonString("Badal"))
student.Add("age", BsonInt32(21))

collection.DeleteMany(FilterDefinition<BsonDocument>.Empty) |> ignore
collection.InsertOne(student)

printfn "\nAfter CREATE:"
printfn "%O" student

// READ
printfn "\nAfter READ:"
let filter = Builders<BsonDocument>.Filter.Eq<int>("_id", 1)
let result = collection.Find(filter).FirstOrDefault()
printfn "%O" result

// UPDATE
let update = Builders<BsonDocument>.Update.Set<int>("age", 22)
collection.UpdateOne(filter, update) |> ignore

printfn "\nAfter UPDATE:"
printfn "%O" (collection.Find(filter).FirstOrDefault())

// DELETE
collection.DeleteOne(filter) |> ignore

printfn "\nAfter DELETE:"
printfn "No records found."

printfn "\nCRUD Operations Completed!"