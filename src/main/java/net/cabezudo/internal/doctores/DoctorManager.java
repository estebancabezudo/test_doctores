package net.cabezudo.internal.doctores;

import lombok.AllArgsConstructor;
import net.cabezudo.internal.doctores.persistence.DoctorEntity;
import net.cabezudo.internal.doctores.persistence.DoctorRepository;
import net.cabezudo.internal.doctores.mappers.DoctorMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import net.cabezudo.internal.persistence.DatabaseManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorManager {
  private final DatabaseManager databaseManager;
  private final DoctorRepository doctorRepository;
  private final DoctorMapper doctorMapper;

  public Doctor findById(int id) {
    JdbcTemplate jdbcTemplate = databaseManager.getJDBCTemplate();
    DoctorEntity doctorEntity = doctorRepository.findById(jdbcTemplate, id);
    return doctorMapper.toBusiness(doctorEntity);
  }

  public List<Doctor> findAll() {
    JdbcTemplate jdbcTemplate = databaseManager.getJDBCTemplate();
    List<DoctorEntity> doctorEntityList = doctorRepository.findAll(jdbcTemplate);
    return doctorMapper.toBusiness(doctorEntityList);
  }
}
