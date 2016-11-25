package org.groceryshop.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atul_saurabh on 22/11/16.
 */
public abstract class Group<T> {
    public boolean addGroup(T group) {
        try {
            SessionFactory factory = DatabaseConnection.HibernateUtil.openSessionFactory();
            Session session = factory.openSession();
            session.getTransaction().begin();
            session.persist(group);
            session.getTransaction().commit();
            session.close();
            factory.close();
            return true;
        } catch (Exception e) {

            Logger.getLogger(Group.class.getName()).log(
                    Level.SEVERE,
                    "Unable to add group " + e.getMessage(),
                    e
            );
        }
        return false;
    }
}
