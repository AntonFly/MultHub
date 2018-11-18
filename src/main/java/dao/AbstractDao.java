package dao;

import util.DBService;

import java.io.Serializable;
import java.util.List;

abstract class AbstractDao<E, K> {

        public abstract List<E> getAll();

        public abstract E getEntityById(K id);

        public  void update(E entity){
                DBService.getSessionFactory()
                        .getCurrentSession()
                        .update(entity);
        };

        public  void delete(E entity){
                DBService.getSessionFactory()
                        .getCurrentSession()
                        .delete(entity);
        };

        public  Serializable create(E entity){
                return  DBService.getSessionFactory()
                        .getCurrentSession()
                        .save(entity);
        };


}
