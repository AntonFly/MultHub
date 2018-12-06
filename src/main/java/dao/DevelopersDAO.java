package dao;

import entity.DevelopersEntity;
import entity.DevelopersEntityPK;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;
import java.util.List;

public class DevelopersDAO extends AbstractDao<DevelopersEntity,String> {
    public DevelopersDAO() {
    }

    @Override
    public List<DevelopersEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from UsersEntity ", DevelopersEntity.class).list();

    }

    @Override
    public DevelopersEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(DevelopersEntity.class, id, LockMode.PESSIMISTIC_READ);
    }

    public List<DevelopersEntity> getEntityById(DevelopersEntityPK key)
    {
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createSQLQuery("select * " /*login, projectid, projpos,description */+ "from developers where login =\'" +key.getLogin()+ /*:paramLogin*/"\' AND projectid =\'"+ key.getProjectid()+"\'"); //:paramProjId");
        //query.setParameter("paramLogin",key.getLogin());
        //query.setParameter("paramProjId",key.getProjectid());
        return query.list();

    }
}
