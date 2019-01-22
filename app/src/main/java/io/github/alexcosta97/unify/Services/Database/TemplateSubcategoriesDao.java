package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.TemplateSubcategories;

@Dao
public interface TemplateSubcategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(TemplateSubcategories template);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMany(List<TemplateSubcategories> templates);

    @Update
    void updateOne(TemplateSubcategories template);
    @Update
    void updateMany(List<TemplateSubcategories> templates);

    @Delete
    void deleteOne(TemplateSubcategories template);
    @Delete
    void deleteMany(List<TemplateSubcategories> templates);

    @Query("SELECT * FROM template_subcategories WHERE template_id = :template")
    List<TemplateSubcategories> getAll(String template);
}