package io.github.alexcosta97.unify.Services.Database;

        import android.arch.persistence.room.Dao;
        import android.arch.persistence.room.Delete;
        import android.arch.persistence.room.Insert;
        import android.arch.persistence.room.OnConflictStrategy;
        import android.arch.persistence.room.Query;
        import android.arch.persistence.room.Update;

        import java.util.List;

        import io.github.alexcosta97.unify.Models.Database.Product;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(Product product);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMany(List<Product> products);

    @Update
    void updateOne(Product product);
    @Update
    void updateMany(List<Product> products);

    @Delete
    void deleteOne(Product product);
    @Delete
    void deleteMany(List<Product> products);

    @Query("SELECT * FROM product ORDER BY product_name ASC")
    List<Product> getAll();

    @Query("SELECT * FROM product WHERE product_id = :query_id LIMIT 1")
    Product getById(String query_id);
}
