package dao;

import entity.CommitsfileEntity;
import entity.CommitsfileEntityPK;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;

import java.util.List;

public class CommitsfileDAO extends AbstractDao<CommitsfileEntity,String> {
    @Override
    public List<CommitsfileEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsEntity ", CommitsfileEntity.class).list();
    }

    /**
     *
     * do not use
     */
    @Override
    public CommitsfileEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(CommitsfileEntity.class,id,LockMode.PESSIMISTIC_READ);
    }

    public CommitsfileEntity getEntityById(CommitsfileEntityPK item){
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsfileEntity where  filename = :filenameParam AND commitid = :commitidParam ");
        query.setParameter("filenameParam", item.getFilename());
        query.setParameter("commitidParam",item.getCommitid());

        return (CommitsfileEntity) query.uniqueResult();
    }
}

