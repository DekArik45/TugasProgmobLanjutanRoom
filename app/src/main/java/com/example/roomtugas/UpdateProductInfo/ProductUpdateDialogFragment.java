package com.example.roomtugas.UpdateProductInfo;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomtugas.entity.Product;
import com.example.roomtugas.R;
import com.example.roomtugas.repository.ProductRepository;

import java.util.List;


public class ProductUpdateDialogFragment extends DialogFragment {

    private static int idProduct;
    private static int productItemPosition;
    private static ProductUpdateListener productUpdateListener;

    private Product mProduct;

    private EditText editProductName;
    private EditText editProductJenis;
    private EditText editProductMerek;
    private EditText editProductDescription;
    private EditText editProductHarga;
    private EditText editProductQty;
    private Button updateButton;
    private Button cancelButton;

    private String nameString = "", jenisString = "",merekString = "",descString = "";
    private String hargaInt = "0", qtyInt = "0";

    private ProductRepository productRepository;

    public ProductUpdateDialogFragment() {
        // Required empty public constructor
    }

    public static ProductUpdateDialogFragment newInstance(int id, int position, ProductUpdateListener listener){
        idProduct = id;
        Log.e("id",idProduct+"");
        productItemPosition = position;
        productUpdateListener = listener;
        ProductUpdateDialogFragment productUpdateDialogFragment = new ProductUpdateDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", "Update Product");
        productUpdateDialogFragment.setArguments(args);

        productUpdateDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);

        return productUpdateDialogFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_update_dialog, container, false);

        productRepository = new ProductRepository(getContext());

        editProductName = view.findViewById(R.id.update_product_name);
        editProductMerek = view.findViewById(R.id.update_product_merk);
        editProductJenis = view.findViewById(R.id.update_product_jenis);
        editProductDescription = view.findViewById(R.id.update_product_desc);
        editProductHarga = view.findViewById(R.id.update_harga);
        editProductQty = view.findViewById(R.id.update_qty);

        updateButton = view.findViewById(R.id.updateButton);
        cancelButton = view.findViewById(R.id.cancelButton);

//        String title = getArguments().getString("Update Product");
        getDialog().setTitle("Update Product");

        productRepository.getProduct().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                for(final Product mProduct : products) {
                    if (mProduct.getId() == idProduct){

                        editProductName.setText(mProduct.getProductName());
                        editProductMerek.setText(mProduct.getMerk());
                        editProductJenis.setText(mProduct.getProductJenis());
                        editProductDescription.setText(mProduct.getProductDesc());
                        editProductHarga.setText(String.valueOf(mProduct.getProductHarga()));
                        editProductQty.setText(String.valueOf(mProduct.getProductQty()));

                        updateButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                nameString = editProductName.getText().toString();
                                jenisString = editProductJenis.getText().toString();
                                merekString = editProductMerek.getText().toString();
                                descString = editProductDescription.getText().toString();
                                hargaInt = editProductHarga.getText().toString();
                                qtyInt = editProductQty.getText().toString();

                                mProduct.setProductName(nameString);
                                mProduct.setMerk(merekString);
                                mProduct.setProductJenis(jenisString);
                                mProduct.setProductDesc(descString);
                                mProduct.setProductHarga(hargaInt);
                                mProduct.setProductQty(qtyInt);

                                productRepository.updateProduct(mProduct);

                                productUpdateListener.onProductInfoUpdated(mProduct, productItemPosition);
                                getDialog().dismiss();
                            }
                        });

                        cancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getDialog().dismiss();
                            }
                        });
                    }

                }
            }
        });



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            //noinspection ConstantConditions
            dialog.getWindow().setLayout(width, height);
        }
    }

}
