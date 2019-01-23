package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Location;

@Dao
public interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(Location location);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMany(List<Location> locations);

    @Update
    void updateOne(Location location);
    @Update
    void updateMany(List<Location> locations);

    @Delete
    void deleteOne(Location location);
    @Delete
    void deleteMany(List<Location> locations);

    @Query("SELECT * FROM location ORDER BY location_name ASC")
    List<Location> getAll();

    @Query("SELECT * FROM location WHERE id = :query_id LIMIT 1")
    Location getById(int query_id);
}
