package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Order;

@Dao
public interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(Order order);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMany(List<Order> orders);

    @Update
    void updateOne(Order order);
    @Update
    void updateMany(List<Order> orders);

    @Delete
    void deleteOne(Order order);
    @Delete
    void deleteMany(List<Order> orders);

    @Query("SELECT * FROM orders ORDER BY date DESC")
    List<Order> getAll();

    @Query("SELECT * FROM orders WHERE id = :query_id LIMIT 1")
    Order getById(int query_id);
}
