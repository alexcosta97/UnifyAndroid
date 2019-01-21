package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.UserLocation;

@Dao
public interface UserLocationDao {
    @Insert
    void insertOne(UserLocation user);
    @Insert
    void insertMany(List<UserLocation> users);

    @Update
    void updateOne(UserLocation user);
    @Update
    void updateMany(List<UserLocation> users);

    @Delete
    void deleteOne(UserLocation user);
    @Delete
    void deleteMany(List<UserLocation> users);

    @Query("SELECT * FROM user_location WHERE user_id = :user")
    List<UserLocation> getAll(String user);
}