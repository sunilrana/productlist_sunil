package com.sunil.demo.test.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.sunil.demo.test.entity.Product;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Sunil Rana on 10/28/2017.
 */

@Dao
public interface ProductDao {

    @Query("SELECT * FROM " + Product.TABLE_NAME)
    LiveData<List<Product>> getProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProduct(Product event);

    @Delete
    void deleteProduct(Product event);

    @Update(onConflict = REPLACE)
    void updateProduct(Product event);

    @Query("SELECT COUNT(*) FROM " + Product.TABLE_NAME)
    int getProductCount();

    @Insert
    void insertAll(Product... products);

}
