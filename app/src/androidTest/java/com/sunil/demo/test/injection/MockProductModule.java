package com.sunil.demo.test.injection;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.sunil.demo.test.AppController;
import com.sunil.demo.test.db.ProductDatabase;
import com.sunil.demo.test.repository.ProductRepository;
import com.sunil.demo.test.repository.ProductRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MockProductModule {

    private final AppController application;

    public MockProductModule(AppController productApplication) {
        this.application = productApplication;
    }

    @Provides
    Context getApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    ProductDatabase provideProductDatabase(Context context) {
        return Room.inMemoryDatabaseBuilder(context.getApplicationContext(), ProductDatabase.class).build();
    }

    @Provides
    @Singleton
    ProductRepository providesProductRepository(ProductDatabase productDatabase) {
        return new ProductRepositoryImpl(productDatabase);
    }

}
