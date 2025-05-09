package net.cabezudo.internal.offices.mappers;

import net.cabezudo.internal.offices.persistence.OfficeEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfficeRowMapper implements RowMapper<OfficeEntity> {
  public OfficeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    int id = rs.getInt("id");
    int number = rs.getInt("number");
    int floor = rs.getInt("floor");
    return new OfficeEntity(id, number, floor);
  }
}
