package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(indices = {@Index(value = "product_id", unique = true)})
public class Product {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
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