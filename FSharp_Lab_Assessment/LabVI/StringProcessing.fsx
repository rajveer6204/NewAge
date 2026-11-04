//2501351020 BADAL PRASAD
open System
open System.Text.RegularExpressions

// 1. Reverse a string
let reverseString (str: string) =
    str |> Seq.rev |> String.Concat

// Check palindrome
let isPalindrome (str: string) =
    let cleaned = str.Replace(" ", "").ToLower()
    cleaned = reverseString cleaned

// 2. Transform string using pattern matching
let transformString (str: string) =
    match str with
    | s when Regex.IsMatch(s, @"^[A-Za-z]+$") ->
        s.ToUpper()
    | s when Regex.IsMatch(s, @"^[0-9]+$") ->
        s + s
    | _ -> str

// 3. Validate email using Regex
let isValidEmail (email: string) =
    Regex.IsMatch(
        email,
        @"^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$"
    )

// Main program
printfn "===== STRING PROCESSING IN F# ====="

// Task 1: Reverse and palindrome
printfn "\n--- Reverse and Palindrome ---"

let words = ["madam"; "hello"; "level"]

words
|> List.iter (fun word ->
    printfn "String: %s" word
    printfn "Reversed: %s" (reverseString word)
    printfn "Palindrome: %b" (isPalindrome word)
)

// Task 2: String transformation
printfn "\n--- String Transformation ---"

let inputs = ["hello"; "1234"; "hello123"]

inputs
|> List.iter (fun s ->
    printfn "Input: %s -> Output: %s" s (transformString s)
)

// Task 3: Email validation
printfn "\n--- Email Validation ---"

let emails = [
    "student@gmail.com"
    "badal123@yahoo.com"
    "invalid-email"
    "rajveer@domain"
]

emails
|> List.iter (fun email ->
    if isValidEmail email then
        printfn "%s : Valid Email" email
    else
        printfn "%s : Invalid Email" email
)
