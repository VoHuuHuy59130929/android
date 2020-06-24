package vn.edu.ntu.vohuuhuy.nav.controller;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.vohuuhuy.nav.dao.AppDatabase;
import vn.edu.ntu.vohuuhuy.nav.dao.ProductDao;
import vn.edu.ntu.vohuuhuy.nav.model.Product;

public class CartControllerDB extends Application implements ICartController {
  AppDatabase appDatabase;
  ProductDao productDao;
  List<Product> shoppingCart = new ArrayList<>();

  public CartControllerDB(Context context) {
    appDatabase = Room.databaseBuilder(context, AppDatabase.class, "product").allowMainThreadQueries().build();
    productDao = appDatabase.productDao();
  }

  @Override
  public List<Product> getAllProduct() {
    return productDao.getAllProduct();
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
    productDao.insertAllProduct(p);
  }

  @Override
  public boolean addToCart(Product p) {
    if(shoppingCart.contains(p))
      return false;
    shoppingCart.add(p);
    return true;
  }
}
