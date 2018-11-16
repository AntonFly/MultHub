package service;

import exception.DBException;
import org.hibernate.Transaction;
import util.DBService;

public class DeveloperService extends AbstractService {
    public DeveloperService() {
    }

    @Override
    long create(Object item) throws DBException {
        Transaction transaction = DBService.getTransaction();
    }

    @Override
    void update(Object item) throws DBException {

    }

    @Override
    void delete(long id) throws DBException {

    }
}
