package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "user_id", childColumns = "user_id"),
        @ForeignKey(entity = Location.class, parentColumns = "location_id", childColumns = "location_id")
}, tableName = "user_location")
public class UserLocation{
    @ColumnInfo(name = "user_id")
    public String userId;
    @ColumnInfo(name = "location_id")
    public String locationId;
}
