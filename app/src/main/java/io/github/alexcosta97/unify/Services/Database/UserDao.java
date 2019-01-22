package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(User user);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMany(List<User> users);

    @Update
    void updateOne(User user);
    @Update
    void updateMany(List<User> users);

    @Delete
    void deleteOne(User user);
    @Delete
    void deleteMany(List<User> users);

    @Query("SELECT * FROM user ORDER BY id ASC")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE user_id = :query_id LIMIT 1")
    User getById(String query_id);
}
