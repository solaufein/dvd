package pl.radek.dvd.logic.daomapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 05.02.14
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class ClientRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        ClientResultSetExtractor extractor = new ClientResultSetExtractor();

        return extractor.extractData(resultSet);
    }
}
