package com.example.demo.service;

import com.example.demo.model.ChangePaymentMethodInput;
import com.example.demo.model.ChangePaymentMethodOutput;

public interface ChangePayment {

    public ChangePaymentMethodOutput changePaymentMethod_VxA(
             ChangePaymentMethodInput input) throws Exception;


    public ChangePaymentMethodOutput changePaymentMethod_test(
            ChangePaymentMethodInput input) throws Exception;


    public ChangePaymentMethodOutput changePaymentMethod_Vx0(
           ChangePaymentMethodInput input) throws Exception ;

}