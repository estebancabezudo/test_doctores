package net.cabezudo.internal.doctores.mappers;

import lombok.AllArgsConstructor;
import net.cabezudo.internal.doctores.Doctor;
import net.cabezudo.internal.doctores.Speciality;
import net.cabezudo.internal.doctores.SpecialityManager;
import net.cabezudo.internal.doctores.persistence.DoctorEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DoctorMapper {
  private final SpecialityManager specialityManager;

  public Doctor toBusiness(DoctorEntity doctorEntity) {
    if (doctorEntity == null) {
      return null;
    }

    Speciality speciality = specialityManager.findById(doctorEntity.getSpeciality());

    return new Doctor(
        doctorEntity.getId(),
        doctorEntity.getName(),
        doctorEntity.getLastName(),
        doctorEntity.getMotherLastName(),
        speciality
    );
  }

  public List<Doctor> toBusiness(List<DoctorEntity> doctorEntityList) {
    List<Doctor> list = new ArrayList<>();
    for (DoctorEntity doctorEntity : doctorEntityList) {
      list.add(toBusiness(doctorEntity));
    }
    return list;
  }
}
