package net.cabezudo.internal.doctores.mappers;

import net.cabezudo.internal.doctores.persistence.SpecialityEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialityRowMapper implements RowMapper<SpecialityEntity> {
  public SpecialityEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    return new SpecialityEntity(id, name);
  }
}
