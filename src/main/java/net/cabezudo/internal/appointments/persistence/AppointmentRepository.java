package net.cabezudo.internal.appointments.persistence;

import net.cabezudo.internal.appointments.mappers.AppointmentRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentRepository {
  private boolean existsAppointmentAtOffice(JdbcTemplate jdbcTemplate, int officeId, LocalDateTime dateTime) {
    String query = "SELECT COUNT(*) FROM appointments WHERE office_id = ? AND appointmentDateTime = ?";
    return jdbcTemplate.queryForObject(query, Integer.class, officeId, Timestamp.valueOf(dateTime)) > 0;
  }

  private boolean existsAppointmentWithDoctorAt(JdbcTemplate jdbcTemplate, int doctorId, LocalDateTime dateTime) {
    String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointmentDateTime = ?";
    return jdbcTemplate.queryForObject(query, Integer.class, doctorId, Timestamp.valueOf(dateTime)) > 0;
  }

  private boolean existsConflictingPatientAppointment(JdbcTemplate jdbcTemplate, String patient, LocalDateTime dateTime) {
    String query = """
        SELECT COUNT(*) FROM appointments
        WHERE patient = ? AND DATE(appointmentDateTime) = ?
        AND ABS(TIMESTAMPDIFF(MINUTE, appointmentDateTime, ?)) < 120
    """;
    return jdbcTemplate.queryForObject(query, Integer.class, patient, dateTime.toLocalDate(), Timestamp.valueOf(dateTime)) > 0;
  }

  private int countAppointmentsForDoctorThatDay(JdbcTemplate jdbcTemplate, int doctorId, LocalDateTime dateTime) {
    String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND DATE(appointmentDateTime) = ?";
    return jdbcTemplate.queryForObject(query, Integer.class, doctorId, dateTime);
  }

  public AppointmentEntity save(JdbcTemplate jdbcTemplate, AppointmentEntity appointmentEntity) {
    Integer officeId = appointmentEntity.getOffice();
    Integer doctorId = appointmentEntity.getDoctor();
    LocalDateTime dateTime = appointmentEntity.getDateTime();
    String patient = appointmentEntity.getPatient();

    if (existsAppointmentAtOffice(jdbcTemplate, officeId, dateTime)) {
      throw new IllegalArgumentException("Ya hay una cita en ese consultorio a esa hora.");
    }

    if (existsAppointmentWithDoctorAt(jdbcTemplate, doctorId, dateTime)) {
      throw new IllegalArgumentException("El doctor ya tiene una cita a esa hora.");
    }

    if (existsConflictingPatientAppointment(jdbcTemplate, patient, dateTime)) {
      throw new IllegalArgumentException("El paciente ya tiene una cita cerca de esa hora el mismo día.");
    }

    if (countAppointmentsForDoctorThatDay(jdbcTemplate, doctorId, dateTime) >= 8) {
      throw new IllegalArgumentException("El doctor ya tiene el máximo de 8 citas ese día.");
    }

    String sqlQuery = "INSERT INTO appointments (office_id, doctor_id, appointmentDateTime, patient) VALUES (?, ?, ?, ?)";
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sqlQuery, new String[]{"id"});
      ps.setInt(1, officeId);
      ps.setInt(2, doctorId);
      ps.setTimestamp(3, Timestamp.valueOf(dateTime));
      ps.setString(4, patient);
      return ps;
    }, keyHolder);
    Number key = keyHolder.getKey();
    if (key == null) {
      throw new IllegalStateException("No se generó ninguna clave para la entidad AppointmentEntity.");
    }
    int id = key.intValue();
    return new AppointmentEntity(id, officeId, doctorId, dateTime, patient);
  }

  public List<AppointmentEntity> search(JdbcTemplate jdbcTemplate, LocalDateTime dateTime, Integer doctorId, Integer officeId) {
    StringBuilder query = new StringBuilder("SELECT * FROM appointments WHERE 1=1");
    List<Object> params = new ArrayList<>();
    if (dateTime != null) {
      query.append(" AND appointmentDateTime = ?");
      params.add(Timestamp.valueOf(dateTime));
    }
    if (doctorId != null) {
      query.append(" AND doctor_id = ?");
      params.add(doctorId);
    }
    if (officeId != null) {
      query.append(" AND office_id = ?");
      params.add(officeId);
    }
    query.append(" ORDER BY appointmentDateTime");
    return jdbcTemplate.query(query.toString(), params.toArray(), new AppointmentRowMapper());
  }
}
