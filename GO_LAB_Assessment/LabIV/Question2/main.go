package main

import (
	"fmt"
	"math"
	"strconv"
	"strings"
)

// Divide performs division and handles division by zero.
func Divide(a, b float64) (float64, error) {
	if b == 0 {
		return 0, fmt.Errorf("division by zero is not allowed")
	}
	return a / b, nil
}

// SquareRoot calculates the square root of a number.
func SquareRoot(n float64) (float64, error) {
	if n < 0 {
		return 0, fmt.Errorf("square root of a negative number is not allowed")
	}
	return math.Sqrt(n), nil
}

// ParseNumber converts a non-empty string into a number.
func ParseNumber(input string) (float64, error) {
	if strings.TrimSpace(input) == "" {
		return 0, fmt.Errorf("input cannot be empty")
	}

	number, err := strconv.ParseFloat(input, 64)
	if err != nil {
		return 0, fmt.Errorf("invalid number: %q", input)
	}

	return number, nil
}

func main() {
	fmt.Println("Error Handling Demonstration")

	result, err := Divide(10, 2)
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Division result:", result)
	}

	result, err = Divide(10, 0)
	if err != nil {
		fmt.Println("Error:", err)
	}

	root, err := SquareRoot(25)
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Square root:", root)
	}

	root, err = SquareRoot(-9)
	if err != nil {
		fmt.Println("Error:", err)
	}

	number, err := ParseNumber("")
	if err != nil {
		fmt.Println("Error:", err)
	}

	number, err = ParseNumber("123")
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Parsed number:", number)
	}
}
