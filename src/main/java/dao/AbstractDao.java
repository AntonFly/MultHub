package dao;

import java.util.List;

abstract class AbstractDao<E,K> {

        public abstract List<E> getAll();
        public abstract E getEntityById(K id);
        public abstract E update(E entity);
        public abstract boolean delete(K id);
        public abstract boolean create(E entity);

}
