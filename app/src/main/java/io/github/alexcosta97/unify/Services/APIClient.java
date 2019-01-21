package io.github.alexcosta97.unify.Services;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Category;
import io.github.alexcosta97.unify.Models.Database.Location;
import io.github.alexcosta97.unify.Models.RequestModels.LoginDetails;
import io.github.alexcosta97.unify.Models.Database.Order;
import io.github.alexcosta97.unify.Models.Database.Product;
import io.github.alexcosta97.unify.Models.Database.Subcategory;
import io.github.alexcosta97.unify.Models.Database.Supplier;
import io.github.alexcosta97.unify.Models.Database.Template;
import io.github.alexcosta97.unify.Models.Database.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIClient {
    @POST("auth/")
    Call<ResponseBody> login(@Body LoginDetails loginDetails);

//    @POST("/companies/")
//    Call<ResponseBody> createCompany(@Body CompanyInfo companyInfo);

    @GET("users/")
    Call<List<User>> getUsers();

    @GET("users/{id}")
    Call<User> getUser(@Path("id") String userId);

    @POST("users/")
    Call<ResponseBody> createUser(@Body User user);

    @PUT("users/{id}")
    Call<ResponseBody> updateUser(@Body User user, @Path("id") String userId);

    @DELETE("users/{id}")
    Call<ResponseBody> deleteUser(@Path("id") String userId);

    @GET("locations/")
    Call<List<Location>> getLocations();

    @GET("locations/{id}")
    Call<Location> getLocation(@Path("id") String locationId);

    @POST("locations/")
    Call<ResponseBody> createLocation(@Body Location location);

    @PUT("locations/{id}")
    Call<ResponseBody> updateLocation(@Body Location location, @Path("id") String locationId);

    @DELETE("locations/{id}")
    Call<ResponseBody> deleteLocation(@Path("id") String locationId);

    @GET("suppliers/")
    Call<List<Supplier>> getSuppliers();

    @GET("suppliers/{id}")
    Call<Supplier> getSupplier(@Path("id") String supplierId);

    @POST("suppliers/")
    Call<ResponseBody> createSupplier(@Body Supplier supplier);

    @PUT("suppliers/{id}")
    Call<ResponseBody> updateSupplier(@Body Supplier supplier, @Path("id") String supplierId);

    @DELETE("suppliers/{id}")
    Call<ResponseBody> deleteSupplier(@Path("id") String supplierId);

    @GET("categories/")
    Call<List<Category>> getCategories();

    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") String categoryId);

    @POST("categories/")
    Call<ResponseBody> createCategory(@Body Category category);

    @PUT("categories/{id}")
    Call<ResponseBody> updateCategory(@Body Category category, @Path("id") String categoryId);

    @DELETE("categories/{id}")
    Call<ResponseBody> deleteCategory(@Path("id") String categoryId);

    @GET("products/")
    Call<List<Product>> getProducts();

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") String productId);

    @POST("products/")
    Call<ResponseBody> createProduct(@Body Product product);

    @PUT("products/{id}")
    Call<ResponseBody> updateProducts(@Body Product product, @Path("id") String productId);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") String productId);

    @GET("subcategories/")
    Call<List<Subcategory>> getSubcategories();

    @GET("subcategories/{id}")
    Call<Subcategory> getSubcategory(@Path("id") String subcategoryId);

    @POST("subcategories/")
    Call<ResponseBody> createSubcategory(@Body Subcategory subcategory);

    @PUT("subcategories/{id}")
    Call<ResponseBody> updateSubcategory(@Body Subcategory subcategory, @Path("id") String subcategoryId);

    @DELETE("subcategories/{id}")
    Call<ResponseBody> deleteSubcategory(@Path("id") String subcategory);

    @GET("templates/")
    Call<List<Template>> getTemplates();

    @GET("templates/{id}")
    Call<Template> getTemplate(@Path("id") String templateId);

    @POST("templates/")
    Call<ResponseBody> createTemplate(@Body Template template);

    @PUT("templates/{id}")
    Call<ResponseBody> updateTemplate(@Body Template template, @Path("id") String templateId);

    @DELETE("templates/{id}")
    Call<ResponseBody> deleteTemplate(@Path("id") String templateId);

    @GET("orders/")
    Call<List<Order>> getOrders();

    @GET("orders/{id}")
    Call<Order> getOrders(@Path("id") String orderId);

    @POST("orders/")
    Call<ResponseBody> createOrder(@Body Order order);
}
