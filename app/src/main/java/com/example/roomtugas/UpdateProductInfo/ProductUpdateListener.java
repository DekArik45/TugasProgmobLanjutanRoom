package com.example.roomtugas.UpdateProductInfo;

import com.example.roomtugas.entity.Product;

public interface ProductUpdateListener {
    void onProductInfoUpdated(Product product, int position);
}
