package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Authorization {
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    public String userId;
    public String token;
}