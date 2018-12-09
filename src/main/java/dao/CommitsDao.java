package dao;

import entity.Approved;
import entity.CommitsEntity;
import entity.CommitsfileEntity;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class CommitsDao extends AbstractDao<CommitsEntity,String> {
    @Override
    public List<CommitsEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsEntity ", CommitsEntity.class).list();
    }

    /**
     *
     * do not use
     */
    @Override
    public CommitsEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(CommitsEntity.class,id,LockMode.PESSIMISTIC_READ);
    }

    public Serializable create(CommitsEntity entity){
        entity.setId( UUID.nameUUIDFromBytes(   (entity.getDeveloper()+entity.getProjectid()+entity.getTime())   .getBytes()  ).toString() );
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .save(entity);
    }

    public void delete(CommitsEntity commitsEntity){
        commitsEntity.setId(UUID.nameUUIDFromBytes((commitsEntity.getDeveloper()+commitsEntity.getProjectid()+commitsEntity.getTime()).getBytes()).toString());
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("delete from CommitsEntity where id = :paramId");
        query.setParameter("paramId",commitsEntity.getId());
        query.executeUpdate();
    }

    public List<CommitsEntity> getUnchecked(){
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsEntity where approved =:paramApr ");
        query.setParameter("paramApr",Approved.AWAITS);

        return query.getResultList();
    }

}
