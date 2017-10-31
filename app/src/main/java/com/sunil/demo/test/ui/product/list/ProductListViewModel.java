package com.sunil.demo.test.ui.product.list;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sunil.demo.test.entity.Product;
import com.sunil.demo.test.injection.ApplicationComponent;
import com.sunil.demo.test.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ProductListViewModel extends ViewModel implements ApplicationComponent.Injectable {

    @Inject
    ProductRepository productRepository;
    private LiveData<List<Product>> products = new MutableLiveData<>();

    @Override
    public void inject(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
        products = productRepository.getProducts();
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public void deleteProduct(Product product) {
        productRepository.deleteProduct(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete - deleted product");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("OnError - deleted product: ", e);
                    }
                });
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete - add product");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("OnError - add product: ", e);
                    }
                });
    }


    public void setMockData(boolean value) {
        if (value) {

            addProduct(new Product(0, "Mock3", "Mock Description3", "20", "15", "", "green", "B"));
            addProduct(new Product(0, "Mock2", "Mock Description2", "30", "25", "", "yellow", "A"));
            addProduct(new Product(0, "Mock1", "Mock Description1", "15", "10", "", "red", "A"));

        }
    }

}
