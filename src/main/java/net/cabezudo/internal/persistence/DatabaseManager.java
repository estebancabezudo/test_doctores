package net.cabezudo.internal.persistence;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DatabaseManager {
  private DriverManagerDataSource dataSource;
  @Value("${spring.datasource.url:#{null}}")
  private String url;
  @Value("${spring.datasource.username:#{null}}")
  private String username;
  @Value("${spring.datasource.password:#{null}}")
  private String password;

  public JdbcTemplate getJDBCTemplate() {
    if (dataSource == null) {
      dataSource = new DriverManagerDataSource(url, username, password);
    }
    return new JdbcTemplate(dataSource);
  }
}
