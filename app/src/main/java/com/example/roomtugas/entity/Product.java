package com.example.roomtugas.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;


@Entity (tableName = "tb_product")
public class Product implements Serializable {

    @PrimaryKey (autoGenerate = true)
    public int id;

    @ColumnInfo (name = "product_name")
    public String productName;

    @ColumnInfo (name = "merk")
    public String merk;

    @ColumnInfo (name = "product_description")
    public String productDesc;

    @ColumnInfo (name = "product_jenis")
    public String productJenis;

    @ColumnInfo (name = "product_harga")
    public String productHarga;

    @ColumnInfo (name = "product_qty")
    public String productQty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductJenis() {
        return productJenis;
    }

    public void setProductJenis(String productJenis) {
        this.productJenis = productJenis;
    }

    public String getProductHarga() {
        return productHarga;
    }

    public void setProductHarga(String productHarga) {
        this.productHarga = productHarga;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }
}