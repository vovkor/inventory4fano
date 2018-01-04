package ru.nw.vir.dao;

import ru.nw.vir.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

	// Find all customers, thanks Java 8, you can create a custom RowMapper like this :
    public List<Customer> findAll() {

        List<Customer> result = jdbcTemplate.query(
                "SELECT id, accenumb, genus, created_date FROM acc",
                (rs, rowNum) -> new Customer(rs.getInt("id"),
                        rs.getString("accenumb"), rs.getString("genus"), rs.getDate("created_date"))
        );

        return result;

    }

	// Add new customer
    public void addCustomer(String accenumb, String genus) {

        jdbcTemplate.update("INSERT INTO customer(accenumb, genus, created_date) VALUES (?,?,?)",
                accenumb, genus, new Date());

    }


}