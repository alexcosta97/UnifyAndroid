package io.github.alexcosta97.unify.Services;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.Category;
import io.github.alexcosta97.unify.Models.Database.Company;
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
import io.github.alexcosta97.unify.Models.Response.CategoryResponse;
import io.github.alexcosta97.unify.Models.Response.CompanyResponse;
import io.github.alexcosta97.unify.Models.Response.LocationResponse;
import io.github.alexcosta97.unify.Models.Response.OrderResponse;
import io.github.alexcosta97.unify.Models.Response.ProductResponse;
import io.github.alexcosta97.unify.Models.Response.SubcategoryResponse;
import io.github.alexcosta97.unify.Models.Response.SupplierResponse;
import io.github.alexcosta97.unify.Models.Response.TemplateResponse;
import io.github.alexcosta97.unify.Models.Response.UserResponse;

public class ResponseConverter {
    public static Category responseToModel(CategoryResponse categoryResponse){
        Category category = new Category();
        category.categoryId = categoryResponse.get_id();
        category.categoryName = categoryResponse.getName();
        category.companyId = categoryResponse.getCompany().get_id();

        return category;
    }

    public static Company responseToModel(CompanyResponse companyResponse) {

        Company company = new Company();
        company._id = companyResponse.get_id();
        company.name = companyResponse.getName();
        company.email = companyResponse.getEmail();
        company.phone = companyResponse.getPhone();

        return company;
    }
    public static Location responseToModel(LocationResponse locationResponse){

        Location location = new Location();
        location.locationId = locationResponse.get_id();
        location.locationName = locationResponse.getName();
        location.companyId = locationResponse.getCompany().get_id();
        location.email = locationResponse.getEmail();
        location.address = locationResponse.getAddress();
        location.fax = locationResponse.getFax();

        return location;
    }

    public static Order responseToModel(OrderResponse response){
        Order order = new Order();
        order.orderId = response.get_id();
        order.date = response.getDate();
        order.locationId = response.getLocation().get_id();
        order.supplierId = response.getSupplier().get_id();

        return order;
    }

    public static Product responseToModel(ProductResponse response){
        Product product = new Product();
        product.productId = response.get_id();
        product.productName = response.getName();
        product.price = response.getPrice();
        product.quantityPerItem = response.getQuantity();
        product.supplierReference = response.getSupplierReference();
        product.supplierId = response.getSupplier().get_id();

        return product;
    }

    public static List<productsOrdered> innerResponseToModel(OrderResponse response){
        List<productsOrdered> productsOrdered = new ArrayList<>();

        for(int i = 0; i < response.getProductsOrdered().size(); i++){
            OrderResponse.ProductsOrdered productOrdered = response.getProductsOrdered().get(i);
            productsOrdered product = new productsOrdered();
            product.orderId = response.get_id();
            product.productId = productOrdered.getProduct().get_id();
            product.quantity = productOrdered.getQuantity();

            productsOrdered.add(product);
        }

        return productsOrdered;
    }

    public static Subcategory responseToModel(SubcategoryResponse response){
        Subcategory subcategory = new Subcategory();
        subcategory.subcategoryId = response.get_id();
        subcategory.subcategoryName = response.getName();
        subcategory.categoryId = response.getCategory().get_id();
        subcategory.companyId = response.getCompany().get_id();

        return subcategory;
    }

    public static List<SubcategoryProducts> innerResponseToModel(SubcategoryResponse response){
        List<SubcategoryProducts> products = new ArrayList<>();

        for(int i = 0; i < response.getProducts().size(); i++){
            SubcategoryProducts product = new SubcategoryProducts();
            product.productId = response.getProducts().get(i).get_id();
            product.subcategoryId = response.get_id();

            products.add(product);
        }

        return products;
    }

    public static Supplier responseToModel(SupplierResponse response){
        Supplier supplier = new Supplier();

        supplier.supplierId = response.get_id();
        supplier.supplierName = response.getName();
        supplier.email = response.getEmail();
        supplier.fax = response.getFax();
        supplier.phone = response.getPhone();

        return supplier;
    }

    public static Template responseToModel(TemplateResponse response){
        Template template = new Template();

        template.templateId = response.get_id();
        template.templateName = response.getName();
        template.companyId = response.getCompany().get_id();
        template.locationId = response.getLocation().get_id();

        return template;
    }

    public static List<TemplateOrderDays> innerResponseToModel(TemplateResponse response){
        List<TemplateOrderDays> orderDays = new ArrayList<>();

        for(int i = 0; i < response.getOrderDays().size(); i++){
            TemplateOrderDays orderDay = new TemplateOrderDays();
            orderDay.orderDay = response.getOrderDays().get(i);
            orderDay.templateId = response.get_id();

            orderDays.add(orderDay);
        }

        return orderDays;
    }

    public static List<TemplateSubcategories> innerresponseToModel(TemplateResponse response){
        List<TemplateSubcategories> templateSubcategories = new ArrayList<>();

        for(int i = 0; i < response.getSubcategories().size(); i++){
            TemplateSubcategories subcategories = new TemplateSubcategories();
            subcategories.subcategoryId = response.getSubcategories().get(i).get_id();
            subcategories.templateId = response.get_id();

            templateSubcategories.add(subcategories);
        }

        return templateSubcategories;
    }

    public static User responseToModel(UserResponse response){
        User user = new User();

        user.userId = response.get_id();
        user.companyId = response.getCompany().get_id();
        user.email = response.getEmail();
        user.firstName = response.getFirstName();
        user.lastName = response.getLastName();
        user.role = response.getRole();

        return user;
    }

    public static List<UserLocation> innerResponseToModel(UserResponse response){
        List<UserLocation> userLocations = new ArrayList<>();

        for(int i = 0; i < response.getLocations().size(); i++){
            UserLocation location = new UserLocation();
            location.locationId = response.getLocations().get(i).get_id();
            location.userId = response.get_id();

            userLocations.add(location);
        }

        return userLocations;
    }
}
