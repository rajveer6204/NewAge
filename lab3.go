package main

import "fmt"

type Person struct {
	Name   string
	Age    int
	Job    string
	Salary float64
}

func main() {

	var p1, p2 Person

	// Person 1
	fmt.Println("Enter details for Person 1")

	fmt.Print("Enter Name: ")
	fmt.Scan(&p1.Name)

	fmt.Print("Enter Age: ")
	fmt.Scan(&p1.Age)

	fmt.Print("Enter Job: ")
	fmt.Scan(&p1.Job)

	fmt.Print("Enter Salary: ")
	fmt.Scan(&p1.Salary)

	// Display Person 1
	fmt.Println("\n--- Person 1 Details ---")
	fmt.Println("Name:", p1.Name)
	fmt.Println("Age:", p1.Age)
	fmt.Println("Job:", p1.Job)
	fmt.Println("Salary:", p1.Salary)

	// Person 2
	fmt.Println("\nEnter details for Person 2")

	fmt.Print("Enter Name: ")
	fmt.Scan(&p2.Name)

	fmt.Print("Enter Age: ")
	fmt.Scan(&p2.Age)

	fmt.Print("Enter Job: ")
	fmt.Scan(&p2.Job)

	fmt.Print("Enter Salary: ")
	fmt.Scan(&p2.Salary)

	// Display Person 2
	fmt.Println("\n--- Person 2 Details ---")
	fmt.Println("Name:", p2.Name)
	fmt.Println("Age:", p2.Age)
	fmt.Println("Job:", p2.Job)
	fmt.Println("Salary:", p2.Salary)
}
