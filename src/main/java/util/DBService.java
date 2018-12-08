package util;

import exception.ServiceException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import entity.*;
import org.hibernate.resource.transaction.spi.TransactionStatus;


public class DBService {
        private static SessionFactory sessionFactory;

        static {
                sessionFactory = initSessionFactory();
        }

        private DBService() {}

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static Transaction getTransaction() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            transaction = session.beginTransaction();
        }
        return transaction;
    }

    public static void transactionRollback(Transaction transaction){
        if (transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK) {
            transaction.rollback();
        }
    }



    public static SessionFactory initSessionFactory()  {
            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration().configure();
                    addAnnotatedClassToConfiguration(configuration);

                    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                    sessionFactory = configuration.buildSessionFactory(builder.build());

                } catch (Exception e) {
                    System.out.println("Исключение!" + e);
                }
            }
            return sessionFactory;
        }
    private static void addAnnotatedClassToConfiguration(Configuration configuration) {
        configuration.addAnnotatedClass(UsersEntity.class)
            .addAnnotatedClass(ConnectiondataEntity.class)
            .addAnnotatedClass(SubsEntity.class)
            .addAnnotatedClass(CommitsEntity.class)
            .addAnnotatedClass(CommentsEntity.class)
            .addAnnotatedClass(ProjectsEntity.class)
            .addAnnotatedClass(RequestsEntity.class)
            .addAnnotatedClass(DevelopersEntity.class)
            .addAnnotatedClass(CreditinfoEntity.class)
            .addAnnotatedClass(DialogEntity.class)
            .addAnnotatedClass(MessageEntity.class)
            .addAnnotatedClass(DonatersEntity.class)
            .addAnnotatedClass(CommitsfileEntity.class)
            .addAnnotatedClass(FollowersEntity.class)
            .addAnnotatedClass(UserpostEntity.class)
            .addAnnotatedClass(ProjectpostsEntity.class);


    }

    public static void close(){
        sessionFactory.close();
    }

}

