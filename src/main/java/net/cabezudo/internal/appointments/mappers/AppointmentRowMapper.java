package net.cabezudo.internal.appointments.mappers;

import net.cabezudo.internal.appointments.persistence.AppointmentEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentRowMapper implements RowMapper<AppointmentEntity> {
  @Override
  public AppointmentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new AppointmentEntity(
        rs.getInt("id"),
        rs.getInt("office_id"),
        rs.getInt("doctor_id"),
        rs.getTimestamp("appointmentDateTime").toLocalDateTime(),
        rs.getString("patient")
    );
  }
}