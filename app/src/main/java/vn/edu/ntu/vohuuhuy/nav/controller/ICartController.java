package vn.edu.ntu.vohuuhuy.nav.controller;

import java.util.List;

import vn.edu.ntu.vohuuhuy.nav.model.Product;

public interface ICartController {
    List<Product> getAllProduct();
    public boolean addToCart(Product p);
    public List<Product> getShoppingCart();
    public void clearShoppingCart();
    public void addProduct(Product p);
}
