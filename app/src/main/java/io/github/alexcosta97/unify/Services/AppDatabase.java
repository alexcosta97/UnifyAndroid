package io.github.alexcosta97.unify.Services;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.Category;
import io.github.alexcosta97.unify.Models.Database.Company;
import io.github.alexcosta97.unify.Models.Database.Converters;
import io.github.alexcosta97.unify.Models.Database.Location;
import io.github.alexcosta97.unify.Models.Database.Order;
import io.github.alexcosta97.unify.Models.Database.Product;
import io.github.alexcosta97.unify.Models.Database.Subcategory;
import io.github.alexcosta97.unify.Models.Database.SubcategoryProducts;
import io.github.alexcosta97.unify.Models.Database.Supplier;
import io.github.alexcosta97.unify.Models.Database.Template;
import io.github.alexcosta97.unify.Models.Database.TemplateOrderDays;
import io.github.alexcosta97.unify.Models.Database.TemplateSubcategories;
import io.github.alexcosta97.unify.Models.Database.User;
import io.github.alexcosta97.unify.Models.Database.UserLocation;
import io.github.alexcosta97.unify.Models.Database.productsOrdered;
import io.github.alexcosta97.unify.Services.Database.AuthorizationDao;
import io.github.alexcosta97.unify.Services.Database.CategoryDao;
import io.github.alexcosta97.unify.Services.Database.CompanyDao;
import io.github.alexcosta97.unify.Services.Database.LocationDao;
import io.github.alexcosta97.unify.Services.Database.OrderDao;
import io.github.alexcosta97.unify.Services.Database.ProductDao;
import io.github.alexcosta97.unify.Services.Database.ProductsOrderedDao;
import io.github.alexcosta97.unify.Services.Database.SubcategoryDao;
import io.github.alexcosta97.unify.Services.Database.SubcategoryProductsDao;
import io.github.alexcosta97.unify.Services.Database.SupplierDao;
import io.github.alexcosta97.unify.Services.Database.TemplateDao;
import io.github.alexcosta97.unify.Services.Database.TemplateOrderDaysDao;
import io.github.alexcosta97.unify.Services.Database.TemplateSubcategoriesDao;
import io.github.alexcosta97.unify.Services.Database.UserDao;
import io.github.alexcosta97.unify.Services.Database.UserLocationDao;

@Database(entities = {
        Authorization.class,
        Category.class,
        Company.class,
        Location.class,
        Order.class,
        Product.class,
        productsOrdered.class,
        Subcategory.class,
        SubcategoryProducts.class,
        Supplier.class,
        Template.class,
        TemplateOrderDays.class,
        TemplateSubcategories.class,
        User.class,
        UserLocation.class
}, version = 8)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract AuthorizationDao authorizationDao();
    public abstract CategoryDao categoryDao();
    public abstract CompanyDao companyDao();
    public abstract LocationDao locationDao();
    public abstract OrderDao orderDao();
    public abstract ProductDao productDao();
    public abstract ProductsOrderedDao productsOrderedDao();
    public abstract SubcategoryDao subcategoryDao();
    public abstract SubcategoryProductsDao subcategoryProductsDao();
    public abstract SupplierDao supplierDao();
    public abstract TemplateDao templateDao();
    public abstract TemplateOrderDaysDao templateOrderDaysDao();
    public abstract TemplateSubcategoriesDao templateSubcategoriesDao();
    public abstract UserDao userDao();
    public abstract UserLocationDao userLocationDao();
    
    private static volatile AppDatabase dbInstance;

    private static AppDatabase create(final Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "unifyDB.db").fallbackToDestructiveMigration().build();
    }
    
    public static AppDatabase getDatabase(Context context){
        if(dbInstance == null) dbInstance = create(context);
        return dbInstance;
    }
}