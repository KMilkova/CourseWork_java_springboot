package com.example.CourseWork.Dao;

import com.example.CourseWork.Model.Entity.*;
import com.example.CourseWork.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CarDaoImpl implements CarDao {
    @Override
    public Car getCarById(int id_car) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Car.class, id_car);
    }
    @Override
    public List<Car> getAllCars() {
        List<Car> users = (List<Car>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Car").list();
        return users;
    }
    @Override
    public void saveCar(Car car) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(car);
        tx1.commit();
        session.close();
    }

    @Override
    public Car getLast() {
        Car car = (Car) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Car ORDER BY idCar DESC\n" +
                "LIMIT 1").uniqueResult();
        return car;
    }

    //хз сработает или нет
    @Override
    public void updateCar(int id,Car car) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Car car_ = (Car) session.get(Car.class,id);
        car_.setCarNumber(car.getCarNumber());
        car_.setCarColor(car.getCarColor());
        car_.setMileage(car.getMileage());
        car_.setIdInformation(car.getIdInformation());
        car_.setYearOfIssue(car.getYearOfIssue());
        session.update(car_);
        tx1.commit();
        session.close();
    }


    //переделать для оплаты и переместить в другое дао
    @Override
    public void deleteJournalById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Journal car = (Journal) session.get(Journal.class,id);
        Transaction tx1 = session.beginTransaction();
        session.delete(car);
        tx1.commit();
        session.close();
    }

    @Override
    public List<CarRegistration> getAllCarRegistrations() {
        List<CarRegistration> users = (List<CarRegistration>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From CarRegistration").list();
        return users;
    }

    @Override
    public CarRegistration getCarRegistrationById(int idCarInformation) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(CarRegistration.class, idCarInformation);
    }

    @Override
    public List<CarRegistration> getCarRegistrationsByCarOwnerId(int idCarOwner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("From CarRegistration as cr left join cr.idOwner as co left join cr.idCar as c where co.idOwner in (select roleId from Authorization where id = :id)");
        query.setParameter("id", idCarOwner);
        List<CarRegistration> carRegistration = (List<CarRegistration>) query.list();
        tx1.commit();
        session.close();
        return carRegistration;
    }

    @Override
    public void saveCarRegistration(CarRegistration carRegistration) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(carRegistration);
        tx.commit();
        session.close();
    }
    @Override
    public void deleteCarRegistration(int idRegistration) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("From CarRegistration where idRegistration = :id");
        query.setParameter("id", idRegistration);
        CarRegistration carRegistration = (CarRegistration) query.uniqueResult();
        Query query2 = session.createQuery("From Status where idStatus = 2");
        Status status = (Status) query2.uniqueResult();
        carRegistration.setIdStatus(status);
        session.update(carRegistration);
        tx1.commit();
        session.close();
    }

    @Override
    public void editCarRegistration(int idCarRegistration, CarRegistration newCarRegistration) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        CarRegistration carRegistration = (CarRegistration) session.get(CarRegistration.class,idCarRegistration);
        carRegistration.setIdOwner(newCarRegistration.getIdOwner());
        carRegistration.setIdCar(newCarRegistration.getIdCar());
        carRegistration.setStartDate(newCarRegistration.getStartDate());
        carRegistration.setEndDate(newCarRegistration.getEndDate());
        carRegistration.setIdStatus(newCarRegistration.getIdStatus());
        session.update(carRegistration);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Car> showAllCarWithOutReg() {
        List<Car> users = (List<Car>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Car where idCar not in (select idCar.id from CarRegistration)").getResultList();
        return users;
    }
    @Override
    public List<CarInformation> getAllCarInformation() {
        List<CarInformation> carInformationList = (List<CarInformation>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From CarInformation").list();
        return carInformationList;
    }

    @Override
    public CarInformation getCarInformationById(int idCarInformation) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(CarInformation.class, idCarInformation);
    }

    @Override
    public List<Status> getAllStatuses() {
        List<Status> statuses = (List<Status>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Status").list();
        return statuses;
    }

    @Override
    public Status getStatusByID(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Status.class, id);
    }


}
