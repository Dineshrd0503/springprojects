package com.dinesh.springjdbcdemo.repo;

              import com.dinesh.springjdbcdemo.model.Alien;
              import org.springframework.jdbc.core.JdbcTemplate;
              import org.springframework.jdbc.core.RowMapper;
              import org.springframework.stereotype.Repository;

              import java.sql.ResultSet;
              import java.sql.SQLException;
              import java.util.List;

              @Repository
              public class AlienRepo {

                  private JdbcTemplate template;

                  public AlienRepo(JdbcTemplate template) {
                      this.template = template;
                  }

                  public void save(Alien alien){
                      String query="insert into aliens(id,name,tech) values(?,?,?)";
                      int rows=template.update(query, alien.getId(), alien.getName(), alien.getTech());
                      System.out.println(rows + " row(s) inserted.");
                  }



                  public List<Alien> displayAll(){
                      String sql="select * from aliens";
                      RowMapper<Alien> mapper=new RowMapper<Alien>() {

                          @Override
                          public Alien mapRow(ResultSet rs, int rowNum) throws SQLException {
                              Alien alien = new Alien();
                              alien.setId(rs.getInt("id"));
                              alien.setName(rs.getString("name"));
                              alien.setTech(rs.getString("tech"));
                              return alien;
                          }
                      };
                      return template.query(sql, mapper);
                  }
              }