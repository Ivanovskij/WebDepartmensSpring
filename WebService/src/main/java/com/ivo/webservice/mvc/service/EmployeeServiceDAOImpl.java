package com.ivo.webservice.mvc.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.ivo.webservice.mvc.dao.EmployeeServiceDAO;
import com.ivo.webservice.mvc.model.Employee;
import com.ivo.webservice.mvc.model.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceDAOImpl implements EmployeeServiceDAO {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public EmployeeServiceDAOImpl() {
    }

    @PostConstruct
    public void init() {
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public boolean create(final Employee employee) {
        String query = "insert into EMPLOYEES (FIO, DATE_OF_BIRTH, SALARY, NAME_DEP) values (?, ?, ?, ?)";
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into EMPLOYEES (FIO, DATE_OF_BIRTH, SALARY, NAME_DEP) values (?, ?, ?, ?)");
                preparedStatement.setString(1, employee.getFio());
                preparedStatement.setDate(2, employee.getDateOfBirth());
                preparedStatement.setInt(3, employee.getSalary());
                preparedStatement.setString(4, employee.getDepartment());
                return preparedStatement;
            }
        });
        return true;
    }

    public Employee read(int id) {
        String query = "select * from EMPLOYEES where IDEMPLOYEES = ?";

        try {
            Employee employee = (Employee)this.jdbcTemplate.queryForObject(query, new Object[]{id}, new EmployeeMapper());
            return employee;
        } catch (Exception var4) {
            return null;
        }
    }

    public List<Employee> list() {
        String query = "select * from EMPLOYEES";
        List<Employee> employees = this.jdbcTemplate.query(query, new EmployeeMapper());
        return employees;
    }

    public boolean update(Employee employee) {
        String query = "update EMPLOYEES set FIO = ?, DATE_OF_BIRTH = ?, SALARY = ?, NAME_DEP = ? where IDEMPLOYEES = ?";
        int result = this.jdbcTemplate.update("update EMPLOYEES set FIO = ?, DATE_OF_BIRTH = ?, SALARY = ?, NAME_DEP = ? where IDEMPLOYEES = ?", new Object[]{employee.getFio(), employee.getDateOfBirth(), employee.getSalary(), employee.getDepartment(), employee.getId()});
        return result > 0;
    }

    public boolean delete(int id) {
        String query = "delete from EMPLOYEES where IDEMPLOYEES like ?";
        int result = this.jdbcTemplate.update("delete from EMPLOYEES where IDEMPLOYEES like ?", new Object[]{id});
        return result > 0;
    }

    @Override
    public List<Employee> search(Date dateOfBirth) {
        String query = "select * from EMPLOYEES where DATE_OF_BIRTH = ?";

        List<Employee> employees = this.jdbcTemplate.query(query, new Object[]{dateOfBirth}, new EmployeeMapper());
        return employees;

    }

    @Override
    public List<Employee> searchBetweenDates(Date from, Date to) {
        String query = "select * from EMPLOYEES where DATE_OF_BIRTH between ? and ?";

        List<Employee> employees = this.jdbcTemplate.query(query, new Object[]{from, to}, new EmployeeMapper());
        return employees;

    }
}
