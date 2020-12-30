package com.example.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductHolder> {


    Context mContext;
    List<Products> mProducts;


    public ProductsAdapter(Context mContext, List<Products> mProducts) {
        this.mContext = mContext;
        this.mProducts = mProducts;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item,parent,false);
        return new ProductHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Products currentProduct = mProducts.get(position);
        holder._itemTitle.setText(currentProduct.get_productTitle());
        holder._itemName.setText(currentProduct.get_productName());
        holder._itemPrice.setText(currentProduct.get_productPrice());
        Picasso.get().load(currentProduct.getProductImageUrl())
                .fit()
                .centerCrop()
                .into(holder._itemImage);


    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder
    {

        public TextView _itemTitle,_itemName,_itemPrice;
        public ImageView _itemImage;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            _itemTitle = itemView.findViewById(R.id.itemTitle);
            _itemName = itemView.findViewById(R.id.itemName);
            _itemPrice = itemView.findViewById(R.id.itemPrice);
            _itemImage = itemView.findViewById(R.id.itemImage);

        }
    }

}
