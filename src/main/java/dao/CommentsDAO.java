package dao;

import entity.CommentsEntity;
import entity.ProjectsEntity;
import entity.UsersEntity;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;
import java.util.List;

public class CommentsDAO extends AbstractDao<CommentsEntity,String> {


    @Override
    public List<CommentsEntity> getAll() {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CreditinfoEntity ", CommentsEntity.class).list();
    }

    @Override
    public CommentsEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(CommentsEntity.class, id, LockMode.PESSIMISTIC_READ);
    }


    public void delete(CommentsEntity comment) {
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("delete from CommentsEntity where login = :paramLogin AND projectid = :paramProjId AND comment = :paramComment");
        query.setParameter("paramLogin",comment.getLogin());
        query.setParameter("paramProjId",comment.getProjectid());
        query.setParameter("paramComment", comment.getComment());

    }
}
