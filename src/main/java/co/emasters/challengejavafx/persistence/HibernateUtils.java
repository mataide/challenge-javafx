package co.emasters.challengejavafx.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate utils class for Hibernate session management.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class HibernateUtils {

  private static final SessionFactory sessionFactory = buildSessionFactory();

  /**
   * Builds Hibernate session factory based on hibernate config xml file.
   *
   * @return a <code>SessionFactory</code>
   */
  private static SessionFactory buildSessionFactory() {
    try {
      // Create the ServiceRegistry from hibernate.cfg.xml
      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
          .configure("hibernate.cfg.xml").build();

      // Create a metadata sources using the specified service registry.
      Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

      return metadata.getSessionFactoryBuilder().build();
    } catch (Throwable ex) {

      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  /**
   * Retrieves current session factory.
   *
   * @return a <code>SessionFactory</code>
   */
  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  /**
   * Close caches and connection pools.
   */
  public static void shutdown() {
    getSessionFactory().close();
  }

}
