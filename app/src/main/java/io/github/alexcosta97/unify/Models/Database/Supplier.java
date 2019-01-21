package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Supplier {
    @PrimaryKey
    @ColumnInfo(name = "supplier_id")
    public String supplierId;
    @ColumnInfo(name = "supplier_name")
    public String supplierName;
    public String phone;
    public String email;
    public String fax;
}