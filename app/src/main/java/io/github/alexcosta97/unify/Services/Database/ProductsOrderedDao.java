package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.productsOrdered;

@Dao
public interface ProductsOrderedDao {
    @Insert
    void insertOne(productsOrdered product);
    @Insert
    void insertMany(List<productsOrdered> products);

    @Update
    void updateOne(productsOrdered product);
    @Update
    void updateMany(List<productsOrdered> products);

    @Delete
    void deleteOne(productsOrdered product);
    @Delete
    void deleteMany(List<productsOrdered> products);

    @Query("SELECT * FROM products_ordered WHERE order_id = :order")
    List<productsOrdered> getAll(String order);
}
