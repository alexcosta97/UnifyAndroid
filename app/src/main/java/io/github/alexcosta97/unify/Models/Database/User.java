package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Company.class, parentColumns = "company_id", childColumns = "company_id")
})
public class User {
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    public String userId;
    public String email;
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    public String role;
    @ColumnInfo(name = "company_id")
    public String companyId;
}
