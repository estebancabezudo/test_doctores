package net.cabezudo.internal.appointments.mappers;

import lombok.AllArgsConstructor;
import net.cabezudo.internal.appointments.Appointment;
import net.cabezudo.internal.appointments.persistence.AppointmentEntity;
import net.cabezudo.internal.doctores.Doctor;
import net.cabezudo.internal.doctores.DoctorManager;
import net.cabezudo.internal.offices.Office;
import net.cabezudo.internal.offices.OfficeManager;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AppointmentMapper {
  private final OfficeManager officeManager;
  private final DoctorManager doctorManager;

  public Appointment toBusiness(AppointmentEntity appointmentEntity) {
    Integer id = appointmentEntity.getId();
    Office office = officeManager.findById(appointmentEntity.getOffice());
    Doctor doctor = doctorManager.findById(appointmentEntity.getDoctor());
    LocalDateTime dateTime = appointmentEntity.getDateTime();
    String patient = appointmentEntity.getPatient();
    return new Appointment(id, office, doctor, dateTime, patient);
  }

  public AppointmentEntity toEntity(Appointment appointment) {
    Integer id = appointment.getId();
    Integer officeId = appointment.getOffice().getId();
    Integer doctorId = appointment.getDoctor().getId();
    LocalDateTime dateTime = appointment.getDateTime();
    String patient = appointment.getPatient();
    return new AppointmentEntity(id, officeId, doctorId, dateTime, patient);
  }

  public List<Appointment> toBusiness(List<AppointmentEntity> appointmentEntityList) {
      List<Appointment> list = new ArrayList<>();
      for (AppointmentEntity appointmentEntity : appointmentEntityList) {
        list.add(toBusiness(appointmentEntity));
      }
      return list;
    }
}
