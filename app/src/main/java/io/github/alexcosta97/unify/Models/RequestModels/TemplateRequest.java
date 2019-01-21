package io.github.alexcosta97.unify.Models.RequestModels;

import java.util.Date;
import java.util.List;

public class TemplateRequest {
    public String name;
    public List<String> locations;
    public List<String> subcategories;
    public List<Date> orderDays;
}
