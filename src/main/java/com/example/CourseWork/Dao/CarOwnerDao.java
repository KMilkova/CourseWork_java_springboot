package com.example.CourseWork.Dao;

import com.example.CourseWork.Model.Entity.CarOwner;

import java.util.List;

public interface CarOwnerDao {

    //Действия с ВЛАДЕЛЬЦАМИ: Вывод инфы о владельце по его айди, вывод всех владельцев
    public CarOwner getCarOwnerById(int id);
    public List<CarOwner> getAllCarOwners();
    public CarOwner getLast();
    public void saveCarOwner(CarOwner carOwner);
    public CarOwner getCarOwnerByIdAuthorization(int idAuthorization);

}
