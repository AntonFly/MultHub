package dao;

import java.io.Serializable;
import java.util.List;

abstract class AbstractDao<E> {

        public abstract List<E> getAll();
        public abstract E getEntityById(String id);
        public abstract void update(E entity);
        public abstract void delete(E entity);
        public abstract Serializable create(E entity);

}
