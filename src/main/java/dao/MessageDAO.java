package dao;

import entity.MassageEntity;
import org.hibernate.LockMode;
import util.DBService;

import java.util.List;

public class MessageDAO extends AbstractDao<MassageEntity,String>{

    @Override
    public List<MassageEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from MassageEntity ", MassageEntity.class).list();
    }

    @Override
    public MassageEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(MassageEntity.class, id, LockMode.PESSIMISTIC_READ);
    }
}
