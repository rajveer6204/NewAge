
open System

printfn "===== STUDENT DIVISION CALCULATOR ====="

printf "Enter marks for Subject 1: "
let m1 = int(Console.ReadLine())

printf "Enter marks for Subject 2: "
let m2 = int(Console.ReadLine())

printf "Enter marks for Subject 3: "
let m3 = int(Console.ReadLine())

printf "Enter marks for Subject 4: "
let m4 = int(Console.ReadLine())

// Calculate total marks
let total = m1 + m2 + m3 + m4

// Calculate percentage
let percentage = float total / 400.0 * 100.0

// Determine division using pattern matching
let division =
    match percentage with
    | p when p >= 60.0 -> "First Division"
    | p when p >= 50.0 -> "Second Division"
    | p when p >= 40.0 -> "Third Division"
    | _ -> "Fail"

// Display result
printfn "\n===== RESULT ====="
printfn "Total Marks: %d / 400" total
printfn "Percentage: %.2f%%" percentage
printfn "Division: %s" division
