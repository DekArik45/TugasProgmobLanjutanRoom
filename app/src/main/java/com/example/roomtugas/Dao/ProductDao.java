package com.example.roomtugas.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.roomtugas.entity.Product;

import java.util.List;


@Dao
public interface ProductDao {

    @Insert
    Long insertProduct(Product product);


    @Query("SELECT * FROM tb_product ORDER BY id desc")
    LiveData<List<Product>> fetchAllProducts();


    @Update
    void updateProduct(Product product);


    @Query("Delete from tb_product where id=:id")
    void deleteProduct(int id);

    @Query("Delete from tb_product")
    void deleteAllProduct();
}