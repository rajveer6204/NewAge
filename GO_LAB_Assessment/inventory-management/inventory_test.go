package main

import "testing"

func TestAddItem(t *testing.T) {
	inv := Inventory{}

	err := inv.AddItem(Item{
		ID: 1, Name: "Laptop", Quantity: 5, Price: 50000,
	})

	if err != nil {
		t.Fatalf("unexpected error: %v", err)
	}

	if len(inv.Items) != 1 {
		t.Fatalf("expected 1 item, got %d", len(inv.Items))
	}
}

func TestUpdateItem(t *testing.T) {
	inv := Inventory{}
	_ = inv.AddItem(Item{
		ID: 1, Name: "Laptop", Quantity: 5, Price: 50000,
	})

	err := inv.UpdateItem(1, 10, 45000)
	if err != nil {
		t.Fatalf("unexpected error: %v", err)
	}

	if inv.Items[0].Quantity != 10 {
		t.Errorf("expected quantity 10, got %d", inv.Items[0].Quantity)
	}
}

func TestRemoveItem(t *testing.T) {
	inv := Inventory{}
	_ = inv.AddItem(Item{
		ID: 1, Name: "Mouse", Quantity: 3, Price: 500,
	})

	err := inv.RemoveItem(1)
	if err != nil {
		t.Fatalf("unexpected error: %v", err)
	}

	if len(inv.Items) != 0 {
		t.Errorf("expected empty inventory, got %d items", len(inv.Items))
	}
}

func TestAddDuplicateItem(t *testing.T) {
	inv := Inventory{}
	item := Item{ID: 1, Name: "Keyboard", Quantity: 2, Price: 800}

	if err := inv.AddItem(item); err != nil {
		t.Fatalf("unexpected error: %v", err)
	}

	if err := inv.AddItem(item); err == nil {
		t.Error("expected error for duplicate item ID")
	}
}

func TestCalculateSummary(t *testing.T) {
	inv := Inventory{}
	_ = inv.AddItem(Item{ID: 1, Name: "Laptop", Quantity: 2, Price: 1000})
	_ = inv.AddItem(Item{ID: 2, Name: "Mouse", Quantity: 3, Price: 200})

	summary := inv.CalculateSummary()

	if summary.ItemCount != 2 {
		t.Errorf("expected 2 items, got %d", summary.ItemCount)
	}

	if summary.TotalValue != 2600 {
		t.Errorf("expected total value 2600, got %.2f", summary.TotalValue)
	}
}
