package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Supplier.class, parentColumns = "supplier_id", childColumns = "supplier_id"))
public class Product {
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    public String productId;
    @ColumnInfo(name = "product_name")
    public String productName;
    public double price;
    @ColumnInfo(name = "quantity_per_item")
    public String quantityPerItem;
    @ColumnInfo(name = "supplier_id")
    public String supplierId;
    @ColumnInfo(name = "supplier_reference")
    public String supplierReference;
}