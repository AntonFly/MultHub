package dao;

import entity.ProjectpostsEntity;
import org.hibernate.LockMode;
import util.DBService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class ProjectspostsDAO extends AbstractDao<ProjectpostsEntity,String> {
    @Override
    public List<ProjectpostsEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsEntity ", ProjectpostsEntity.class).list();
    }

    /**
     *
     * do not use
     */
    @Override
    public ProjectpostsEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(ProjectpostsEntity.class,id,LockMode.PESSIMISTIC_READ);
    }

    public Serializable create(ProjectpostsEntity entity){
        entity.setId( UUID.nameUUIDFromBytes(entity.getText().getBytes()).toString());
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .save(entity);
    }
}
