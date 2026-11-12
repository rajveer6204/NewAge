package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

const (
	minAllowed = -1000000.0
	maxAllowed = 1000000.0
)

var reader = bufio.NewReader(os.Stdin)

func main() {
	fmt.Println("=== Go Lab 1: Simple Calculator ===")
	fmt.Println("Go environment verified — program compiled and running successfully.")
	fmt.Println()

	numberType := promptNumberType()

	if numberType == "int" {
		a := promptValidInt("Enter first integer: ")
		b := promptValidInt("Enter second integer: ")
		runIntCalculator(a, b)
	} else {
		a := promptValidFloat("Enter first floating-point number: ")
		b := promptValidFloat("Enter second floating-point number: ")
		runFloatCalculator(a, b)
	}
}

func promptNumberType() string {
	for {
		fmt.Print("Work with (i)ntegers or (f)loating-point numbers? [i/f]: ")
		input, _ := reader.ReadString('\n')
		input = strings.ToLower(strings.TrimSpace(input))

		if input == "i" || input == "int" {
			return "int"
		} else if input == "f" || input == "float" {
			return "float"
		}
		fmt.Println("Invalid choice. Please type 'i' or 'f'.")
	}
}

func promptValidInt(prompt string) int {
	for {
		fmt.Print(prompt)
		input, _ := reader.ReadString('\n')
		input = strings.TrimSpace(input)

		value, err := strconv.Atoi(input)
		if err != nil {
			fmt.Println("  -> Not a valid integer. Please try again.")
			continue
		}

		if float64(value) < minAllowed || float64(value) > maxAllowed {
			fmt.Printf("  -> Out of range. Enter a value between %.0f and %.0f.\n", minAllowed, maxAllowed)
			continue
		}

		return value
	}
}

func promptValidFloat(prompt string) float64 {
	for {
		fmt.Print(prompt)
		input, _ := reader.ReadString('\n')
		input = strings.TrimSpace(input)

		value, err := strconv.ParseFloat(input, 64)
		if err != nil {
			fmt.Println("  -> Not a valid number. Please try again.")
			continue
		}

		if value < minAllowed || value > maxAllowed {
			fmt.Printf("  -> Out of range. Enter a value between %.0f and %.0f.\n", minAllowed, maxAllowed)
			continue
		}

		return value
	}
}

func runIntCalculator(a, b int) {
	sum := a + b
	diff := a - b
	product := a * b

	fmt.Println()
	fmt.Println("Integer Results :")
	fmt.Printf("%d + %d = %d\n", a, b, sum)
	fmt.Printf("%d - %d = %d\n", a, b, diff)
	fmt.Printf("%d * %d = %d\n", a, b, product)
}

func runFloatCalculator(a, b float64) {
	sum := a + b
	diff := a - b
	product := a * b

	fmt.Println()
	fmt.Println("Floating-Point Results :")
	fmt.Printf("%.4f + %.4f = %.4f\n", a, b, sum)
	fmt.Printf("%.4f - %.4f = %.4f\n", a, b, diff)
	fmt.Printf("%.4f * %.4f = %.4f\n", a, b, product)
}
