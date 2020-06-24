package vn.edu.ntu.vohuuhuy.nav.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vn.edu.ntu.vohuuhuy.nav.model.Product;

@Dao
public interface ProductDao {
  @Query("SELECT * FROM product")
  List<Product> getAllProduct ();

  @Query("SELECT * FROM product WHERE name IN (:names)")
  List<Product> findProductByNames (String[] names);

  @Query("SELECT * FROM product Where id = :id LIMIT 1")
  Product findProductById (int id);

  @Insert
  boolean insertAllProduct (Product...products);

  @Update
  void updateProducts (Product...products);

  @Delete
  void deleteProduct (Product product);
}
