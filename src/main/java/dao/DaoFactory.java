package dao;

public class DaoFactory {
    private static volatile UsersDAO usersDAO;
    private static volatile CreditInfoDAO creditInfoDAO;
    private static volatile ConnectiondataDao connectiondataDao;
    private static volatile CommitsDao commitsDao;
    private static volatile SubsDAO subsDAO;
    private static volatile RequestsDAO requestsDAO;
    private static volatile ProjectsDAO projectsDAO;
    private static volatile DevelopersDAO developersDAO;
    private static volatile CommentsDAO commentsDAO;

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

    public static RequestsDAO getRequestsDAO(){
        if(requestsDAO == null) {
            synchronized(DaoFactory.class) {
                if(requestsDAO == null) {
                    requestsDAO = new RequestsDAO();
                }
            }
        }
        return requestsDAO;
    }
    public static ProjectsDAO getProjectsDAO(){
        if(projectsDAO == null) {
            synchronized(DaoFactory.class) {
                if(projectsDAO == null) {
                    projectsDAO = new ProjectsDAO();
                }
            }
        }
        return projectsDAO;
    }


    public static DevelopersDAO getDevelopersDAO(){
        if(developersDAO == null) {
            synchronized(DaoFactory.class) {
                if(developersDAO == null) {
                    developersDAO = new DevelopersDAO();
                }
            }
        }
        return developersDAO;
    }
    public static CommentsDAO getCommentsDAO(){
        if(commentsDAO == null) {
            synchronized(DaoFactory.class) {
                if(commentsDAO == null) {
                    commentsDAO = new CommentsDAO();
                }
            }
        }
        return commentsDAO;
    }
    public static SubsDAO getSubsDAO(){
        if(subsDAO == null) {
            synchronized(DaoFactory.class) {
                if(subsDAO == null) {
                    subsDAO = new SubsDAO();
                }
            }
        }
        return subsDAO;
    }


}