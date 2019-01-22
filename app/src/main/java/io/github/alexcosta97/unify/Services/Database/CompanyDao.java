package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Company;

@Dao
public interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(Company company);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMany(List<Company> companies);

    @Update
    void updateOne(Company company);
    @Update
    void updateMany(List<Company> companies);

    @Delete
    void deleteOne(Company company);
    @Delete
    void deleteMany(List<Company> companies);

    @Query("SELECT * FROM company ORDER BY company_name ASC")
    List<Company> getAll();

    @Query("SELECT * FROM company WHERE company_id = :query_id LIMIT 1")
    Company getById(String query_id);
}
