package main

import "fmt"

func main() {

	// Slice operations
	students := []string{"RAJVEER", "BADAL", "RAJ"}

	fmt.Println("Original Slice:", students)

	// Add
	students = append(students, "Neha")
	fmt.Println("After Add:", students)

	// Remove by index (index 1)
	students = append(students[:1], students[2:]...)
	fmt.Println("After Remove:", students)

	// Update
	students[1] = "Riya"
	fmt.Println("After Update:", students)

	// Map operations
	marks := map[string]int{
		"Math":    80,
		"Java":    85,
		"DBMS":    75,
	}

	fmt.Println("\nOriginal Map:", marks)

	// Insert
	marks["Go"] = 90
	fmt.Println("After Insert:", marks)

	// Update
	marks["Java"] = 95
	fmt.Println("After Update:", marks)

	// Delete
	delete(marks, "DBMS")
	fmt.Println("After Delete:", marks)

	// Lookup
	fmt.Println("Java Marks:", marks["Java"])
}