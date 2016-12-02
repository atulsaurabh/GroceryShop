package org.groceryshop.model;

import org.groceryshop.entity.Customer;
import org.groceryshop.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atul_saurabh on 31/10/16.
 */
public class DatabaseConnection {
    private static Connection connection;

    @Nullable
    public static Connection getDatabaseConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String userName = "root";
            String password = "mysql";
            String url = "jdbc:mysql://localhost:3306/grocery";
            connection = DriverManager.getConnection(url, userName, password);
            return connection;
        } catch (SQLException sqle) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(
                    Level.SEVERE,
                    "Unable To Establishe Connection " + sqle.getMessage(),
                    sqle
            );
        } catch (ClassNotFoundException cnfe) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(
                    Level.SEVERE,
                    "Unable To Load Driver Class",
                    cnfe
            );
        }
        return null;
    }


    public static void closeConnection() throws SQLException {
        connection.close();
    }


    public static class HibernateUtil {
        private static SessionFactory sessionFactory;

        public static SessionFactory openSessionFactory() {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/grocery")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.connection.password", "mysql")
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(StoreItem.class);
            configuration.addAnnotatedClass(ItemGroup.class);
            configuration.addAnnotatedClass(UnitForSell.class);
            configuration.addAnnotatedClass(SellingUnitGroup.class);
            configuration.addAnnotatedClass(Supplier.class);
            configuration.addAnnotatedClass(SubCatagory.class);
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(registry);
            return sessionFactory;

        }
    }
}
