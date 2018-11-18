package dao;

public class DaoFactory {
    private static volatile UsersDAO usersDAO;
    private static volatile CreditInfoDAO creditInfoDAO;


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


}
