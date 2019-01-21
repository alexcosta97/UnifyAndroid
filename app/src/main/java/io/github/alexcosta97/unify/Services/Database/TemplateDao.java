package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Template;

@Dao
public interface TemplateDao {
    @Insert
    void insertOne(Template template);
    @Insert
    void insertMany(List<Template> templates);

    @Update
    void updateOne(Template template);
    @Update
    void updateMany(List<Template> templates);

    @Delete
    void deleteOne(Template template);
    @Delete
    void deleteMany(List<Template> templates);

    @Query("SELECT * FROM template ORDER BY template_name ASC")
    List<Template> getAll();

    @Query("SELECT * FROM template WHERE template_id = :query_id LIMIT 1")
    Template getById(String query_id);
}
