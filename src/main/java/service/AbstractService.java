package service;
import exception.DBException;




abstract class AbstractService<E>  {

    abstract long create(E item) throws DBException;

    abstract void update(E item) throws DBException;

    abstract void delete(long id) throws DBException;

}