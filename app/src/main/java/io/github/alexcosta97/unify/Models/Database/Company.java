package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Company {
    @PrimaryKey
    @ColumnInfo(name = "company_id")
    public String _id;
    @ColumnInfo(name = "company_name")
    public String name;
    public String email;
    public String phone;
}
