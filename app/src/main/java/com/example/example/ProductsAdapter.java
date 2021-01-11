package com.example.example;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
       ProductHolder pd = new ProductHolder(view);
       return pd;

    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        holder._itemTitle.setText(mProducts.get(position).get_productTitle());
        holder._itemName.setText(mProducts.get(position).get_productName());
        holder._itemPrice.setText(mProducts.get(position).get_productPrice());
        Picasso.get().load(mProducts.get(position).getProductImageUrl())
                .into(holder._itemImage);

        String ilong = mProducts.get(position).getProductLong();
        String ilat = mProducts.get(position).getProductLat();

        holder._itemLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(mContext,MapActivity.class);
                map.putExtra("mapLong",ilong);
                map.putExtra("mapLat",ilat);
                mContext.startActivity(map);
            }
        });




    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder
    {

        public TextView _itemTitle,_itemName,_itemPrice;
        public ImageView _itemImage,_itemLocation;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            _itemTitle = itemView.findViewById(R.id.itemTitle);
            _itemName = itemView.findViewById(R.id.itemName);
            _itemPrice = itemView.findViewById(R.id.itemPrice);
            _itemImage = itemView.findViewById(R.id.itemImage);
            _itemLocation = itemView.findViewById(R.id.itemLocation);

        }
    }

}
