package service;
import exception.DBException;

import java.util.List;


abstract class AbstractService<E>  {

    abstract boolean create(E item) throws DBException;

    abstract boolean update(E item) throws DBException;

    abstract void delete(long id) throws DBException;

    abstract List<E> getAll() throws DBException;

}