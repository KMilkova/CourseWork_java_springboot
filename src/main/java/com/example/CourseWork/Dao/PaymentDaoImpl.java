package com.example.CourseWork.Dao;

import com.example.CourseWork.Model.Entity.Payment;
import com.example.CourseWork.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PaymentDaoImpl implements PaymentDao{
    @Override
    public List<Payment> getAllPayments() {
        List<Payment> payments = (List<Payment>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Payment").list();
        return payments;
    }

    @Override
    public List<Payment> getPaymentsByIdContract(int idContract) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("From Payment as p left join p.idContract as c  where c.idContract = :id");
        query.setParameter("id", idContract);
        List<Payment> payments = (List<Payment>) query.list();
        tx1.commit();
        session.close();
        return payments;
    }

    @Override
    public List<Payment> getPaymentsByIdOwner(int idOwner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("From Payment as p left join p.idContract as c left join c.idRegistration as cr left join cr.idOwner as o where o.idOwner in (select roleId from Authorization where id =:id)");
        query.setParameter("id", idOwner);
        List<Payment> payments = (List<Payment>) query.list();
        tx1.commit();
        session.close();
        return payments;
    }

    @Override
    public Payment getPaymentById(int idPayment) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Payment.class, idPayment);
    }

    @Override
    public void savePayment(Payment payment) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(payment);
        tx1.commit();
        session.close();
    }

    @Override
    public void editPayment(int idPayment, Payment payment) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Payment newPayment = (Payment) session.get(Payment.class, idPayment);
        newPayment.setIdContract(payment.getIdContract());
        newPayment.setPaymentAmount(payment.getPaymentAmount());
        newPayment.setDateOfPayment(payment.getDateOfPayment());
        session.update(newPayment);
        tx1.commit();
        session.close();
    }


}
