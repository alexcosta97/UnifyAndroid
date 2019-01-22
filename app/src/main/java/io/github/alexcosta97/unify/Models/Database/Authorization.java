package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Authorization {
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    @NonNull
    public String userId;
    public String token;
    public String role;
    public String company;
}