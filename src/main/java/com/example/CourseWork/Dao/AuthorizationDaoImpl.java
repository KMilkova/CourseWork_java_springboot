package com.example.CourseWork.Dao;

import com.example.CourseWork.Model.Entity.Authorization;
import com.example.CourseWork.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AuthorizationDaoImpl implements AuthorizationDao{
    @Override
    public Authorization findByLogin(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("From Authorization where username = :login");
        query.setParameter("login", login);
        Authorization user = (Authorization) query.uniqueResult();
        tx1.commit();
        session.close();
        return user;
    }

    @Override
    public Authorization saveAuthorization(Authorization authorization) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(authorization);
        tx1.commit();
        session.close();
        return authorization;
    }
}
