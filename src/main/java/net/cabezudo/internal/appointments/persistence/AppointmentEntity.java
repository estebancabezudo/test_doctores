package net.cabezudo.internal.appointments.persistence;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
public class AppointmentEntity {
  private final Integer id;
  private final Integer office;
  private final Integer doctor;
  private final LocalDateTime dateTime;
  private final String patient;

  public AppointmentEntity(Integer id, Integer office, Integer doctor, LocalDateTime dateTime, String patient) {
    this.id = id;
    this.office = office;
    this.doctor = doctor;
    this.dateTime = dateTime;
    this.patient = patient;
  }
}
