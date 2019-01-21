package io.github.alexcosta97.unify.Models.RequestModels;

import java.util.List;

public class OrderRequest {
    public class productOrdered{
        public String productId;
        public int quantity;
    }

    public String locationId;
    public String supplierId;
    public List<productOrdered> productsOrdered;
}
