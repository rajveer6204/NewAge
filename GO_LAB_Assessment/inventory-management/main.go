package main

import (
	"errors"
	"fmt"
	"strings"
)

// Item represents a product in the inventory.
type Item struct {
	ID       int
	Name     string
	Quantity int
	Price    float64
}

// Inventory stores items using a slice.
type Inventory struct {
	Items []Item
}

// AddItem adds a new item after validation.
func (inv *Inventory) AddItem(item Item) error {
	if item.ID <= 0 {
		return errors.New("item ID must be positive")
	}
	if strings.TrimSpace(item.Name) == "" {
		return errors.New("item name cannot be empty")
	}
	if item.Quantity < 0 {
		return errors.New("quantity cannot be negative")
	}
	if item.Price < 0 {
		return errors.New("price cannot be negative")
	}

	for _, existing := range inv.Items {
		if existing.ID == item.ID {
			return errors.New("item ID already exists")
		}
	}

	item.Name = strings.TrimSpace(item.Name)
	inv.Items = append(inv.Items, item)
	return nil
}

// UpdateItem updates an existing item.
func (inv *Inventory) UpdateItem(id, quantity int, price float64) error {
	if id <= 0 {
		return errors.New("invalid item ID")
	}
	if quantity < 0 || price < 0 {
		return errors.New("quantity and price must be non-negative")
	}

	for i := range inv.Items {
		if inv.Items[i].ID == id {
			inv.Items[i].Quantity = quantity
			inv.Items[i].Price = price
			return nil
		}
	}

	return errors.New("item not found")
}

// RemoveItem removes an item by ID.
func (inv *Inventory) RemoveItem(id int) error {
	for i, item := range inv.Items {
		if item.ID == id {
			inv.Items = append(inv.Items[:i], inv.Items[i+1:]...)
			return nil
		}
	}
	return errors.New("item not found")
}

// Summary contains inventory statistics.
type Summary struct {
	ItemCount  int
	TotalValue float64
}

// CalculateSummary computes statistics concurrently.
func (inv *Inventory) CalculateSummary() Summary {
	// Copy the slice so the goroutine reads a stable snapshot.
	items := append([]Item(nil), inv.Items...)
	result := make(chan Summary)

	go func() {
		var summary Summary
		summary.ItemCount = len(items)

		for _, item := range items {
			summary.TotalValue +=
				float64(item.Quantity) * item.Price
		}

		result <- summary
	}()

	return <-result
}

func main() {
	inv := Inventory{}

	// Add items.
	err := inv.AddItem(Item{ID: 1, Name: "Laptop", Quantity: 5, Price: 50000})
	if err != nil {
		fmt.Println("Error:", err)
	}

	err = inv.AddItem(Item{ID: 2, Name: "Mouse", Quantity: 10, Price: 500})
	if err != nil {
		fmt.Println("Error:", err)
	}

	// Update an item.
	if err := inv.UpdateItem(1, 6, 52000); err != nil {
		fmt.Println("Error:", err)
	}

	// Display inventory.
	fmt.Println("INVENTORY MANAGEMENT SYSTEM")
	fmt.Println("---------------------------")
	for _, item := range inv.Items {
		fmt.Printf("ID: %d | Name: %s | Quantity: %d | Price: %.2f\n",
			item.ID, item.Name, item.Quantity, item.Price)
	}

	// Calculate summary using a goroutine and channel.
	summary := inv.CalculateSummary()
	fmt.Println("\nINVENTORY SUMMARY")
	fmt.Println("-----------------")
	fmt.Println("Item count:", summary.ItemCount)
	fmt.Printf("Total inventory value: Rs. %.2f\n", summary.TotalValue)

	// Remove an item.
	if err := inv.RemoveItem(2); err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("\nMouse removed successfully.")
	}
}
