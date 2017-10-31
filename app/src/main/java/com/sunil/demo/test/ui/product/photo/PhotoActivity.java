package com.sunil.demo.test.ui.product.photo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sunil.demo.test.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoActivity extends AppCompatActivity {

    @BindView(R.id.photo)
    ImageView photoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        String photoAddress = getIntent().getStringExtra("photo");
        if(!TextUtils.isEmpty(photoAddress)){
            Uri photoURI = Uri.fromFile(new File(photoAddress));
            Glide.with(this)
                    .load(photoURI)
                    .into(photoView);
        }


        ActionBar bar = this.getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
