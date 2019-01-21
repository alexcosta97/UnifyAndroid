package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Company.class,
        parentColumns = "company_id",
        childColumns = "company_id"))
public class Category {
    @PrimaryKey
    @ColumnInfo(name = "category_id")
    public String categoryId;
    @ColumnInfo(name = "category_name")
    public String categoryName;
    @ColumnInfo(name = "company_id")
    public String companyId;
}
