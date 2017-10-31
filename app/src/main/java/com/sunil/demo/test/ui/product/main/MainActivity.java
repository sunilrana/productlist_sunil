package com.sunil.demo.test.ui.product.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.sunil.demo.test.R;
import com.sunil.demo.test.ui.product.add.AddProductActivity;
import com.sunil.demo.test.ui.product.list.ProductListActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.add_id)
    Button addProductTv;

    @BindView(R.id.show_id)
    Button showProductTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        addProductTv.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AddProductActivity.class)));
        showProductTv.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ProductListActivity.class)));

    }
}
