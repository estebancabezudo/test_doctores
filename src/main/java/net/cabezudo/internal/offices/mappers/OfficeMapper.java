package net.cabezudo.internal.offices.mappers;

import lombok.AllArgsConstructor;
import net.cabezudo.internal.offices.Office;
import net.cabezudo.internal.offices.persistence.OfficeEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class OfficeMapper {
  public Office toBusiness(OfficeEntity officeEntity) {
    if (officeEntity == null) {
      return null;
    }
    return new Office(officeEntity.getId(), officeEntity.getNumber(), officeEntity.getFloor());
  }

  public List<Office> toBusiness(List<OfficeEntity> officeEntityList) {
    List<Office> list = new ArrayList<>();
    for (OfficeEntity officeEntity : officeEntityList) {
      list.add(toBusiness(officeEntity));
    }
    return list;
  }
}
