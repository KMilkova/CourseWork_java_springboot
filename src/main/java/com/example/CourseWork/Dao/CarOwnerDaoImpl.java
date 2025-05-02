package com.example.CourseWork.Dao;

import com.example.CourseWork.Model.Entity.CarOwner;
import com.example.CourseWork.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CarOwnerDaoImpl implements CarOwnerDao{
    @Override
    public CarOwner getCarOwnerById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(CarOwner.class, id);
    }
    @Override
    public List<CarOwner> getAllCarOwners() {
        List<CarOwner> carOwners = (List<CarOwner>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From CarOwner").list();
        return carOwners;
    }

    @Override
    public CarOwner getLast() {
        CarOwner owner = (CarOwner) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From CarOwner ORDER BY idOwner DESC\n" +
                "LIMIT 1").uniqueResult();
        return owner;
    }

    @Override
    public void saveCarOwner(CarOwner carOwner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(carOwner);
        tx1.commit();
        session.close();
    }

    @Override
    public CarOwner getCarOwnerByIdAuthorization(int idAuthorization) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("From CarOwner where idOwner in (select roleId from Authorization where id = :id)");
        query.setParameter("id", idAuthorization);
        CarOwner carOwner = (CarOwner)  query.uniqueResult();
        tx1.commit();
        session.close();
        return carOwner;
    }



}
