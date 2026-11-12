
// Base Abstract Class
[<AbstractClass>]
type Shape() =
    abstract member Area : unit -> float

// Circle Class
type Circle(radius: float) =
    inherit Shape()

    override this.Area() =
        3.14 * radius * radius

// Rectangle Class
type Rectangle(length: float, width: float) =
    inherit Shape()

    override this.Area() =
        length * width

// Main Program
let main() =

    let c = Circle(5.0)
    let r = Rectangle(4.0, 6.0)

    printfn "Circle Area = %f" (c.Area())
    printfn "Rectangle Area = %f" (r.Area())

    // Polymorphism
    let shapes: Shape list = [c; r]

    printfn "\nPolymorphism:"

    for s in shapes do
        printfn "Area = %f" (s.Area())

// Execute Program
main()
