package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(indices = {@Index(value = "order_id", unique = true)}, tableName = "orders")
public class Order {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    @ColumnInfo(name = "order_id")
    public String orderId;
    @ColumnInfo(name = "location_id")
    public String locationId;
    public Date date;
    @ColumnInfo(name = "supplier_id")
    public String supplierId;
}
