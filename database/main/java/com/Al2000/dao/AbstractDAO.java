package database.main.java.com.Al2000.dao;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDAO<T> {
    protected Connection connection = null;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract Boolean create(T obj) throws SQLException;
    public abstract T read(T id) throws SQLException;
    public abstract Boolean update(T obj) throws SQLException;
    public abstract Boolean delete(T obj) throws SQLException;
}
