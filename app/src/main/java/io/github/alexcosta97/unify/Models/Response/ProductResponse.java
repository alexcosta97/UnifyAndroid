package io.github.alexcosta97.unify.Models.Response;

public class ProductResponse {
    private String _id;
    private String name;
    private double price;
    private String quantity;
    private String supplierReference;
    private SupplierResponse supplier;


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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSupplierReference() {
        return supplierReference;
    }

    public void setSupplierReference(String supplierReference) {
        this.supplierReference = supplierReference;
    }

    public SupplierResponse getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierResponse supplier) {
        this.supplier = supplier;
    }
}
