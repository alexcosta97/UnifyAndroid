package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Category;

@Dao
public interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(Category category);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMany(List<Category> categories);

    @Update
    void updateOne(Category category);
    @Update
    void updateMany(List<Category> categories);

    @Delete
    void deleteOne(Category category);
    @Delete
    void deleteMany(List<Category> categories);

    @Query("SELECT * FROM category ORDER BY category_name ASC")
    List<Category> getAll();

    @Query("SELECT * FROM category WHERE id = :query_id LIMIT 1")
    Category getById(int query_id);
}
