package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = Location.class, parentColumns = "location_id", childColumns = "location_id"),
        @ForeignKey(entity = Supplier.class, parentColumns = "supplier_id", childColumns = "supplier_id")
}, tableName = "orders")
public class Order {
    @PrimaryKey
    @ColumnInfo(name = "order_id")
    public String orderId;
    @ColumnInfo(name = "location_id")
    public String locationId;
    public Date date;
    @ColumnInfo(name = "supplier_id")
    public String supplierId;
}
