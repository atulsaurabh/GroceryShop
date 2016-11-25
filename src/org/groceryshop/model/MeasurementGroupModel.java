package org.groceryshop.model;

import org.groceryshop.entity.SellingUnitGroup;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by atul_saurabh on 22/11/16.
 */
public class MeasurementGroupModel extends Group<SellingUnitGroup> {


    public String[] getAllMeasurementGroup() {
        String[] groupNames;
        int i = 0;
        Session session = DatabaseConnection.HibernateUtil.openSessionFactory().openSession();
        session.getTransaction().begin();
        String hQuery = "FROM org.groceryshop.entity.SellingUnitGroup";
        Query query = session.createQuery(hQuery);
        List<SellingUnitGroup> groups = query.getResultList();
        groupNames = new String[groups.size()];
        for (SellingUnitGroup s : groups) {
            groupNames[i] = s.getGroupname();
            i++;
        }
        session.getTransaction().commit();
        session.close();
        return groupNames;
    }

    public SellingUnitGroup getSellingUnitByName(String name) {
        Session session = DatabaseConnection.HibernateUtil.openSessionFactory().openSession();
        session.getTransaction().begin();
        String hQuery = "FROM org.groceryshop.entity.SellingUnitGroup se where se.groupname = :gname";
        Query query = session.createQuery(hQuery);
        query.setParameter("gname", name);
        SellingUnitGroup grp = (SellingUnitGroup) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return grp;
    }


    public boolean updateGroup(SellingUnitGroup group) {
        try {
            Session session = DatabaseConnection.HibernateUtil.openSessionFactory().openSession();
            session.getTransaction().begin();
            session.merge(group);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
