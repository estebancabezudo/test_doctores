package net.cabezudo.internal.appointments;

import lombok.Getter;
import lombok.ToString;
import net.cabezudo.internal.doctores.Doctor;
import net.cabezudo.internal.offices.Office;

import java.time.LocalDateTime;

@Getter
@ToString
public class Appointment {
  private final Integer id;
  private final Office office;
  private final Doctor doctor;
  private final LocalDateTime dateTime;
  private final String patient;

  // Sé que puede hacer esto Lombok, pero no me gusta el riesgo de que
  // si cambia el orden de las propiedades cambie el orden del constructor
  public Appointment(Integer id, Office office, Doctor doctor, LocalDateTime dateTime, String patient) {
    this.id = id;
    this.office = office;
    this.doctor = doctor;
    this.dateTime = dateTime;
    this.patient = patient;
  }
}
