package com.sunil.demo.test.ui.product.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.sunil.demo.test.R;

class ProductViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.product_name)
    TextView productNameTv;

    @BindView(R.id.description)
    TextView descriptionTv;

    @BindView(R.id.reg_price)
    TextView priceTv;

    @BindView(R.id.sale_price)
    TextView salesTv;

    @BindView(R.id.color)
    TextView colorTv;

    @BindView(R.id.store)
    TextView storeTv;

    @BindView(R.id.product_photo)
    ImageView mProductPhoto;

    @BindView(R.id.button_delete)
    ImageButton deleteButton;

    @BindView(R.id.button_edit)
    ImageButton editButton;

    ProductViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }
}
