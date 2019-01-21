package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity= Company.class,
        parentColumns = "company_id",
        childColumns = "company_id"))
public class Location {
    @PrimaryKey
    @ColumnInfo(name = "location_id")
    public String locationId;
    @ColumnInfo(name = "location_name")
    public String locationName;
    public String phone;
    public String fax;
    public String email;
    @ColumnInfo(name = "company_id")
    public String companyId;
    @Embedded
    public Address address;
}
