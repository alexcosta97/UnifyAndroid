package io.github.alexcosta97.unify.Models.Response;


import java.util.Date;
import java.util.List;

public class OrderResponse {
    private String _id;
    private LocationResponse location;
    private Date date;
    private SupplierResponse supplier;
    private List<ProductsOrdered> productsOrdered;

    public LocationResponse getLocation() {
        return location;
    }

    public void setLocation(LocationResponse location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SupplierResponse getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierResponse supplier) {
        this.supplier = supplier;
    }

    public List<ProductsOrdered> getProductsOrdered() {
        return productsOrdered;
    }

    public void setProductsOrdered(List<ProductsOrdered> productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    private class ProductsOrdered{
        private ProductResponse product;
        private int quantity;

        public ProductResponse getProduct() {
            return product;
        }

        public void setProduct(ProductResponse product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}