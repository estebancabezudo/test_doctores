package net.cabezudo.internal.offices;

import lombok.AllArgsConstructor;
import net.cabezudo.internal.offices.mappers.OfficeMapper;
import net.cabezudo.internal.offices.persistence.OfficeEntity;
import net.cabezudo.internal.offices.persistence.OfficeRepository;
import net.cabezudo.internal.persistence.DatabaseManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfficeManager {
  private final DatabaseManager databaseManager;
  private final OfficeRepository officeRepository;
  private final OfficeMapper officeMapper;

  public Office findById(int id) {
    // Creo el jdbcTemplate en el manager para poder utilizar el mismo con diferentes llamadas a repositorios
    JdbcTemplate jdbcTemplate = databaseManager.getJDBCTemplate();
    OfficeEntity officeEntity = officeRepository.findById(jdbcTemplate, id);
    return officeMapper.toBusiness(officeEntity);
  }

  public List<Office> findAll() {
    JdbcTemplate jdbcTemplate = databaseManager.getJDBCTemplate();
    List<OfficeEntity> officeEntityList = officeRepository.findAll(jdbcTemplate);
    return officeMapper.toBusiness(officeEntityList);
  }
}
