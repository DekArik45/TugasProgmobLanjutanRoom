package com.example.roomtugas.CreateProduct;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomtugas.R;
import com.example.roomtugas.entity.Product;
import com.example.roomtugas.repository.ProductRepository;


public class ProductCreateDialogFragment extends DialogFragment {

    private static ProductCreateListener productCreateListener;

    private EditText editProductName;
    private EditText editProductJenis;
    private EditText editProductMerek;
    private EditText editProductDescription;
    private EditText editProductHarga;
    private EditText editProductQty;
    private Button createButton;
    private Button cancelButton;

    private String nameString = "", jenisString = "",merekString = "",descString = "";
    private String hargaInt = "0", qtyInt = "0";

    public ProductCreateDialogFragment() {
        // Required empty public constructor
    }

    public static ProductCreateDialogFragment newInstance(String title, ProductCreateListener listener){
        productCreateListener = listener;
        ProductCreateDialogFragment productCreateDialogFragment = new ProductCreateDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        productCreateDialogFragment.setArguments(args);

        productCreateDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);

        return productCreateDialogFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_create_dialog, container, false);

        editProductName = view.findViewById(R.id.create_product_name);
        editProductMerek = view.findViewById(R.id.create_product_merk);
        editProductJenis = view.findViewById(R.id.create_product_jenis);
        editProductDescription = view.findViewById(R.id.create_product_desc);
        editProductHarga = view.findViewById(R.id.create_harga);
        editProductQty = view.findViewById(R.id.create_qty);

        createButton = view.findViewById(R.id.createButton);
        cancelButton = view.findViewById(R.id.createCancelButton);

        String title = getArguments().getString("Create Product");
        getDialog().setTitle("Create Product");

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameString = editProductName.getText().toString();
                merekString = editProductMerek.getText().toString();
                jenisString = editProductJenis.getText().toString();
                descString = editProductDescription.getText().toString();
                if (editProductHarga.getText().toString().equals("")){
                    hargaInt="0";
                }
                else {
                    hargaInt = editProductHarga.getText().toString();
                }

                if (editProductQty.getText().toString().equals("")){
                    qtyInt="0";
                }
                else {
                    qtyInt = editProductQty.getText().toString();
                }

                ProductRepository productRepository = new ProductRepository(getContext());
                Product product = new Product();
                product.setProductName(nameString);
                product.setProductJenis(jenisString);
                product.setMerk(merekString);
                product.setProductDesc(descString);
                product.setProductHarga(hargaInt);
                product.setProductQty(qtyInt);

                productRepository.insertProduct(product);
                productCreateListener.onProductCreated(product);
                getDialog().dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
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
