package dao;

import entity.MessageEntity;
import org.hibernate.LockMode;
import util.DBService;

import java.util.List;

public class MessageDAO extends AbstractDao<MessageEntity,String>{

    @Override
    public List<MessageEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from MessageEntity ", MessageEntity.class).list();
    }

    @Override
    public MessageEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(MessageEntity.class, id, LockMode.PESSIMISTIC_READ);
    }
}
