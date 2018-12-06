package dao;

public class DaoFactory {
    private static volatile UsersDAO usersDAO;
    private static volatile CreditInfoDAO creditInfoDAO;
    private static volatile ConnectiondataDao connectiondataDao;
    private static volatile CommitsDao commitsDao;

    public static UsersDAO getUsersDAO(){
        if(usersDAO == null) {
            synchronized(DaoFactory.class) {
                if(usersDAO == null) {
                    usersDAO = new UsersDAO();
                }
            }
        }
        return usersDAO;
    }

    public static CreditInfoDAO getCreditInfoDAO(){
        if(creditInfoDAO == null) {
            synchronized(DaoFactory.class) {
                if(creditInfoDAO == null) {
                    creditInfoDAO = new CreditInfoDAO();
                }
            }
        }
        return creditInfoDAO;
    }

    public static ConnectiondataDao getConnectiondataDao(){
        if(connectiondataDao == null) {
            synchronized(DaoFactory.class) {
                if(connectiondataDao == null) {
                    connectiondataDao = new ConnectiondataDao();
                }
            }
        }
        return connectiondataDao;
    }

    public static CommitsDao getCommitsDao(){
        if(commitsDao == null) {
            synchronized(DaoFactory.class) {
                if(commitsDao == null) {
                    commitsDao = new CommitsDao();
                }
            }
        }
        return commitsDao;
    }

}
