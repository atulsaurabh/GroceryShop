package org.groceryshop.model;

import javafx.scene.control.ToggleGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atul_saurabh on 31/10/16.
 */
public class Customer {

    public Customer() {
    }

    public static String getNextAccountNumber() {
        try {
            SessionFactory factory = DatabaseConnection.HibernateUtil.openSessionFactory();
            Session session = factory.openSession();
            session.getTransaction().begin();
            List<org.groceryshop.entity.Customer> list = session.createCriteria(org.groceryshop.entity.Customer.class).list();
            if (!list.isEmpty()) {
                org.groceryshop.entity.Customer c = list.get(list.size() - 1);
                String[] s = c.getAccountnumber().split("-");
                String nextAccountNumber = "cust-" + (Long.parseLong(s[1]) + 1);
                session.close();
                factory.close();
                return nextAccountNumber;
            } else {
                return "cust-1";
            }

        } catch (Exception sqle) {
            Logger.getLogger(Customer.class.getName()).log(
                    Level.SEVERE,
                    "Unable to fetch next available key " + sqle.getMessage(),
                    sqle
            );
        }
        return null;
    }

    public boolean addCustomer(org.groceryshop.entity.Customer customer) {
        try {
            SessionFactory sessionFactory = DatabaseConnection.HibernateUtil.openSessionFactory();
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.persist(customer);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            return true;
        } catch (Exception sqle) {
            Logger.getLogger(Customer.class.getName()).log(
                    Level.SEVERE,
                    "Unable to add new customer " + sqle.getMessage(),
                    sqle
            );
        }

        return false;
    }

    @Nullable
    public List<org.groceryshop.entity.Customer> getAllCustomers(String nameToSearch) {
        ToggleGroup toggleGroup = new ToggleGroup();
        try {
            /*Connection connection = DatabaseConnection.getDatabaseConnection();
            String query = "SELECT * FROM customer where customername like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,"%"+nameToSearch+"%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                final SelectableCustomer customer = new SelectableCustomer();
                customer.setAccountnumber(rs.getString(1));
                customer.setCustomername(rs.getString(2));
                customer.setOccupation(rs.getString(3));
                customer.setAddress(rs.getString(4));
                customer.setMobilenumber(rs.getString(5));
                RadioButton rd = new RadioButton();
                rd.setToggleGroup(toggleGroup);
                rd.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event)
                    {
                       controller.callBack(customer.getAccountnumber(),customer.getCustomername());
                        childWindow.close();
                    }
                });
                customer.setRadioButton(rd);
                Hyperlink hyperlink = new Hyperlink("Details");
                hyperlink.setGraphic(new ImageView(new Image("/org/groceryshop/image/details_1.png")));
                hyperlink.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event)
                    {
                      System.out.println("Customer id = "+customer.getAccountnumber());
                    }
                });
                customer.setDetails(hyperlink);
                customers.add(customer);
            }*/

            SessionFactory factory = DatabaseConnection.HibernateUtil.openSessionFactory();
            Session session = factory.openSession();
            session.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<org.groceryshop.entity.Customer> criteriaQuery = criteriaBuilder.createQuery(org.groceryshop.entity.Customer.class);
            Root<org.groceryshop.entity.Customer> customerRoot = criteriaQuery.from(org.groceryshop.entity.Customer.class);
            criteriaQuery.select(customerRoot);
            criteriaQuery.where(criteriaBuilder.like(customerRoot.get("customername"), nameToSearch + "%"));
            List<org.groceryshop.entity.Customer> customerlist = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();
            return customerlist;
        } catch (Exception sqle) {
            Logger.getLogger(Customer.class.getName()).log(
                    Level.SEVERE,
                    "Unable to fetch customers " + sqle.getMessage(),
                    sqle
            );
        }
        return null;
    }
}
