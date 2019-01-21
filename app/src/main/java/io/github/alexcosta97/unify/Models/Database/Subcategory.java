package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Company.class, parentColumns = "company_id", childColumns = "company_id"),
        @ForeignKey(entity = Category.class, parentColumns = "category_id", childColumns = "category_id")
})
public class Subcategory {
    @PrimaryKey
    @ColumnInfo(name = "subcategory_id")
    public String subcategoryId;
    @ColumnInfo(name = "subcategory_name")
    public String subcategoryName;
    @ColumnInfo(name = "company_id")
    public String companyId;
    @ColumnInfo(name = "category_id")
    public String categoryId;
}
