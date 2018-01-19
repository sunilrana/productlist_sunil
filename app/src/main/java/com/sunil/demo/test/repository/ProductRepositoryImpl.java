package com.sunil.demo.test.repository;

import android.arch.lifecycle.LiveData;

import com.sunil.demo.test.db.ProductDatabase;
import com.sunil.demo.test.entity.Product;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Sunil Rana on 10/28/2017.
 */

public class ProductRepositoryImpl implements ProductRepository {

    @Inject
    ProductDatabase eventDatabase;

    MockData mock;
    public ProductRepositoryImpl(ProductDatabase eventDatabase, MockData mockData) {
        this.eventDatabase = eventDatabase;
        this.mock = mockData;
    }


    @Override
    public Completable addProduct(Product product) {
        return Completable.fromAction(() -> eventDatabase.productDao().addProduct(product));
    }

    @Override
    public LiveData<List<Product>> getProducts() {

        return eventDatabase.productDao().getProducts();
    }

    @Override
    public Completable deleteProduct(Product product) {
        return Completable.fromAction(() -> eventDatabase.productDao().deleteProduct(product));
    }

    @Override
    public Completable updateProduct(Product product) {
        return Completable.fromAction(() -> eventDatabase.productDao().updateProduct(product));
    }

    @Override
    public int getProductCount() {
        return eventDatabase.productDao().getProductCount();
    }

    @Override
    public String getMockData() {
        return mock.mockData;
    }
}

