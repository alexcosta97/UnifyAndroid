package io.github.alexcosta97.unify.Models.Response;

public class PostCompanyResponse {
    private CompanyResponse company;
    private LocationResponse location;

    public CompanyResponse getCompany() {
        return company;
    }

    public void setCompany(CompanyResponse company) {
        this.company = company;
    }

    public LocationResponse getLocation() {
        return location;
    }

    public void setLocation(LocationResponse location) {
        this.location = location;
    }
}
