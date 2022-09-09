package com.example.demo.service;

import com.example.demo.model.ProductDetailInput;
import com.example.demo.model.ProductDetailOutput;
import com.example.demo.model.ProductDetailPojo;

public interface ProductDetail {


    public ProductDetailOutput productDetail_Vx0(
            ProductDetailInput input) throws Exception;


    public ProductDetailOutput productDetail_Vx0_token(
             ProductDetailPojo input) throws Exception;


    public ProductDetailOutput productDetail_VxA(
           ProductDetailInput input) throws Exception;
}