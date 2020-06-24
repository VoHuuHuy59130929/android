package vn.edu.ntu.vohuuhuy.nav.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.vohuuhuy.nav.model.Product;

public class CartController extends Application implements ICartController {

    List<Product> productList = new ArrayList<>();
    List<Product> shoppingCart = new ArrayList<>();

    public CartController() {
        productList.add(new Product("Khoai Lang Ninh Hoa", 12000, "Khoai lang cao to den hoi"));
        productList.add(new Product("Ca tim Ninh Hoa", 8000, "Ca tim to dai sang bong"));
        productList.add(new Product("Chuoi Ninh Hoa", 10000, "Chuoi xanh to dai map map mum mim"));
        productList.add(new Product("Xoai Ninh Hoa", 6000, "Xoai sieu to khong lo"));
        productList.add(new Product("Thanh long Ninh Hoa", 22000, "Thanh long ruot do khong hat thom ngon"));
    }

    @Override
    public List<Product> getAllProduct() {
        return productList;
    }

    @Override
    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void clearShoppingCart() {
        shoppingCart.clear();
    }

    @Override
    public void addProduct(Product p) {
        if (!productList.contains(p)) productList.add(p);
    }

    public boolean addToCart(Product p)
    {
        if(shoppingCart.contains(p))
            return false;
        shoppingCart.add(p);
        return true;
    }
}
