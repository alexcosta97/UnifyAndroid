package io.github.alexcosta97.unify.Services;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.RequestModels.CategoryRequest;
import io.github.alexcosta97.unify.Models.RequestModels.CompanyRequest;
import io.github.alexcosta97.unify.Models.RequestModels.LocationRequest;
import io.github.alexcosta97.unify.Models.RequestModels.LoginDetails;
import io.github.alexcosta97.unify.Models.RequestModels.OrderRequest;
import io.github.alexcosta97.unify.Models.RequestModels.ProductRequest;
import io.github.alexcosta97.unify.Models.RequestModels.SubcategoryRequest;
import io.github.alexcosta97.unify.Models.RequestModels.SupplierRequest;
import io.github.alexcosta97.unify.Models.RequestModels.TemplateRequest;
import io.github.alexcosta97.unify.Models.RequestModels.UserRequest;
import io.github.alexcosta97.unify.Models.Response.CategoryResponse;
import io.github.alexcosta97.unify.Models.Response.CompanyResponse;
import io.github.alexcosta97.unify.Models.Response.LocationResponse;
import io.github.alexcosta97.unify.Models.Response.OrderResponse;
import io.github.alexcosta97.unify.Models.Response.PostCompanyResponse;
import io.github.alexcosta97.unify.Models.Response.ProductResponse;
import io.github.alexcosta97.unify.Models.Response.SubcategoryResponse;
import io.github.alexcosta97.unify.Models.Response.SupplierResponse;
import io.github.alexcosta97.unify.Models.Response.TemplateResponse;
import io.github.alexcosta97.unify.Models.Response.UserResponse;
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
    Call<Authorization> login(@Body LoginDetails loginDetails);

    @GET("companies/{id}")
    Call<CompanyResponse> getCompany(@Path("id") String company);

    @POST("companies/")
    Call<PostCompanyResponse> createCompany(@Body CompanyRequest companyInfo);

    @GET("users/")
    Call<List<UserResponse>> getUsers();

    @GET("users/{id}")
    Call<UserResponse> getUser(@Path("id") String userId);

    @POST("users/")
    Call<UserResponse> createUser(@Body UserRequest user);

    @PUT("users/{id}")
    Call<ResponseBody> updateUser(@Body UserRequest user, @Path("id") String userId);

    @DELETE("users/{id}")
    Call<ResponseBody> deleteUser(@Path("id") String userId);

    @GET("locations/")
    Call<List<LocationResponse>> getLocations();

    @GET("locations/{id}")
    Call<LocationResponse> getLocation(@Path("id") String locationId);

    @POST("locations/")
    Call<LocationResponse> createLocation(@Body LocationRequest location);

    @PUT("locations/{id}")
    Call<ResponseBody> updateLocation(@Body LocationRequest location, @Path("id") String locationId);

    @DELETE("locations/{id}")
    Call<ResponseBody> deleteLocation(@Path("id") String locationId);

    @GET("suppliers/")
    Call<List<SupplierResponse>> getSuppliers();

    @GET("suppliers/{id}")
    Call<SupplierResponse> getSupplier(@Path("id") String supplierId);

    @POST("suppliers/")
    Call<SupplierResponse> createSupplier(@Body SupplierRequest supplier);

    @PUT("suppliers/{id}")
    Call<ResponseBody> updateSupplier(@Body SupplierRequest supplier, @Path("id") String supplierId);

    @DELETE("suppliers/{id}")
    Call<ResponseBody> deleteSupplier(@Path("id") String supplierId);

    @GET("categories/")
    Call<List<CategoryResponse>> getCategories();

    @GET("categories/{id}")
    Call<CategoryResponse> getCategory(@Path("id") String categoryId);

    @POST("categories/")
    Call<CategoryResponse> createCategory(@Body CategoryRequest category);

    @PUT("categories/{id}")
    Call<ResponseBody> updateCategory(@Body CategoryRequest category, @Path("id") String categoryId);

    @DELETE("categories/{id}")
    Call<ResponseBody> deleteCategory(@Path("id") String categoryId);

    @GET("products/")
    Call<List<ProductResponse>> getProducts();

    @GET("products/{id}")
    Call<ProductResponse> getProduct(@Path("id") String productId);

    @POST("products/")
    Call<ProductResponse> createProduct(@Body ProductRequest product);

    @PUT("products/{id}")
    Call<ResponseBody> updateProducts(@Body ProductRequest product, @Path("id") String productId);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") String productId);

    @GET("subcategories/")
    Call<List<SubcategoryResponse>> getSubcategories();

    @GET("subcategories/{id}")
    Call<SubcategoryResponse> getSubcategory(@Path("id") String subcategoryId);

    @POST("subcategories/")
    Call<SubcategoryResponse> createSubcategory(@Body SubcategoryRequest subcategory);

    @PUT("subcategories/{id}")
    Call<ResponseBody> updateSubcategory(@Body SubcategoryRequest subcategory, @Path("id") String subcategoryId);

    @DELETE("subcategories/{id}")
    Call<ResponseBody> deleteSubcategory(@Path("id") String subcategory);

    @GET("templates/")
    Call<List<TemplateResponse>> getTemplates();

    @GET("templates/{id}")
    Call<TemplateResponse> getTemplate(@Path("id") String templateId);

    @POST("templates/")
    Call<List<TemplateResponse>> createTemplate(@Body TemplateRequest template);

    @PUT("templates/{id}")
    Call<ResponseBody> updateTemplate(@Body TemplateRequest template, @Path("id") String templateId);

    @DELETE("templates/{id}")
    Call<ResponseBody> deleteTemplate(@Path("id") String templateId);

    @GET("orders/")
    Call<List<OrderResponse>> getOrders();

    @GET("orders/{id}")
    Call<OrderResponse> getOrders(@Path("id") String orderId);

    @POST("orders/")
    Call<OrderResponse> createOrder(@Body OrderRequest order);
}
