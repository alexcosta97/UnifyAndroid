package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Location.class, parentColumns = "location_id", childColumns = "location_id"),
        @ForeignKey(entity = Company.class, parentColumns = "company_id", childColumns = "company_id")
})
public class Template {
    @PrimaryKey
    @ColumnInfo(name = "template_id")
    public String templateId;
    @ColumnInfo(name = "template_name")
    public String templateName;
    @ColumnInfo(name = "location_id")
    public String locationId;
    @ColumnInfo(name = "company_id")
    public String companyId;
}
