package ru.nw.vir.dao;

import ru.nw.vir.model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
//import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Optional;
//http://docs.spring.io/spring/docs/4.3.x/spring-framework-reference/html/jdbc.html
//http://spring-projects.ru/guides/relational-data-access/
@Repository
public class FilesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

	// Find all accounts, thanks Java 8, you can create a custom RowMapper like this :
    public List<Files> findAll() {

        List<Files> result = jdbcTemplate.query("SELECT id,name,date_upload,owner_id,owner,comment FROM files"
				,
                (rs, rowNum) -> new Files(rs.getInt("id"),
                        rs.getString("name"),
						rs.getDate("date_upload"),
						rs.getInt("owner_id"),
						rs.getString("owner"),
						rs.getString("comment")
						)
        );
        return result;   }


	// добавить обработку EmptyResultDataAccessException ???
	public List<Files> findByOwner(String own) { 
        List<Files> result = jdbcTemplate.query("SELECT id,name,date_upload,owner_id,owner,comment FROM files where owner = ?"
				,
				new Object[]{own},
				new RowMapper<Files>() {
					public Files mapRow (ResultSet rs, int rowNum) throws SQLException{
						Files files = new Files(
						rs.getInt("id"),
                        rs.getString("name"),
						//rs.getDate("date_upload"),
						rs.getTimestamp("date_upload"),
						
						rs.getInt("owner_id"),
						rs.getString("owner"),
						rs.getString("comment")
						);
						return files;
					}
				}		
        );
        return result;   		
	}
	
	// queryForObject
	// добавить обработку EmptyResultDataAccessException ???
	public String getLastFile(String own) { 
        Files result = jdbcTemplate.queryForObject("SELECT id,name,date_upload,owner_id,owner,comment FROM ibs.files where owner = ? AND date_upload = (SELECT MAX(date_upload) FROM ibs.files WHERE owner = ?)"
				,
				new Object[]{own, own},
				new RowMapper<Files>() {
					public Files mapRow (ResultSet rs, int rowNum) throws SQLException{
						Files files = new Files(
						rs.getInt("id"),
                        rs.getString("name"),
						//rs.getDate("date_upload"),
						rs.getTimestamp("date_upload"),
						rs.getInt("owner_id"),
						rs.getString("owner"),
						rs.getString("comment")
						);
						return files;
					}
				}		
        );
        return result.getName();   		
	}	
	
	// Delete
	// Add new 
    
}