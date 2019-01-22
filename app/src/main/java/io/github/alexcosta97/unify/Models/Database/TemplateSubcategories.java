package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = {
        @ForeignKey(entity = Template.class, parentColumns = "template_id", childColumns = "template_id"),
        @ForeignKey(entity = Subcategory.class, parentColumns = "subcategory_id", childColumns = "subcategory_id")
}, tableName = "template_subcategories")
public class TemplateSubcategories {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    @ColumnInfo(name = "template_id")
    public String templateId;
    @ColumnInfo(name = "subcategory_id")
    public String subcategoryId;
}
