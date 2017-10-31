package com.sunil.demo.test.ui.product.list;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sunil.demo.test.entity.Product;
import com.sunil.demo.test.injection.ApplicationComponent;
import com.sunil.demo.test.repository.ProductRepository;
import com.sunil.demo.test.ui.product.add.AddProductViewModel;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductListViewModelTest {

    ProductListViewModel productListViewModel;

    @Mock
    ProductRepository productRepository;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();


    @BeforeClass
    public static void setUpClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        productListViewModel = new ProductListViewModel();
        productListViewModel.productRepository = productRepository;

    }

    @AfterClass
    public static void tearDownClass() {
        RxAndroidPlugins.reset();
    }

    @Test
    public void getProducts() throws InterruptedException {
        MutableLiveData<List<Product>> fakeProducts = getProductListMutableData();
        when(productRepository.getProducts()).thenReturn(fakeProducts);

        productListViewModel.inject(new ApplicationComponent() {
            @Override
            public void inject(ProductListViewModel productListViewModel) {
                productListViewModel.productRepository = productRepository;
            }

            @Override
            public void inject(AddProductViewModel addProductViewModel) {

            }
        });
        List<Product> productReturned = getValue(productListViewModel.getProducts());

        verify(productRepository).getProducts();
        assertEquals(1, productReturned.size());
        assertEquals("Name", productReturned.get(0).getName());
    }

    @NonNull
    private MutableLiveData<List<Product>> getProductListMutableData() {
        List<Product> products = new ArrayList<>();
        Product product = new Product(1, "Name", "Desc", "15", "10", "http://abc.com", "red", "A");
        products.add(product);
        MutableLiveData<List<Product>> fakeProducts = new MutableLiveData<>();
        fakeProducts.setValue(products);
        return fakeProducts;
    }

    @Test
    public void deleteProduct() {
        when(productRepository.deleteProduct(any())).thenReturn(Completable.complete());

        productListViewModel.deleteProduct(new Product(1, "Name", "Description","15", "10", "http://abc.com", "red", "A"));

        verify(productRepository).deleteProduct(any());
    }

    public static <T> T getValue(LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        //noinspection unchecked
        return (T) data[0];
    }
}
