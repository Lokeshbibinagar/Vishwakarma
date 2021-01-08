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

public class UserProductAdapter extends RecyclerView.Adapter<UserProductAdapter.UserProductHolder> {


        Context mContext;
        List<Products> mProducts;


public UserProductAdapter(Context mContext, List<Products> mProducts) {
        this.mContext = mContext;
        this.mProducts = mProducts;
        }

@NonNull
@Override
public UserProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item,parent,false);
        UserProductHolder pd = new UserProductHolder(view);
        return pd;

        }

@Override
public void onBindViewHolder(@NonNull UserProductHolder holder, int position) {

        holder._itemTitle.setText(mProducts.get(position).get_productTitle());
        holder._itemName.setText(mProducts.get(position).get_productName());
        holder._itemPrice.setText(mProducts.get(position).get_productPrice());
        Picasso.get().load(mProducts.get(position).getProductImageUrl())
        .into(holder._itemImage);


        }

@Override
public int getItemCount() {
        return mProducts.size();
        }

public class UserProductHolder extends RecyclerView.ViewHolder
{

    public TextView _itemTitle,_itemName,_itemPrice;
    public ImageView _itemImage;

    public UserProductHolder(@NonNull View itemView) {
        super(itemView);

        _itemTitle = itemView.findViewById(R.id.itemTitle);
        _itemName = itemView.findViewById(R.id.itemName);
        _itemPrice = itemView.findViewById(R.id.itemPrice);
        _itemImage = itemView.findViewById(R.id.itemImage);

    }
}

}
