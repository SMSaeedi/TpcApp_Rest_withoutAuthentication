package com.example.demo.model;

import java.util.List;

public class NewProductsOutput {

    List<NewProductModel> newProductsList;

    public NewProductsOutput() {
        super();
    }

    public List<NewProductModel> getNewProductsList() {
        return newProductsList;
    }

    public void setNewProductsList(List<NewProductModel> newProductsList) {
        this.newProductsList = newProductsList;
    }
}
