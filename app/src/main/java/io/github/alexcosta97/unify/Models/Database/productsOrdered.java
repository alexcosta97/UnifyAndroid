package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = { @ForeignKey(entity = Product.class, parentColumns = "product_id", childColumns = "product_id"),
        @ForeignKey(entity = Order.class, parentColumns = "order_id", childColumns = "order_id")},
tableName = "products_ordered")
public class productsOrdered {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    @ColumnInfo(name = "order_id")
    public String orderId;
    @ColumnInfo(name = "product_id")
    public String productId;
    public int quantity;
}
