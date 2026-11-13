package main

import (
	"math"
	"strings"
	"testing"
)

func TestDivide(t *testing.T) {
	result, err := Divide(10, 2)

	if err != nil {
		t.Fatalf("unexpected error: %v", err)
	}

	if result != 5 {
		t.Errorf("expected 5, got %v", result)
	}
}

func TestDivideByZero(t *testing.T) {
	_, err := Divide(10, 0)

	if err == nil {
		t.Fatal("expected division by zero error")
	}

	if !strings.Contains(err.Error(), "division by zero") {
		t.Errorf("unexpected error message: %v", err)
	}
}

func TestSquareRoot(t *testing.T) {
	result, err := SquareRoot(25)

	if err != nil {
		t.Fatalf("unexpected error: %v", err)
	}

	if result != 5 {
		t.Errorf("expected 5, got %v", result)
	}
}

func TestNegativeSquareRoot(t *testing.T) {
	_, err := SquareRoot(-9)

	if err == nil {
		t.Fatal("expected an error for a negative number")
	}

	if !strings.Contains(err.Error(), "negative number") {
		t.Errorf("unexpected error message: %v", err)
	}
}

func TestParseNumber(t *testing.T) {
	result, err := ParseNumber("123")

	if err != nil {
		t.Fatalf("unexpected error: %v", err)
	}

	if result != 123 {
		t.Errorf("expected 123, got %v", result)
	}
}

func TestEmptyInput(t *testing.T) {
	_, err := ParseNumber("")

	if err == nil {
		t.Fatal("expected an error for empty input")
	}

	if !strings.Contains(err.Error(), "empty") {
		t.Errorf("unexpected error message: %v", err)
	}
}

func TestInvalidNumber(t *testing.T) {
	_, err := ParseNumber("abc")

	if err == nil {
		t.Fatal("expected an error for invalid numeric input")
	}
}

func TestSquareRootAccuracy(t *testing.T) {
	result, err := SquareRoot(2)

	if err != nil {
		t.Fatalf("unexpected error: %v", err)
	}

	if math.Abs(result*result-2) > 1e-9 {
		t.Errorf("square root result is inaccurate: %v", result)
	}
}
