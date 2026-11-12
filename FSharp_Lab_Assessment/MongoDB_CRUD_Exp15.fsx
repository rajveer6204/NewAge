
#r "nuget: MongoDB.Driver"

open MongoDB.Driver
open MongoDB.Bson

// Connect to MongoDB
let client = MongoClient("mongodb://localhost:27017")

let database = client.GetDatabase("StudentDB")

let collection =
    database.GetCollection<BsonDocument>("Students")

// Clear old records
collection.DeleteMany(FilterDefinition<BsonDocument>.Empty)
|> ignore

// CREATE Operation
let student =
    BsonDocument()
        .Add("_id", BsonInt32(1))
        .Add("name", BsonString("Badal"))
        .Add("age", BsonInt32(21))
collection.InsertOne(student)

printfn "After CREATE:"
for s in collection.Find(FilterDefinition<BsonDocument>.Empty).ToList() do
    printfn "%O" s

// READ Operation
printfn "\nAfter READ:"
let filter =
    Builders<BsonDocument>.Filter.Eq<int>("_id", 1)

let result = collection.Find(filter).FirstOrDefault()
printfn "%O" result

// UPDATE Operation
let update =
    Builders<BsonDocument>.Update.Set<int>("age", 22)

collection.UpdateOne(filter, update) |> ignore

printfn "\nAfter UPDATE:"
for s in collection.Find(FilterDefinition<BsonDocument>.Empty).ToList() do
    printfn "%O" s

// DELETE Operation
collection.DeleteOne(filter) |> ignore

printfn "\nAfter DELETE:"
let remaining =
    collection.Find(FilterDefinition<BsonDocument>.Empty).ToList()

if remaining.Count = 0 then
    printfn "No records found."
else
    for s in remaining do
        printfn "%O" s
