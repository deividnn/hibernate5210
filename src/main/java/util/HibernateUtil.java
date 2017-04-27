/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entidades.Pessoa;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author deividnn
 */
public class HibernateUtil {

     static StandardServiceRegistry registry;
     static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory != null) {
            return sessionFactory;
        }
        return null;
    }

    static {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                properties.put("hibernate.show_sql", "true");
                properties.put("hibernate.hbm2ddl.auto", "update");
                properties.put("hibernate.current_session_context_class", "thread");
                properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");//driver do banco de dados
                properties.put("hibernate.connection.url",
                        "jdbc:postgresql://localhost:5432/hibernate5210?charSet=UTF-8");//url da conexao com o banco de dados
                properties.put("hibernate.connection.username", "deivid");//usuario do banco de dados
                properties.put("hibernate.connection.password", "deivid");//senha do banco de dados

                properties.put("hibernate.connection.provider_class",
                        "org.hibernate.service.jdbc.connections.internal.C3P0ConnectionProvider");
                properties.put("hibernate.c3p0.min_size", "5");
                properties.put("hibernate.c3p0.max_size", "20");
                properties.put("hibernate.c3p0.timeout", "40");
                properties.put("hibernate.c3p0.validate", "true");
                properties.put("hibernate.c3p0.preferredTestQuery", "SELECT 1;");
                properties.put("hibernate.c3p0.acquireRetryAttempts", "5");
                properties.put("hibernate.c3p0.acquireRetryDelay", "200");
                properties.put("hibernate.c3p0.breakAfterAcquireFailure", "true");
                properties.put("hibernate.c3p0.idle_test_period", "10");
                properties.put("hibernate.c3p0.testConnectionOnCheckout", "true");
                properties.put("hibernate.connection.handling_mode", "DELAYED_ACQUISITION_AND_RELEASE_AFTER_TRANSACTION");
                properties.put("hibernate.max_fetch_depth", "5");
                properties.put("hibernate.jdbc.batch_size", "10");

                properties.put("hibernate.cache.use_second_level_cache", "true");
                properties.put("hibernate.cache.use_query_cache", "true");
                properties.put("hibernate.cache.use_structured_entries", "true");
                properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
                properties.put("net.sf.ehcache.configurationResourceName", "/hibernate5210.xml");

                properties.put("hibernate.transaction.auto_close_session", "true");//fechar a transacao automatico
                properties.put("hibernate.auto_close_session", "true");//fechar a sessao automatico
                properties.put("hibernate.connection.autocommit", "false");//fechar a sessao automatico
                properties.put("hibernate.format_sql", "false");//formata os comandos sql     
                properties.put("hibernate.connection.shutdown", "true");
                properties.put("hibernate.service.allow_crawling", "false");

                StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
                standardServiceRegistryBuilder.applySettings(properties);

                registry = standardServiceRegistryBuilder.build();

                MetadataSources metadataSources = new MetadataSources(registry);
                metadataSources.addAnnotatedClass(Pessoa.class);
                sessionFactory = metadataSources.getMetadataBuilder().build().buildSessionFactory();

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }

    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
