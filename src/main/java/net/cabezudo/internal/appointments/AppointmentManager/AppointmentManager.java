package net.cabezudo.internal.appointments.AppointmentManager;

import lombok.AllArgsConstructor;
import net.cabezudo.internal.appointments.Appointment;
import net.cabezudo.internal.appointments.mappers.AppointmentMapper;
import net.cabezudo.internal.appointments.persistence.AppointmentEntity;
import net.cabezudo.internal.appointments.persistence.AppointmentRepository;
import net.cabezudo.internal.persistence.DatabaseManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentManager {
  private final DatabaseManager databaseManager;
  private final AppointmentRepository appointmentRepository;
  private final AppointmentMapper appointmentMapper;

  public Appointment save(Appointment appointment) {
    JdbcTemplate jdbcTemplate = databaseManager.getJDBCTemplate();
    AppointmentEntity appointmentEntityToSave = appointmentMapper.toEntity(appointment);
    AppointmentEntity newAppointmentEntity = appointmentRepository.save(jdbcTemplate, appointmentEntityToSave);
    return appointmentMapper.toBusiness(newAppointmentEntity);
  }

  public List<Appointment> search(String date, Integer doctorId, Integer officeId) {
    return new ArrayList<>();
  }

  public void cancelIfFuture(int id) {
  }

  public Appointment findById(int id) {
    return null;
  }

  public void updateWithValidation(Appointment updatedAppointment) {
  }

  public List<Appointment> search(LocalDateTime dateTime, Integer doctorId, Integer officeId) {
    JdbcTemplate jdbcTemplate = databaseManager.getJDBCTemplate();
    List<AppointmentEntity> entityList = appointmentRepository.search(jdbcTemplate, dateTime, doctorId, officeId);
    return appointmentMapper.toBusiness(entityList);
  }
}
