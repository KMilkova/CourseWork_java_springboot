package com.example.CourseWork.Dao;

import com.example.CourseWork.Model.Entity.Payment;

import java.util.List;

public interface PaymentDao {

    //Действия с ОПЛАТОЙ:вывод всех, вывод по айді контракта
    public List<Payment> getAllPayments();
    public List<Payment> getPaymentsByIdContract(int idContract);
    public List<Payment> getPaymentsByIdOwner(int idOwner);
    public Payment getPaymentById(int idPayment);
    public void savePayment(Payment payment);
    public void editPayment(int idPayment, Payment payment);
}
