package com.uade.tpo.demo.request;

import com.uade.tpo.demo.request.CheckoutItem;

import java.util.List;

public class CheckoutRequest {
    private List<CheckoutItem> cartItems; // Lista de productos
    private String discountApplied; // Descuento aplicado
    private String total; // Total a pagar

    // Constructor
    public CheckoutRequest(List<CheckoutItem> cartItems, String discountApplied, String total) {
        this.cartItems = cartItems;
        this.discountApplied = discountApplied;
        this.total = total;
    }

    // Getters y Setters
    public List<CheckoutItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CheckoutItem> cartItems) {
        this.cartItems = cartItems;
    }

    public String getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(String discountApplied) {
        this.discountApplied = discountApplied;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
