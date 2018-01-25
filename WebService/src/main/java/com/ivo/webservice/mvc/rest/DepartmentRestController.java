package com.ivo.webservice.mvc.rest;


import com.ivo.webservice.mvc.dao.DepartmentServiceDAO;
import com.ivo.webservice.mvc.model.AvgSalary;
import com.ivo.webservice.mvc.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:8084" })
@RestController
@RequestMapping(value = "/departments")
public class DepartmentRestController {

    @Autowired
    DepartmentServiceDAO departmentServiceDao;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Department>> list() {
        List<Department> departments = departmentServiceDao.list();
        if (departments.isEmpty()) {
            //logger.debug("Employees does not exists");
            return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
        }
        //logger.debug("Found " + employees.size() + " Employees");
        //logger.debug(employees);
        //logger.debug(Arrays.toString(employees.toArray()));
        return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
    }

    @RequestMapping(value = "/{departmentId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Department> read(@PathVariable(value = "departmentId") int departmentId) {
        Department department = departmentServiceDao.read(departmentId);
        if (department == null) {
            //logger.debug("Employee with id " + id + " does not exists");
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
        }
        //logger.debug("Found Employee:: " + employee);
        return new ResponseEntity<Department>(department, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Department> create(@RequestBody Department department) {
        departmentServiceDao.create(department);
        //logger.debug("Added:: " + employee);
        return new ResponseEntity<Department>(department, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Void> update(@RequestBody Department department) {
        Department existingDep = departmentServiceDao.read(department.getId());
        if (existingDep == null) {
            //logger.debug("Employee with id " + department.getId() + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            departmentServiceDao.update(department);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{departmentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable(value = "departmentId") int departmentId) {
        Department department = departmentServiceDao.read(departmentId);
        if (department == null) {
            //logger.debug("Employee with id " + id + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            departmentServiceDao.delete(departmentId);
            //logger.debug("Employee with id " + id + " deleted");
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/averageSalary", method = RequestMethod.GET, produces = "application/json")
    public List<AvgSalary> averageSalary() {
        return departmentServiceDao.averageSalaryByDepartment();
    }

}
