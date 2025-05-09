package net.cabezudo.internal.doctores.mappers;

import lombok.AllArgsConstructor;
import net.cabezudo.internal.doctores.Speciality;
import net.cabezudo.internal.doctores.persistence.SpecialityEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SpecialityMapper {
  public Speciality toBusiness(SpecialityEntity specialityEntity) {
    if (specialityEntity == null) {
      return null;
    }
    return new Speciality(specialityEntity.getId(), specialityEntity.getName());
  }
}
