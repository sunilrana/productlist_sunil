package com.sunil.demo.test.injection;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.sunil.demo.test.AppController;
import com.sunil.demo.test.db.ProductDatabase;
import com.sunil.demo.test.repository.MockData;
import com.sunil.demo.test.repository.ProductRepository;
import com.sunil.demo.test.repository.ProductRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sunil Rana on 10/28/2017.
 */

@Module
public class ApplicationModule {

    private AppController application;

    public ApplicationModule(AppController app) {
        this.application = app;
    }

    @Provides
    Context applicationContext() {
        return application;
    }


    @Provides
    @Singleton
    ProductDatabase providesProductDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), ProductDatabase.class, "product_db").build();
    }


    @Provides
    @Singleton
    ProductRepository providesProductRepository(ProductDatabase eventDatabase , MockData mockData) {
        return new ProductRepositoryImpl(eventDatabase, mockData);
    }

    @Provides
    MockData provideMockData(Context context) {
        return new MockData(context);
    }
}
