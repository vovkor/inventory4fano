package ru.nw.vir.dao;

import ru.nw.vir.model.Account;
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
public class AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

	// Find all accounts, thanks Java 8, you can create a custom RowMapper like this :
    public List<Account> findAll() {

        List<Account> result = jdbcTemplate.query("SELECT id,username,password,fio,casta,depart,boss FROM account"
				,
                (rs, rowNum) -> new Account(rs.getInt("id"),
                        rs.getString("username"),
						rs.getString("password"),
						rs.getString("fio"),
						rs.getString("casta"),
						rs.getString("depart"),
						rs.getInt("boss")
						)
        );
        return result;   }

    public Account getAccount(Long id) {

        Account result = jdbcTemplate.queryForObject("SELECT id,username,password,fio,casta,depart,boss FROM account where id = ?"
				,
				new Object[]{id},
				new RowMapper<Account>() {
					public Account mapRow (ResultSet rs, int rowNum) throws SQLException{
						Account account = new Account(
						rs.getInt("id"),
                        rs.getString("username"),
						rs.getString("password"),
						rs.getString("fio"),
						rs.getString("casta"),
						rs.getString("depart"),
						rs.getInt("boss")
						);
						return account;
					}
				}		
        );
        return result;   
	}		
	//тип Optional<>, удобен для работы с null
	// добавить обработку EmptyResultDataAccessException ???
	public Optional<Account> findByUsername(String username) { // в примере было Optional<Account>
        Optional<Account> result = jdbcTemplate.queryForObject("SELECT id,username,password,fio,casta,depart,boss FROM account where username = ?"
				,
				new Object[]{username},
				new RowMapper<Optional<Account>>() {
					public Optional<Account> mapRow (ResultSet rs, int rowNum) throws SQLException{
						Account account = new Account(
						rs.getInt("id"),
                        rs.getString("username"),
						rs.getString("password"),
						rs.getString("fio"),
						rs.getString("casta"),
						rs.getString("depart"),
						rs.getInt("boss")
						);
						return Optional.of(account);
					}
				}		
        );
        return result;   		
	}
	
	// Delete
    public String deleteAccount(Long id) throws SQLException {
		
		String deleteSql; // если добавить private не компилируется
		deleteSql = "DELETE FROM account WHERE id = ?";
        int rows = jdbcTemplate.update(deleteSql, id);
		return rows + "";
    }	
	
	// Add new Account
    public void addAccount(String username,String password,String fio,String casta,String depart,int boss)
	throws SQLException {

        jdbcTemplate.update("insert into account (username,password,fio,casta,depart,boss) values (?,?,?,?,?,?)",
                username,password,fio,casta,depart,boss);
    }
}