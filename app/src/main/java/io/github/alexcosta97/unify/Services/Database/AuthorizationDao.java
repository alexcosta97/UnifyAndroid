package io.github.alexcosta97.unify.Services.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import io.github.alexcosta97.unify.Models.Database.Authorization;

@Dao
public interface AuthorizationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAuthorization(Authorization authorization);

    @Delete
    void deleteAuthorization(Authorization authorization);

    @Query("SELECT * FROM authorization LIMIT 1")
    Authorization getAuthorization();
}
