package dao;

import entity.ProjectpostsEntity;
import org.hibernate.LockMode;
import util.DBService;

import java.util.List;

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
}
