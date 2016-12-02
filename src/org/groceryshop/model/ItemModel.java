package org.groceryshop.model;

import javafx.collections.ObservableList;
import javafx.scene.control.ProgressIndicator;
import org.groceryshop.entity.ItemGroup;
import org.groceryshop.entity.SellingUnitGroup;
import org.groceryshop.entity.StoreItem;
import org.groceryshop.entity.UnitForSell;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by atul_saurabh on 31/10/16.
 */
public class ItemModel extends Group<UnitForSell> {

    public boolean addNewItem(ObservableList<StoreItem> items, ProgressIndicator batch, ArrayList<Long> ids) {
        try {
            SessionFactory factory = DatabaseConnection.HibernateUtil.openSessionFactory();
            Session session = factory.openSession();
            int batchsize = items.size();
            double batchFraction = 100d / batchsize;
            double t = 0;
            session.getTransaction().begin();
            Iterator<Long> longIterator = ids.iterator();
            for (StoreItem i : items) {
                ItemGroup grp = session.find(ItemGroup.class, longIterator.next());
                //i.setGroup(grp);
                //grp.getStoreItems().add(i);
                session.merge(grp);
                t = t + batchFraction;
                batch.setProgress(t);

            }
            session.getTransaction().commit();
            session.close();
            factory.close();
            return true;
        } catch (Exception sqle) {

            sqle.printStackTrace();
            batch.setProgress(0);

         /* try {
              Logger logger = Logger.getLogger(ItemModel.class.getName());
              System.out.println(sqle.getMessage());
              FileHandler fileHandler = new FileHandler(
                      getClass().getResource("/org/groceryshop/log/databaselog.log").getPath()
                      ,true);
              logger.setUseParentHandlers(false);
              logger.addHandler(fileHandler);
              logger.log(Level.SEVERE,"Unable To insert items "+sqle.getMessage(),sqle);
          }
          catch (IOException ioe)
          {
              ioe.printStackTrace();
          }*/
        }

        return false;
    }


    public List<String> getItems(String search) {
        ArrayList<String> nameAndIds = new ArrayList<>();
        SessionFactory factory = DatabaseConnection.HibernateUtil.openSessionFactory();
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = factory.getCriteriaBuilder();
            CriteriaQuery<StoreItem> query = builder.createQuery(StoreItem.class);
            Root<StoreItem> root = query.from(StoreItem.class);
            query.select(root);
            query.where(builder.like(root.get("itemname"), search + "%"));
            List<StoreItem> storeItems = session.createQuery(query).list();

            for (StoreItem s : storeItems) {
                nameAndIds.add(s.getItemname() + "@" + s.getItemid());
            }

            return nameAndIds;

        } catch (Exception sqle) {
            Logger.getLogger(ItemModel.class.getName()).log(
                    Level.SEVERE,
                    "Unable to fetch items " + sqle.getMessage(),
                    sqle
            );
        } finally {

            try {
                session.close();
                factory.close();
            } catch (Exception sqle) {
                Logger.getLogger(ItemModel.class.getName()).log(
                        Level.SEVERE,
                        "Unable to close database connection " + sqle.getMessage(),
                        sqle
                );
            }
        }
        return null;
    }

    public List<UnitForSell> getAllUnit(String unitName) {
        try {
            SessionFactory factory = DatabaseConnection.HibernateUtil.openSessionFactory();
            Session session = factory.openSession();
            CriteriaBuilder builder = factory.getCriteriaBuilder();
            CriteriaQuery<UnitForSell> query = builder.createQuery(UnitForSell.class);
            Root<UnitForSell> root = query.from(UnitForSell.class);
            query.select(root);
            query.where(builder.like(root.get("unitName"), unitName + "%"));
            List<UnitForSell> units = session.createQuery(query).getResultList();
            session.close();
            factory.close();
            return units;
        } catch (Exception e) {
            Logger l = Logger.getLogger(ItemModel.class.getName()); //
            try {
                SimpleFormatter formatter = new SimpleFormatter();

                FileHandler handler = new FileHandler(ItemModel.class.getResource("/org/groceryshop/log/database.log").getPath(), true);
                handler.setFormatter(formatter);
                l.addHandler(handler);

                l.setUseParentHandlers(true);
                l.log(Level.SEVERE, "Unable to fetch units " + e.getMessage(), e);

            } catch (IOException io) {
                io.printStackTrace();
            }


        }
        return null;
    }

    public List<SellingUnitGroup> getAllItemGroup() {
        SessionFactory sessionFactory = DatabaseConnection.HibernateUtil.openSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM org.groceryshop.entity.SellingUnitGroup");
        List<SellingUnitGroup> groups = query.getResultList();
        session.close();
        sessionFactory.close();
        return groups;

    }

    public List<StoreItem> getAllItems() {
        SessionFactory sessionFactory = DatabaseConnection.HibernateUtil.openSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM org.groceryshop.entity.StoreItem");
        List<StoreItem> storeItems = query.getResultList();
        session.close();
        sessionFactory.close();
        return storeItems;
    }

}
