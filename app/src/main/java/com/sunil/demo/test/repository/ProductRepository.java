package com.sunil.demo.test.repository;

import android.arch.lifecycle.LiveData;

import com.sunil.demo.test.entity.Product;

import java.util.List;

import io.reactivex.Completable;

/**
 * Created by Sunil Rana on 10/28/2017.
 */

public interface ProductRepository {

    Completable addProduct(Product product);

    LiveData<List<Product>> getProducts();

    Completable deleteProduct(Product product);

    Completable updateProduct(Product product);

    int getProductCount();

    String getMockData();

}
