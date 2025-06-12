package com.dinesh.springjdbcdemo.repo;

import com.dinesh.springjdbcdemo.model.Alien;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlienRepo {
    // This class can be used to define methods for database operations related to Alien entities.
    // For example, you can add methods to save, update, delete, or retrieve Alien records from the database.

   private JdbcTemplate template;

  public AlienRepo(JdbcTemplate template) {
      this.template = template;
   }

   public void save(Alien alien){
      String query="insert into aliens(id,name,tech) values(?,?,?)";
        int rows=template.update(query, alien.getId(), alien.getName(), alien.getTech());
        System.out.println(rows + " row(s) inserted.");

   }


}
