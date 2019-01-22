package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(indices = {@Index(value = "supplier_id", unique = true)})
public class Supplier {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    @ColumnInfo(name = "supplier_id")
    public String supplierId;
    @ColumnInfo(name = "supplier_name")
    public String supplierName;
    public String phone;
    public String email;
    public String fax;
}