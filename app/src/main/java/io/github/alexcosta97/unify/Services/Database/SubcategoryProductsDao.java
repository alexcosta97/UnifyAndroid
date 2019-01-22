package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.SubcategoryProducts;

@Dao
public interface SubcategoryProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(SubcategoryProducts subcategoryProduct);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMany(List<SubcategoryProducts> subcategoryProducts);

    @Update
    void updateOne(SubcategoryProducts subcategoryProduct);
    @Update
    void updateMany(List<SubcategoryProducts> subcategoryProducts);

    @Delete
    void deleteOne(SubcategoryProducts subcategoryProduct);
    @Delete
    void deleteMany(List<SubcategoryProducts> subcategoryProducts);

    @Query("SELECT * FROM subcategory_products WHERE subcategory_id = :subcategory")
    List<SubcategoryProducts> getAll(String subcategory);
}
