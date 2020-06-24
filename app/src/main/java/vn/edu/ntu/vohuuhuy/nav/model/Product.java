package vn.edu.ntu.vohuuhuy.nav.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Product")
public class Product {
  @PrimaryKey(autoGenerate = true)
  int id;
  @ColumnInfo
  private String name;
  @ColumnInfo
  private String desc;
  @ColumnInfo
  private int price;
  public Product (String name, int price, String desc) {
    setName(name);
    setPrice(price);
    setDesc(desc);
  }

  public int getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getPrice() {
    return price;
  }

  public String getDesc() {
    return desc;
  }

  public String getName() {
    return name;
  }
}
