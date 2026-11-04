open System

// Recursive function to check prime number
let rec isPrime n divisor =
    if n < 2 then
        false
    elif divisor * divisor > n then
        true
    elif n % divisor = 0 then
        false
    else

        isPrime n (divisor + 1)

//2501351020 BADAL PRASAD

// Take input from user
printf "Enter an integer: "
let number = int(Console.ReadLine())

// Check and display result
if isPrime number 2 then
    printfn "%d is a Prime Number." number
else
    printfn "%d is Not a Prime Number." number