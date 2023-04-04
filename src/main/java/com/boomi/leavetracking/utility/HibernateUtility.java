package com.boomi.leavetracking.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
    public static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
