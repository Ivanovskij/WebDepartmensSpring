package com.ivo.webservice.mvc.service;

import com.ivo.webservice.mvc.dao.DepartmentServiceDAO;
import com.ivo.webservice.mvc.model.AvgSalary;
import com.ivo.webservice.mvc.model.AvgSalaryMapper;
import com.ivo.webservice.mvc.model.Department;
import com.ivo.webservice.mvc.model.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class DepartmentServiceDAOImpl implements DepartmentServiceDAO {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean create(final Department department) {
        final String query = "insert into DEPARTMENTS (NAME_DEP) values (?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, department.getName_dep());
                return preparedStatement;
            }
        });
        return true;
    }

    @Override
    public Department read(int id) {
        String query = "select * from DEPARTMENTS where IDDEPARTMENTS = ?";
        try {
            Department department = jdbcTemplate.queryForObject(query,
                    new Object[]{id}, new DepartmentMapper());
            return department;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Department> list() {
        String query = "select * from DEPARTMENTS";
        List<Department> departments = jdbcTemplate.query(query, new DepartmentMapper());
        return departments;
    }

    @Override
    public boolean update(Department department) {
        final String query = "update DEPARTMENTS set NAME_DEP = ? where IDDEPARTMENTS = ?";
        int result = jdbcTemplate.update(query, new Object[]{department.getName_dep(), department.getId()});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean delete(int id) {
        final String query = "delete from DEPARTMENTS where IDDEPARTMENTS like ?";
        int result = jdbcTemplate.update(query, new Object[]{id});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<AvgSalary> averageSalaryByDepartment() {
        final String query = "select NAME_DEP, AVG(SALARY) as AVG_SALARY " +
                "from EMPLOYEES group by NAME_DEP";

        List<AvgSalary> avgSalaries = jdbcTemplate.query(query, new AvgSalaryMapper());

        return avgSalaries;
    }
}
