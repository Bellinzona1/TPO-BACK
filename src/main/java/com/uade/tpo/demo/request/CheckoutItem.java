package com.uade.tpo.demo.request;

public class CheckoutItem {
    private String name; // Nombre del producto
    private double price; // Precio del producto
    private int quantity; // Cantidad del producto

    // Constructor
    public CheckoutItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
