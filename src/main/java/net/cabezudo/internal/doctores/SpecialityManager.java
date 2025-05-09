package net.cabezudo.internal.doctores;

import lombok.AllArgsConstructor;
import net.cabezudo.internal.doctores.persistence.SpecialityEntity;
import net.cabezudo.internal.doctores.persistence.SpecialityRepository;
import net.cabezudo.internal.doctores.mappers.SpecialityMapper;
import net.cabezudo.internal.persistence.DatabaseManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpecialityManager {
  private final DatabaseManager databaseManager;
  private final SpecialityRepository specialityRepository;
  private final SpecialityMapper specialityMapper;

  public Speciality findById(int id) {
    JdbcTemplate jdbcTemplate = databaseManager.getJDBCTemplate();
    SpecialityEntity specialityEntity = specialityRepository.findById(jdbcTemplate, id);
    return specialityMapper.toBusiness(specialityEntity);
  }
}
