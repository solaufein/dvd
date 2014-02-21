package pl.radek.dvd.logic.daomapper;

import org.springframework.jdbc.core.ResultSetExtractor;
import pl.radek.dvd.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 05.02.14
 * Time: 14:22
 * To change this template use File | Settings | File Templates.
 */
public class ClientResultSetExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet resultSet) throws SQLException {
        Client client = new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
        return client;
    }
}
