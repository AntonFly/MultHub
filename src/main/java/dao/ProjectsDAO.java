package dao;

import entity.ProjectsEntity;
import org.hibernate.LockMode;
import util.DBService;

import java.util.List;

public class ProjectsDAO extends AbstractDao<ProjectsEntity,Integer> {
    public ProjectsDAO(){}

    @Override
    public List<ProjectsEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CreditinfoEntity ", ProjectsEntity.class).list();
    }

    @Override
    public ProjectsEntity getEntityById(Integer id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(ProjectsEntity.class, id, LockMode.PESSIMISTIC_READ);
    }

}

