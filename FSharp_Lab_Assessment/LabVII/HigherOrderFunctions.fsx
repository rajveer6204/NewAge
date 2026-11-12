
// 2501351020 BADAL PRASAD
// LAB VII

let list1 = [1; 2; 3; 4; 5]
let list2 = [6; 7; 8; 9; 10]

// 1. List.map
printfn "List.map - Square Numbers"

let a = List.map (fun x -> x * x) list1
let b = List.map (fun x -> x * x) list2

printfn "%A" a
printfn "%A" b

// 2. List.filter
printfn "\nList.filter - Even Numbers"

let c = List.filter (fun x -> x % 2 = 0) list1
let d = List.filter (fun x -> x % 2 = 0) list2

printfn "%A" c
printfn "%A" d

// 3. List.fold
printfn "\nList.fold - Sum of Numbers"

let sum1 = List.fold (fun a x -> a + x) 0 list1
let sum2 = List.fold (fun a x -> a + x) 0 list2

printfn "Sum of list1 = %d" sum1
printfn "Sum of list2 = %d" sum2
