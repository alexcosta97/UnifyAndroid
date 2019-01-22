package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(indices = @Index(value = "user_id", unique = true))
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer id;
    @ColumnInfo(name = "user_id")
    public String userId;
    public String email;
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    public String role;
    @ColumnInfo(name = "company_id")
    public String companyId;
}
