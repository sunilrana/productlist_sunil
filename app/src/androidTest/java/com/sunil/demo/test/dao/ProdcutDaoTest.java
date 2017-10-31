package com.sunil.demo.test.dao;

import android.app.Instrumentation;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.sunil.demo.test.MockProductApplication;
import com.sunil.demo.test.db.ProductDatabase;
import com.sunil.demo.test.entity.Product;
import com.sunil.demo.test.injection.ApplicationComponent;
import com.sunil.demo.test.injection.MockProductModule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ProdcutDaoTest {

    ProductDao productDao;

    @Inject
    ProductDatabase productDatabase;

    @Singleton
    @Component(modules = {MockProductModule.class})
    public interface MockCountdownComponent extends ApplicationComponent {
        void inject(ProdcutDaoTest productDaoTest);
    }

    @Before
    public void setup() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        MockProductApplication app
                = (MockProductApplication) instrumentation.getTargetContext().getApplicationContext();
        MockCountdownComponent component = (MockCountdownComponent) app.getApplicationComponent();
        component.inject(this);

        productDao = productDatabase.productDao();
    }


    @Test
    public void addProduct_SuccessfullyAddsProduct() throws InterruptedException {
        Product product = generateProductTestData(0, "Wedding");
        productDao.addProduct(product);

        List<Product> productRetrieved = getValue(productDao.getProducts());

        assertEquals(product.getName(), productRetrieved.get(0).getName());
        productDao.deleteProduct(productRetrieved.get(0));
    }

    @Test
    public void deleteProduct_SuccessfullyDeletesProduct() throws InterruptedException {
        Product product = generateProductTestData(0, "Product1");
        productDao.addProduct(product);

        List<Product> productRetrieved = getValue(productDao.getProducts());
        assertEquals(product.getName(), productRetrieved.get(0).getName());

        productDao.deleteProduct(productRetrieved.get(0));
        List<Product> productRetrievedAfterUpdate = getValue(productDao.getProducts());

        assertEquals(0, productRetrievedAfterUpdate.size());
    }


    @Test
    public void updateProduct_SuccessfullyUpdatesProduct() throws InterruptedException {
        Product product = generateProductTestData(0, "First Name");
        productDao.addProduct(product);

        List<Product> productsRetrieved = getValue(productDao.getProducts());
        Product productRetrieved = productsRetrieved.get(0);
        assertEquals(product.getName(), productRetrieved.getName());
        String newName = "New Name";
        Product newProductUpdated = generateProductTestData(productRetrieved.getId(), newName);

        productDao.updateProduct(newProductUpdated);
        List<Product> productRetrievedAfterUpdate = getValue(productDao.getProducts());

        assertEquals(newName, productRetrievedAfterUpdate.get(0).getName());
        productDao.deleteProduct(productRetrievedAfterUpdate.get(0));
    }


    Product generateProductTestData(int id, String name) {
        return new Product(id, name, "Test Description", "15", "10", "http://abc.com", "red", "A");
          }

    /**
     * This is used to make sure the method waits till data is available from the observer.
     */
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
