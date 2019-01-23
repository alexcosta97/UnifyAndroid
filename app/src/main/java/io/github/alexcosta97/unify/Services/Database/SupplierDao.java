package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Supplier;

@Dao
public interface SupplierDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(Supplier supplier);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMany(List<Supplier> suppliers);

    @Update
    void updateOne(Supplier supplier);
    @Update
    void updateMany(List<Supplier> suppliers);

    @Delete
    void deleteOne(Supplier supplier);
    @Delete
    void deleteMany(List<Supplier> suppliers);

    @Query("SELECT * FROM supplier ORDER BY supplier_name ASC")
    List<Supplier> getAll();

    @Query("SELECT * FROM supplier WHERE id = :query_id LIMIT 1")
    Supplier getById(int query_id);
}
