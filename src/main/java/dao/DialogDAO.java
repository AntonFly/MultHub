package dao;

import entity.DialogEntity;
import org.hibernate.LockMode;
import util.DBService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class DialogDAO extends AbstractDao<DialogEntity,String>  {
    public DialogDAO(){}

    @Override
    public List<DialogEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CreditinfoEntity ", DialogEntity.class).list();
    }

    @Override
    public DialogEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(DialogEntity.class, id, LockMode.PESSIMISTIC_READ);
    }

    public Serializable create(DialogEntity entity){
        entity.setId(UUID.nameUUIDFromBytes((entity.getOneUserId()+entity.getTwoUserId()).getBytes()).toString());
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .save(entity);
    }
}
