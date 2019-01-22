package io.github.alexcosta97.unify.Models.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = Template.class, parentColumns = "template_id", childColumns = "template_id")
}, tableName = "template_order_days")
public class TemplateOrderDays {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    @ColumnInfo(name = "template_id")
    public String templateId;
    @ColumnInfo(name = "order_day")
    public Date orderDay;
}
