package dao;

import java.io.Serializable;
import java.util.List;

abstract class AbstractDao<E> {

        public abstract List<E> getAll();
//        public abstract E getEntityById(K id);
        public abstract void update(E entity);
//        public abstract boolean delete(K id);
        public abstract Serializable create(E entity);

}
