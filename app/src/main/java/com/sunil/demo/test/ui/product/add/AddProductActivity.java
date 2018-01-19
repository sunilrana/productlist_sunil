package com.sunil.demo.test.ui.product.add;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sunil.demo.test.AppController;
import com.sunil.demo.test.R;
import com.sunil.demo.test.entity.Product;
import com.sunil.demo.test.injection.ApplicationFactory;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sunil Rana on 10/29/2017.
 */


public class AddProductActivity extends AppCompatActivity {
    public static final String PRODUCT = "product";
    public static final int GALLERY_REQUEST_CODE = 101;


    @BindView(R.id.name_et)
    EditText mNameEView;

    @BindView(R.id.description_et)
    EditText mDescription_et;

    @BindView(R.id.reg_price_et)
    EditText mPriceEView;

    @BindView(R.id.sale_price_et)
    EditText mSalePriceEView;

    @BindView(R.id.rg_color)
    RadioGroup mColorRadioView;

    @BindView(R.id.rg_stores)
    RadioGroup mStoreRadioView;

    @BindView(R.id.photo_id)
    ImageView mPhotoView;

    @BindView(R.id.submit_button)
    Button mSubmitButton;

    private AddProductViewModel addProductViewModel;
    String mPhotoString;

    Product product;
    boolean updateFlag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        ButterKnife.bind(this);
        setupClickListeners();
        setupViewModel();
        checkIntent();

        ActionBar bar = this.getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupViewModel() {
        AppController productApplication = (AppController) getApplication();
        addProductViewModel = ViewModelProviders.of(this, new ApplicationFactory(productApplication)).get(AddProductViewModel.class);
    }

    private void setupClickListeners() {

        mPhotoView.setOnClickListener(v -> {
            getImageFromGallery();
        });
        mSubmitButton.setOnClickListener(v -> {

            if (updateFlag) {
                updateProduct();
                return;
            }

            addProductViewModel.setProductName(mNameEView.getText().toString());
            addProductViewModel.setProductDescription(mDescription_et.getText().toString());
            addProductViewModel.setRegular_price(mPriceEView.getText().toString());
            addProductViewModel.setSale_price(mSalePriceEView.getText().toString());

            int selectedColor = mColorRadioView.getCheckedRadioButtonId();
            addProductViewModel.setColor(((RadioButton) findViewById(selectedColor)).getText().toString());

            int selectedStore = mStoreRadioView.getCheckedRadioButtonId();
            addProductViewModel.setStore(((RadioButton) findViewById(selectedStore)).getText().toString());

            addProductViewModel.addProduct();
            addProductViewModel.getAddCallback().subscribe(bool -> {
                Toast.makeText(this, R.string.add_msg , Toast.LENGTH_SHORT).show();
                finish();
            });

        });
    }

    private void updateProduct() {

        product.setName(mNameEView.getText().toString());
        product.setDescription(mDescription_et.getText().toString());
        product.setRegular_price(mPriceEView.getText().toString());
        product.setSale_price(mSalePriceEView.getText().toString());
        product.setProduct_photo(mPhotoString);

        int selectedColor = mColorRadioView.getCheckedRadioButtonId();
        product.setColor(((RadioButton) findViewById(selectedColor)).getText().toString());

        int selectedStore = mStoreRadioView.getCheckedRadioButtonId();
        product.setStore(((RadioButton) findViewById(selectedStore)).getText().toString());

        addProductViewModel.updateProduct(product);
        addProductViewModel.getAddCallback().subscribe(bool -> {
            Toast.makeText(this, R.string.update_msg , Toast.LENGTH_SHORT).show();
            finish();
        });

    }

    private void checkIntent() {
        product = (Product) getIntent().getSerializableExtra("product");
        if (product != null) {

            updateFlag = true;
            mNameEView.setText(product.getName());
            mDescription_et.setText(product.getDescription());
            mPriceEView.setText(product.getRegular_price());
            mSalePriceEView.setText(product.getSale_price());
            mSubmitButton.setText(R.string.update);
            mPhotoString = product.getProduct_photo();


            if(!TextUtils.isEmpty(mPhotoString)){
                Uri photoURI = Uri.fromFile(new File(mPhotoString));
                Glide.with(this).load(photoURI).override(100, 100).into(mPhotoView);
            }

            for (int i = 0; i < mColorRadioView.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) mColorRadioView.getChildAt(i);
                if (radioButton.getText().toString().equalsIgnoreCase(product.getColor())) {
                    radioButton.setChecked(true);
                    break;
                }
            }

            for (int i = 0; i < mStoreRadioView.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) mStoreRadioView.getChildAt(i);
                if (radioButton.getText().toString().equalsIgnoreCase(product.getStore())) {
                    radioButton.setChecked(true);
                    break;
                }
            }

        }

    }

    private void getImageFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GALLERY_REQUEST_CODE:
                if (resultCode == RESULT_OK) {

                    Uri uri = addProductViewModel.getImageFromGallery(this, data);
                    mPhotoString = uri.getPath();
                    Glide.with(this)
                            .load(uri)
                            .override(100, 100)
                            .into(mPhotoView);

                }
                break;
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
