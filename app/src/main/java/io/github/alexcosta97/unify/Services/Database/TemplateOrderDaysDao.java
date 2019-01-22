package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.TemplateOrderDays;

@Dao
public interface TemplateOrderDaysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(TemplateOrderDays template);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMany(List<TemplateOrderDays> templates);

    @Update
    void updateOne(TemplateOrderDays template);
    @Update
    void updateMany(List<TemplateOrderDays> templates);

    @Delete
    void deleteOne(TemplateOrderDays template);
    @Delete
    void deleteMany(List<TemplateOrderDays> templates);

    @Query("SELECT * FROM template_order_days WHERE template_id = :template")
    List<TemplateOrderDays> getAll(String template);
}