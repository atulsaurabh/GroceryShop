package org.groceryshop.model;

import org.groceryshop.entity.Supplier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by bipul on 1/12/16.
 */
public class SupplierModel {
    public boolean createSupplier(Supplier supplier) {
        SessionFactory factory = DatabaseConnection.HibernateUtil.openSessionFactory();
        Session session = factory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM org.groceryshop.entity.Supplier s WHERE s.suppliername = :sname AND s.mobilenumber = :mob");
        query.setParameter("sname", supplier.getSuppliername());
        query.setParameter("mob", supplier.getMobilenumber());
        try {
            Supplier supplier1 = (Supplier) query.getSingleResult();
            return false;
        } catch (NoResultException no) {
            session.persist(supplier);
        }
        session.getTransaction().commit();
        session.close();
        factory.close();
        return true;
    }


}
