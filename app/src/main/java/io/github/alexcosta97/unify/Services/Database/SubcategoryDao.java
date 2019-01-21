package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Subcategory;

@Dao
public interface SubcategoryDao {
    @Insert
    void insertOne(Subcategory subcategory);
    @Insert
    void insertMany(List<Subcategory> subcategories);

    @Update
    void updateOne(Subcategory subcategory);
    @Update
    void updateMany(List<Subcategory> subcategories);

    @Delete
    void deleteOne(Subcategory subcategory);
    @Delete
    void deleteMany(List<Subcategory> subcategories);

    @Query("SELECT * FROM subcategory ORDER BY subcategory_name ASC")
    List<Subcategory> getAll();

    @Query("SELECT * FROM subcategory WHERE subcategory_id = :query_id LIMIT 1")
    Subcategory getById(String query_id);
}
