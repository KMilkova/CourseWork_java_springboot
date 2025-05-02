package com.example.CourseWork.Dao;

import com.example.CourseWork.Model.Entity.*;
import com.example.CourseWork.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ContractsDaoImpl implements ContractsDao {
    @Override
    public List<Contracts> getAllContracts() {
        List<Contracts> contracts = (List<Contracts>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Contracts").list();
        return contracts;
    }

    @Override
    public List<Contracts> getAllContractsByIdCarOwner(int idCarOwner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("From Contracts as cn left join cn.idRegistration as cr left join cr.idOwner as co where co.idOwner in (select roleId from Authorization where id=:id)");
        query.setParameter("id", idCarOwner);
        List<Contracts> contracts = (List<Contracts>) query.list();
        tx1.commit();
        session.close();
        return contracts;
    }

    @Override
    public Contracts getContractById(int idContract) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Contracts.class, idContract);
    }

    @Override
    public void saveContract(Contracts contract) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(contract);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateContract(int idContract, Contracts newContract) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Contracts contracts = (Contracts) session.get(Contracts.class,idContract);
        contracts.setContractNumber(newContract.getContractNumber());
        contracts.setStartDate(newContract.getStartDate());
        contracts.setEndDate(newContract.getEndDate());
        contracts.setIdStatus(newContract.getIdStatus());
        contracts.setIdManager(newContract.getIdManager());
        contracts.setIdRegistration(newContract.getIdRegistration());
        contracts.setIdSpace(newContract.getIdSpace());
        session.update(contracts);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteContract(int idContract) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("From Contracts where idContract = :id");
        query.setParameter("id", idContract);
        Contracts contract = (Contracts) query.uniqueResult();
        Query query2 = session.createQuery("From Status where idStatus = 2");
        Status status = (Status) query2.uniqueResult();
        contract.setIdStatus(status);
        session.update(contract);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Contractdocument> getAllContractDoc() {
        List<Contractdocument> contracts = (List<Contractdocument>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Contractdocument ").list();
        return contracts;
    }

    @Override
    public void deleteContractDoc(int idDocumentation) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Contractdocument car = (Contractdocument) session.get(Contractdocument.class,idDocumentation);
        Transaction tx1 = session.beginTransaction();
        session.delete(car);
        tx1.commit();
        session.close();
    }

    //МЕСТА
    @Override
    public List<ParkingSpace> getAllParkingSpaces() {
        List<ParkingSpace> spaces = (List<ParkingSpace>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From ParkingSpace").list();
        return spaces;
    }

    @Override
    public ParkingSpace getSpaseById(int idSpace) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ParkingSpace.class, idSpace);

    }

    @Override
    public List<ParkingSpace> getAllFreeParkingSpaces() {
        List<ParkingSpace> spaces = (List<ParkingSpace>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From ParkingSpace where idStatus.idStatus = 4 ").list();
        return spaces;
    }

    //МЕНЕДЖЕР
    @Override
    public List<Manager> getAllManagers() {
        List<Manager> managers = (List<Manager>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Manager").list();
        return managers;    }

    @Override
    public Manager getManagerById(int idManager) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Manager.class, idManager);
    }

    @Override
    public void editManager(int idManager, Manager manager) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Manager manager_ = (Manager) session.get(Manager.class,idManager);
        manager_.setFioManager(manager.getFioManager());
        manager_.setManagerEmail(manager.getManagerEmail());
        manager_.setTelephoneNumber(manager.getTelephoneNumber());
        manager_.setJobTitle(manager.getJobTitle());
        manager_.setIdStatus(manager.getIdStatus());
        session.update(manager_);
        tx1.commit();
        session.close();
    }

}
