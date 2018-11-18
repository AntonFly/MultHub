import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import entity.UsersEntity;
import service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String... args) throws SQLException {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("3d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        UserService ds= new UserService();
        try{
            ds.create(usersEntity);
            List<UsersEntity> list =ds.getAll();
            for (UsersEntity us:list
                 ) {
                System.out.println(us.getLogin()+" "+us.getName()+" "+ us.getSurname());

            }
//            ds.create(usersEntity);
            UsersEntity us= ds.get("1d");
            System.out.println(us.getLogin()+" "+us.getName()+" "+ us.getSurname());
        }catch (Exception e){
            e.printStackTrace();
        }






    }
    public static void connectBd()
    {
        try
        {
            String strSshUser = "s241837";                  // SSH loging username
            String strSshPassword = "hgd320";                   // SSH login password
            String strSshHost = "helios.cs.ifmo.ru";          // hostname or ip or SSH server
            int nSshPort = 2222;                                    // remote SSH host port number
            String strRemoteHost = "pg";  // hostname or ip of your database server
            int nLocalPort = 63333;                                // local port number use to bind SSH tunnel
            int nRemotePort = 5432;                               // remote port number of your database
            String strDbUser = "s241837";                    // database loging username
            String strDbPassword = "hgd320";                    // database login password

            doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort, nRemotePort);

            Class.forName("org.postgresql.Driver");



        }
        catch( Exception e )
        {
            e.printStackTrace();
        }


    }
     static void doSshTunnel(String strSshUser, String strSshPassword, String strSshHost, int nSshPort, String strRemoteHost, int nLocalPort, int nRemotePort) throws JSchException
    {
        final JSch jsch = new JSch();
        Session session = jsch.getSession( strSshUser, strSshHost, 2222 );
        session.setPassword( strSshPassword );

        final Properties config = new Properties();
        config.put( "StrictHostKeyChecking", "no" );
        session.setConfig( config );

        session.connect();
        System.out.println("tonnel created");
        session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
    }
}
