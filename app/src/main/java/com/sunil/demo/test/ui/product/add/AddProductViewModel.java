package com.sunil.demo.test.ui.product.add;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;
import com.sunil.demo.test.entity.Product;
import com.sunil.demo.test.injection.ApplicationComponent;
import com.sunil.demo.test.repository.ProductRepository;

/**
 * Created by Sunil Rana on 10/29/2017.
 */

public class AddProductViewModel extends ViewModel implements ApplicationComponent.Injectable {

    @Inject
    ProductRepository productRepository;

    private final PublishSubject<Boolean> addCallback = PublishSubject.create();

    public AddProductViewModel() {
    }

    private String productName;
    private String productDescription;
    private String regular_price;
    private String sale_price;
    private String photo;
    private String color;
    private String store;

    public Observable<Boolean> getAddCallback(){
        return addCallback;
    }

    public String getRegular_price() {
        return regular_price;
    }

    public void setRegular_price(String regular_price) {
        this.regular_price = regular_price;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }


    public void addProduct() {
        Product product = new Product(0, productName, productDescription, regular_price, sale_price,  photo,  color,  store);
        productRepository.addProduct(product).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete - successfully added product");
                        addCallback.onNext(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("onError - add:", e);
                    }
                });
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onComplete() {
                        Timber.d("onComplete - successfully updated product");
                        addCallback.onNext(true);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Timber.d("onError - add:", e);
                    }
                });
    }


    public Uri getImageFromGallery(Context context, Intent intent){
        Uri selectedImage = intent.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        photo = cursor.getString(columnIndex);
        cursor.close();

        Uri photoURI = Uri.fromFile(new File(photo));

        return photoURI;
    }


    @Override
    public void inject(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }
}