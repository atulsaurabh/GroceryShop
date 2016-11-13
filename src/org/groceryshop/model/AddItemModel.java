package org.groceryshop.model;

import javafx.collections.ObservableList;
import javafx.scene.control.ProgressIndicator;
import org.groceryshop.entity.ItemGroup;
import org.groceryshop.entity.StoreItem;
import org.groceryshop.entity.UnitForSell;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atul_saurabh on 31/10/16.
 */
public class AddItemModel {

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
                i.setGroup(grp);
                grp.getStoreItems().add(i);
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
              Logger logger = Logger.getLogger(AddItemModel.class.getName());
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
        try {
            ArrayList<String> list = new ArrayList<>();
            Connection connection = DatabaseConnection.getDatabaseConnection();
            String query = "SELECT * FROM items where item_name LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, search + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(2) + "@" + resultSet.getString(1));
            }
            return list;
        } catch (SQLException sqle) {
            Logger.getLogger(AddItemModel.class.getName()).log(
                    Level.SEVERE,
                    "Unable to fetch items " + sqle.getMessage(),
                    sqle
            );
        } finally {

            try {
                DatabaseConnection.closeConnection();
            } catch (SQLException sqle) {
                Logger.getLogger(AddItemModel.class.getName()).log(
                        Level.SEVERE,
                        "Unable to close database connection " + sqle.getMessage(),
                        sqle
                );
            }
        }
        return null;
    }


    public boolean createUnit(UnitForSell unitForSell) {
        try {

            SessionFactory factory = DatabaseConnection.HibernateUtil.openSessionFactory();
            Session session = factory.openSession();
            session.getTransaction().begin();
            session.persist(unitForSell);
            session.getTransaction().commit();
            session.close();
            factory.close();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AddItemModel.class.getName()).log(Level.SEVERE, "Unable to add unit for sell " + e.getMessage(), e);
        }
        return false;
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
            Logger.getLogger(AddItemModel.class.getName()).log(Level.SEVERE, "Unable to fetch units " + e.getMessage(), e);
        }
        return null;
    }
}
