package org.groceryshop.model;

import org.groceryshop.entity.ItemGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atul_saurabh on 6/11/16.
 */
public class GroupCreatorModel {
    public boolean isGroupExist(String goupname) {
        try {
            SessionFactory factory = DatabaseConnection.HibernateUtil.openSessionFactory();
            Session session = factory.openSession();
            session.getTransaction().begin();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(ItemGroup.class);
            Root<ItemGroup> root = query.from(ItemGroup.class);
            query.select(root);
            query.where(builder.equal(root.get("groupname"), goupname));
            List<ItemGroup> groups = session.createQuery(query).getResultList();
            session.getTransaction().commit();
            session.close();
            factory.close();
            return !groups.isEmpty();
        } catch (Exception e) {
            Logger.getLogger(GroupCreatorModel.class.getName()).log(
                    Level.SEVERE,
                    "Unable to find group name " + e.getMessage(),
                    e
            );
        }
        return false;
    }

    public boolean addGroup(ItemGroup group) {
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

            Logger.getLogger(GroupCreatorModel.class.getName()).log(
                    Level.SEVERE,
                    "Unable to add group " + e.getMessage(),
                    e
            );
        }
        return false;
    }

    public List<String> getAllGroups(String groupName) {
        ArrayList<String> grps = new ArrayList<>();
        try {
            SessionFactory factory = DatabaseConnection.HibernateUtil.openSessionFactory();
            Session session = factory.openSession();
            session.getTransaction().begin();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(ItemGroup.class);
            Root root = query.from(ItemGroup.class);
            query.select(root);
            query.where(builder.like(root.get("groupname"), groupName + "%"));
            List<ItemGroup> groups = session.createQuery(query).getResultList();
            for (ItemGroup g : groups) {
                grps.add(g.getGroupname() + "@" + g.getGroupid());
            }
            return grps;


        } catch (Exception ex) {
            Logger.getLogger(GroupCreatorModel.class.getName()).log(
                    Level.SEVERE,
                    "Unable to fetch groups " + ex.getMessage(),
                    ex
            );
        }
        return grps;
    }


}
