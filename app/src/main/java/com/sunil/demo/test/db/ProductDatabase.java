package com.sunil.demo.test.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sunil.demo.test.dao.ProductDao;
import com.sunil.demo.test.entity.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

}
