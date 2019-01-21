package io.github.alexcosta97.unify.Models.Response;

import io.github.alexcosta97.unify.Models.Database.Company;

public class CompanyResponse {
    private Company company;
    private LocationResponse location;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LocationResponse getLocation() {
        return location;
    }

    public void setLocation(LocationResponse location) {
        this.location = location;
    }
}
