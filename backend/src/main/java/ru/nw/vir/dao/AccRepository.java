package ru.nw.vir.dao;

import ru.nw.vir.model.Acc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
//import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
//http://docs.spring.io/spring/docs/4.3.x/spring-framework-reference/html/jdbc.html
//http://spring-projects.ru/guides/relational-data-access/
@Repository
public class AccRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

	// Find all accs, thanks Java 8, you can create a custom RowMapper like this :
    public List<Acc> findAll() {

        List<Acc> result = jdbcTemplate.query("SELECT id,accenumb,collmunb,collcode,expedition,cropname,genus,species,spauthor,subtaxa,subtauthor,accename,accename_rus,acqdate,origcty,collsite,collsite_rus,latitude,longitude,elevation,colldate,bredcode,sampstat,ancest,ancest_rus,collsrc,doncty,donorcode,donornumb,othernumb,duplsite,storage,lifform,intrnumb,remarks,owner,created_date FROM acc"
				,
                (rs, rowNum) -> new Acc(rs.getInt("id"),
                        rs.getString("accenumb"),
						rs.getString("collmunb"),
						rs.getString("collcode"),
						rs.getString("expedition"),
						rs.getString("cropname"),
						rs.getString("genus"),
						rs.getString("species"),
						rs.getString("spauthor"),
						rs.getString("subtaxa"),
						rs.getString("subtauthor"),
						rs.getString("accename"),
						rs.getString("accename_rus"),
						rs.getString("acqdate"),
						rs.getString("origcty"),
						rs.getString("collsite"),
						rs.getString("collsite_rus"),
						rs.getString("latitude"),
						rs.getString("longitude"),
						rs.getString("elevation"),
						rs.getString("colldate"),
						rs.getString("bredcode"),
						rs.getString("sampstat"),
						rs.getString("ancest"),
						rs.getString("ancest_rus"),
						rs.getString("collsrc"),
						rs.getString("doncty"),
						rs.getString("donorcode"),
						rs.getString("donornumb"),
						rs.getString("othernumb"),
						rs.getString("duplsite"),
						rs.getString("storage"),
						rs.getString("lifform"),
						rs.getString("intrnumb"),
						rs.getString("remarks"),
						rs.getString("owner"),						
						rs.getDate("created_date"))
        );
        return result;   }

    public Acc getAcc(Long id) {

        Acc result = jdbcTemplate.queryForObject("SELECT id,accenumb,collmunb,collcode,expedition,cropname,genus,species,spauthor,subtaxa,subtauthor,accename,accename_rus,acqdate,origcty,collsite,collsite_rus,latitude,longitude,elevation,colldate,bredcode,sampstat,ancest,ancest_rus,collsrc,doncty,donorcode,donornumb,othernumb,duplsite,storage,lifform,intrnumb,remarks,owner,created_date FROM acc where id = ?"
				,
				new Object[]{id},
				new RowMapper<Acc>() {
					public Acc mapRow (ResultSet rs, int rowNum) throws SQLException{
						Acc acc = new Acc(
						rs.getInt("id"),
                        rs.getString("accenumb"),
						rs.getString("collmunb"),
						rs.getString("collcode"),
						rs.getString("expedition"),
						rs.getString("cropname"),
						rs.getString("genus"),
						rs.getString("species"),
						rs.getString("spauthor"),
						rs.getString("subtaxa"),
						rs.getString("subtauthor"),
						rs.getString("accename"),
						rs.getString("accename_rus"),
						rs.getString("acqdate"),
						rs.getString("origcty"),
						rs.getString("collsite"),
						rs.getString("collsite_rus"),
						rs.getString("latitude"),
						rs.getString("longitude"),
						rs.getString("elevation"),
						rs.getString("colldate"),
						rs.getString("bredcode"),
						rs.getString("sampstat"),
						rs.getString("ancest"),
						rs.getString("ancest_rus"),
						rs.getString("collsrc"),
						rs.getString("doncty"),
						rs.getString("donorcode"),
						rs.getString("donornumb"),
						rs.getString("othernumb"),
						rs.getString("duplsite"),
						rs.getString("storage"),
						rs.getString("lifform"),
						rs.getString("intrnumb"),
						rs.getString("remarks"),
						rs.getString("owner"),						
						rs.getDate("created_date"));
						return acc;
					}
				}		
        );
        return result;   }		

	// --------------------------- Выбрать первые n записей ----------------------------------------
	// Find all accs, thanks Java 8, you can create a custom RowMapper like this :
    public List<Acc> findAllLimit(Long lm) {

        List<Acc> result = jdbcTemplate.query("SELECT id,accenumb,collmunb,collcode,expedition,cropname,genus,species,spauthor,subtaxa,subtauthor,accename,accename_rus,acqdate,origcty,collsite,collsite_rus,latitude,longitude,elevation,colldate,bredcode,sampstat,ancest,ancest_rus,collsrc,doncty,donorcode,donornumb,othernumb,duplsite,storage,lifform,intrnumb,remarks,owner,created_date FROM acc LIMIT 0,?"
				,
				new Object[]{lm}
				,
                (rs, rowNum) -> new Acc(rs.getInt("id"),
                        rs.getString("accenumb"),
						rs.getString("collmunb"),
						rs.getString("collcode"),
						rs.getString("expedition"),
						rs.getString("cropname"),
						rs.getString("genus"),
						rs.getString("species"),
						rs.getString("spauthor"),
						rs.getString("subtaxa"),
						rs.getString("subtauthor"),
						rs.getString("accename"),
						rs.getString("accename_rus"),
						rs.getString("acqdate"),
						rs.getString("origcty"),
						rs.getString("collsite"),
						rs.getString("collsite_rus"),
						rs.getString("latitude"),
						rs.getString("longitude"),
						rs.getString("elevation"),
						rs.getString("colldate"),
						rs.getString("bredcode"),
						rs.getString("sampstat"),
						rs.getString("ancest"),
						rs.getString("ancest_rus"),
						rs.getString("collsrc"),
						rs.getString("doncty"),
						rs.getString("donorcode"),
						rs.getString("donornumb"),
						rs.getString("othernumb"),
						rs.getString("duplsite"),
						rs.getString("storage"),
						rs.getString("lifform"),
						rs.getString("intrnumb"),
						rs.getString("remarks"),
						rs.getString("owner"),						
						rs.getDate("created_date"))
        );
        return result;   }
		
	// Add new addAccTest
    public void addAccTest(String accenumb, String genus) throws SQLException {

        jdbcTemplate.update("insert into acc (accenumb, genus, created_date) values (?,?,?)",
                accenumb, genus, new Date());
    }
	
	// Добавить запись о загрузке файла
    public void addRecFile(String filename, String owner)  {

        jdbcTemplate.update("insert into files (name, owner, date_upload) values (?,?,?)",
                filename, owner, new Date());
    }
	
	// Delete
    public String deleteAcc(String owner) throws SQLException {
		
		String deleteSql; // если добавить private не компилируется
		deleteSql = "DELETE FROM acc WHERE owner = ?";
        int rows = jdbcTemplate.update(deleteSql, owner);
		return rows + "";
    }	
	
	// Add new Acc
    public void addAcc(String accenumb,String collmunb,String collcode,String expedition,String cropname,String genus,String species,String spauthor,String subtaxa,String subtauthor,String accename,String accename_rus,String acqdate,String origcty,String collsite,String collsite_rus,String latitude,String longitude,String elevation,String colldate,String bredcode,String sampstat,String ancest,String ancest_rus,String collsrc,String doncty,String donorcode,String donornumb,String othernumb,String duplsite,String storage,String lifform,String intrnumb,String remarks,String owner)
	throws SQLException {

        jdbcTemplate.update("insert into acc (accenumb,collmunb,collcode,expedition,genus,species,spauthor,subtaxa,subtauthor,accename,accename_rus,acqdate,origcty,collsite,collsite_rus,latitude,longitude,elevation,colldate,bredcode,sampstat,ancest,ancest_rus,collsrc,doncty,donorcode,donornumb,othernumb,duplsite,storage,lifform,intrnumb,remarks,owner,created_date) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                accenumb,collmunb,collcode,expedition,genus,species,spauthor,subtaxa,subtauthor,accename,accename_rus,acqdate,origcty,collsite,collsite_rus,latitude,longitude,elevation,colldate,bredcode,sampstat,ancest,ancest_rus,collsrc,doncty,donorcode,donornumb,othernumb,duplsite,storage,lifform,intrnumb,remarks,owner, new Date());
    }
}