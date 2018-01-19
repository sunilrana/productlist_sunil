package com.sunil.demo.test.injection;

import com.sunil.demo.test.ui.product.add.AddProductViewModel;
import com.sunil.demo.test.ui.product.list.ProductListViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sunil Rana on 10/28/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(AddProductViewModel addProductViewModel);
    void inject(ProductListViewModel productListViewModel);

    interface Injectable {
        void inject(ApplicationComponent countdownComponent);
    }
}
