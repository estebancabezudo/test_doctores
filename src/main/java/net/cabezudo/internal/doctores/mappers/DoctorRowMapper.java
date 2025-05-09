package net.cabezudo.internal.doctores.mappers;

import net.cabezudo.internal.doctores.persistence.DoctorEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorRowMapper  implements RowMapper<DoctorEntity> {
  public DoctorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    String lastName = rs.getString("lastName");
    String motherLastName = rs.getString("motherLastName");
    int speciality = rs.getInt("speciality_id");
    return new DoctorEntity(id, name, lastName, motherLastName, speciality);
  }
}
