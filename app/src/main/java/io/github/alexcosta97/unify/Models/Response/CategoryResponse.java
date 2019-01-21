package io.github.alexcosta97.unify.Models.Response;

public class CategoryResponse {
    private String _id;
    private String name;
    private CompanyResponse company;

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
}
