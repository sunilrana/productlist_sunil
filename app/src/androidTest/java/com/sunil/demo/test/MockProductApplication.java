package com.sunil.demo.test;

import com.sunil.demo.test.dao.DaggerEventDaoTest_MockCountdownComponent;
import com.sunil.demo.test.injection.ApplicationComponent;
import com.sunil.demo.test.injection.MockProductModule;



public class MockProductApplication extends AppController {

    @Override
    public ApplicationComponent createApplicationComponent() {
        return DaggerProductDaoTest_MockProductComponent.builder()
                .mockProductModule(new MockProductModule(this)).build();
    }
}
