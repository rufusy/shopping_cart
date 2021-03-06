package database;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateHelper {
    private static SessionFactory sessionFactory;

    private static SessionFactory createSessionFactory(){

        Configuration configuration = new Configuration();

        //configuration.configure("hibernate.cfg.xml");

        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/shopping_cart?useSSL=false");
        properties.setProperty(Environment.USER, "rufusy");
        properties.setProperty(Environment.PASS, "password1234");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.SHOW_SQL, "true");
        //properties.setProperty(Environment.HBM2DDL_AUTO, "create");
        properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        configuration.setProperties(properties);

        configuration.addAnnotatedClass(models.Address.class);
        configuration.addAnnotatedClass(models.Category.class);
        configuration.addAnnotatedClass(models.Customer.class);
        configuration.addAnnotatedClass(models.Product.class);
        configuration.addAnnotatedClass(models.ProductDiscount.class);
        configuration.addAnnotatedClass(models.ProductImage.class);
        configuration.addAnnotatedClass(models.ProductSpecial.class);
        configuration.addAnnotatedClass(models.Review.class);
        configuration.addAnnotatedClass(models.StockStatus.class);
        configuration.addAnnotatedClass(models.User.class);
        configuration.addAnnotatedClass(models.UserGroup.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;

    }

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null)
            sessionFactory = createSessionFactory();

        return sessionFactory;
    }
}
