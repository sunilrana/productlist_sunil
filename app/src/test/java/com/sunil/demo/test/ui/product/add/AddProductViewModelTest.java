package com.sunil.demo.test.ui.product.add;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.sunil.demo.test.repository.ProductRepository;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Completable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddProductViewModelTest {

    AddProductViewModel addProductViewModel;

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
        addProductViewModel = new AddProductViewModel();
        addProductViewModel.productRepository = productRepository;
    }

    @AfterClass
    public static void tearDownClass() {
        RxAndroidPlugins.reset();
    }

    @Test
    public void addEvent() {
        when(productRepository.addProduct(any())).thenReturn(Completable.complete());

        addProductViewModel.addProduct();

        verify(productRepository).addProduct(any());
    }
}