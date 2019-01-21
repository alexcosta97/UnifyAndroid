package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;

public class Address{
    @ColumnInfo(name = "house_number")
    public String houseNumber;
    public String street;
    public String town;
    @ColumnInfo(name = "post_code")
    public String postCode;
    public String county;
    public String country;
}