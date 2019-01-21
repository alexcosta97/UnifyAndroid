package io.github.alexcosta97.unify.Models.Response;

import java.util.Date;
import java.util.List;

public class TemplateResponse {
    private String _id;
    private String name;
    private LocationResponse location;
    private CompanyResponse company;
    private List<SubcategoryResponse> subcategories;
    private List<Date> orderDays;

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

    public LocationResponse getLocation() {
        return location;
    }

    public void setLocation(LocationResponse location) {
        this.location = location;
    }

    public CompanyResponse getCompany() {
        return company;
    }

    public void setCompany(CompanyResponse company) {
        this.company = company;
    }

    public List<SubcategoryResponse> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryResponse> subcategories) {
        this.subcategories = subcategories;
    }

    public List<Date> getOrderDays() {
        return orderDays;
    }

    public void setOrderDays(List<Date> orderDays) {
        this.orderDays = orderDays;
    }
}
