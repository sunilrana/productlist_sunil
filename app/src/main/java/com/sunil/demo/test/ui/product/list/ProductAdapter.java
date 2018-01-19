package com.sunil.demo.test.ui.product.list;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sunil.demo.test.R;
import com.sunil.demo.test.entity.Product;

import java.io.File;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private final Context context;
    private List<Product> items;
    private View.OnClickListener deleteClickListener;
    private View.OnClickListener photoClickListener;
    private View.OnClickListener editClickListener;

    ProductAdapter(List<Product> items, Context context, View.OnClickListener photoClickListener, View.OnClickListener deleteClickListener, View.OnClickListener editClickListener) {
        this.items = items;
        this.context = context;
        this.deleteClickListener = deleteClickListener;
        this.photoClickListener = photoClickListener;
        this.editClickListener = editClickListener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product item = items.get(position);
        holder.productNameTv.setText(item.getName());
        holder.descriptionTv.setText(item.getDescription());
        holder.priceTv.setText(context.getString(R.string.regular_price_detail, item.getRegular_price()));
        holder.salesTv.setText(context.getString(R.string.sale_price_detail, item.getSale_price()));
        holder.colorTv.setText(context.getString(R.string.color_detail, item.getColor()));
        holder.storeTv.setText(context.getString(R.string.store_detail, item.getStore()));

        holder.mProductPhoto.setTag(item);
        holder.deleteButton.setTag(item);
        holder.editButton.setTag(item);
        holder.deleteButton.setOnClickListener(deleteClickListener);
        holder.mProductPhoto.setOnClickListener(photoClickListener);
        holder.editButton.setOnClickListener(editClickListener);

        if(!TextUtils.isEmpty(item.getProduct_photo())){
            Glide.with(context).load(Uri.fromFile(new File(item.getProduct_photo()))).error(R.mipmap.ic_launcher).fallback(R.mipmap.ic_launcher).into(holder.mProductPhoto);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void setItems(List<Product> products) {
        this.items = products;
        notifyDataSetChanged();
    }
}