package com.example.roomtugas.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.roomtugas.Database.AppDatabase;
import com.example.roomtugas.entity.Product;

import java.util.List;

public class ProductRepository {

    private String DB_NAME = "db_product";

    private AppDatabase database;
    public ProductRepository(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public void insertProduct(String productName,
                           String productDesc,
                           String merk,
                           String productJenis,
                           String productHarga,
                           String productQty) {

        insertProduct(productName, productDesc, merk, productJenis, productHarga, productQty);
    }

    public void insertProduct(final Product product) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.ProductDao().insertProduct(product);
                return null;
            }
        }.execute();
    }

    public void updateProduct(final Product product) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.ProductDao().updateProduct(product);
                return null;
            }
        }.execute();
    }

    public void deleteProduct(final int id) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.ProductDao().deleteProduct(id);
                return null;
            }
        }.execute();

    }

    public void deleteAllProduct() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.ProductDao().deleteAllProduct();
                return null;
            }

        }.execute();

    }

    public LiveData<List<Product>> getProduct() {
        return database.ProductDao().fetchAllProducts();
    }
}