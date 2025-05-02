package com.example.CourseWork.Dao;

import com.example.CourseWork.Model.Entity.Contractdocument;
import com.example.CourseWork.Model.Entity.Contracts;
import com.example.CourseWork.Model.Entity.Manager;
import com.example.CourseWork.Model.Entity.ParkingSpace;

import java.util.List;

public interface ContractsDao {
    //Действия с ДОГОВОРАМИ: вывод всех контрактов, вывод контрактов по айди владельца
    public List<Contracts> getAllContracts();
    public List<Contracts> getAllContractsByIdCarOwner(int idCarOwner);
    public Contracts getContractById(int idContract);
    public void saveContract(Contracts contract);
    public void updateContract(int idContract,Contracts newContract);
    public void deleteContract(int idContract);
    public List<Contractdocument> getAllContractDoc();
    public void deleteContractDoc(int idDocumentation);

    //Действия с МАСТАМИ:Вывод всех, вывод места по айди, вывод свободных мест
    public List<ParkingSpace> getAllParkingSpaces();
    public ParkingSpace getSpaseById(int idSpace);
    public List<ParkingSpace> getAllFreeParkingSpaces();

    //Действия с МЕНЕДЖЕРОМ: вывод всех, вывод по айди, редактирование
    public List<Manager> getAllManagers();
    public Manager getManagerById(int idManager);
    public void editManager(int idManager, Manager manager);

}
