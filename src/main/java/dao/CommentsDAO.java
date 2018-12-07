package dao;

import entity.CommentsEntity;
import entity.ProjectsEntity;
import entity.UsersEntity;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

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
        String uuid=UUID.nameUUIDFromBytes((comment.getComment()+comment.getLogin()).getBytes()).toString();
        delete(uuid);

    }


    public Serializable create(CommentsEntity entity) {
        entity.setId(UUID.nameUUIDFromBytes((entity.getComment()+entity.getLogin()).getBytes()).toString());
        return super.create(entity);
    }
}
