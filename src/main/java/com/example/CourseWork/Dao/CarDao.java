package com.example.CourseWork.Dao;

import com.example.CourseWork.Model.Entity.Car;
import com.example.CourseWork.Model.Entity.CarInformation;
import com.example.CourseWork.Model.Entity.CarRegistration;
import com.example.CourseWork.Model.Entity.Status;

import java.util.List;

public interface CarDao {

    //Действия с МАШИНОЙ: вывод всех, вывод одной по айди, обновление и добавление
    public Car getCarById(int id);
    public List<Car> getAllCars();
    public List<Car> showAllCarWithOutReg();

    public void updateCar(int id,Car car);
    public void saveCar(Car car);
    public Car getLast();

    //удаление записи в журнале действия
    public void deleteJournalById(int id);

    //Действия с РЕГИСТРАЦИЕЙ: Вывод всех, вывод по айди регистрации, вывод по айди владельца автомобиля, добавление
    //новой регистрации, удаление (обновление статуса), добавление и автомобиля и регистрации
    public List<CarRegistration> getAllCarRegistrations();
    public CarRegistration getCarRegistrationById(int idCarInformation);
    public List<CarRegistration> getCarRegistrationsByCarOwnerId(int idCarOwner);
    public void saveCarRegistration(CarRegistration carRegistration);
    public void deleteCarRegistration(int idRegistration);
    public void editCarRegistration(int idCarRegistration, CarRegistration newCarRegistration);

    //Действия с ИНФОРМАЦИЕЙ ОБ АВТОМОБИЛЯХ: Вывод всех, вывод по айди
    public List<CarInformation> getAllCarInformation();
    public CarInformation getCarInformationById(int idCarInformation);

    //Действие со СТАТУСОМ: Вывод всех
    public List<Status> getAllStatuses();
    public Status getStatusByID(int id);
}
