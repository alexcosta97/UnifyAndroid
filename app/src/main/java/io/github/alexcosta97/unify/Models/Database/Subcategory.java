package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(indices = {@Index(value = "subcategory_id", unique = true)})
public class Subcategory {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    @ColumnInfo(name = "subcategory_id")
    public String subcategoryId;
    @ColumnInfo(name = "subcategory_name")
    public String subcategoryName;
    @ColumnInfo(name = "company_id")
    public String companyId;
    @ColumnInfo(name = "category_id")
    public String categoryId;
}
