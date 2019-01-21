package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Subcategory.class, parentColumns = "subcategory_id", childColumns = "subcategory_id"),
        @ForeignKey(entity = Product.class, parentColumns = "product_id", childColumns = "product_id")
}, tableName = "subcategory_products")
public class SubcategoryProducts {
    @ColumnInfo(name = "subcategory_id")
    public String subcategoryId;
    @ColumnInfo(name = "product_id")
    public String productId;
}
