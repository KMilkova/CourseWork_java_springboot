package com.example.CourseWork.Utils;

import com.example.CourseWork.Model.Entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Car.class);
                configuration.addAnnotatedClass(CarInformation.class);
                configuration.addAnnotatedClass(CarRegistration.class);
                configuration.addAnnotatedClass(CarOwner.class);
                configuration.addAnnotatedClass(Contracts.class);
                configuration.addAnnotatedClass(Manager.class);
                configuration.addAnnotatedClass(Payment.class);
                configuration.addAnnotatedClass(ParkingSpace.class);
                configuration.addAnnotatedClass(Journal.class);
                configuration.addAnnotatedClass(Authorization.class);
                configuration.addAnnotatedClass(Status.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
