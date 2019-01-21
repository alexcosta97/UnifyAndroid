package io.github.alexcosta97.unify.Models.Response;

import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Company;

public class SubcategoryResponse {
    private String _id;
    private String name;
    private CompanyResponse company;
    private CategoryResponse category;
    private List<ProductResponse> products;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyResponse getCompany() {
        return company;
    }

    public void setCompany(CompanyResponse company) {
        this.company = company;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}
