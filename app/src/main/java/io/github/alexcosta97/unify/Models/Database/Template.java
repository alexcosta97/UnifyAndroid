package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(indices = {@Index(value = "template_id", unique = true)})
public class Template {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    @ColumnInfo(name = "template_id")
    public String templateId;
    @ColumnInfo(name = "template_name")
    public String templateName;
    @ColumnInfo(name = "location_id")
    public String locationId;
    @ColumnInfo(name = "company_id")
    public String companyId;
}
