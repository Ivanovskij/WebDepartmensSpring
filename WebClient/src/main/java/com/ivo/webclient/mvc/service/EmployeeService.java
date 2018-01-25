package com.ivo.webclient.mvc.service;

import com.ivo.webclient.mvc.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final String REST_URL = "http://localhost:8080/webservice/employees";

    @Autowired
    RestTemplate restTemplate;

    public Employee read(int employeeId) {
        logger.debug("called method read");

        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(
                REST_URL + "{id}",
                Employee.class,
                employeeId);

        Employee employee = responseEntity.getBody();

        logger.debug("Found employee: " + employee);

        return employee;
    }

    public List<Employee> list() {
        logger.debug("called method list");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Employee[]> response = restTemplate.exchange(
                REST_URL,
                HttpMethod.GET,
                entity,
                Employee[].class
        );

        Employee[] employees = response.getBody();
        if (employees == null) {
            logger.debug("employees not found");
            return new ArrayList<>();
        }

        logger.debug("Found " + employees.length + " employees");
        logger.debug(Arrays.toString(employees));

        return Arrays.asList(employees);
    }

    public void delete(int employeeId) {
        logger.debug("called method delete");

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                REST_URL + "/delete/" + "{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                employeeId);

        logger.debug("Employee with id " + employeeId + " deleted");
    }

    public void create(Employee employee) {
        logger.debug("called method create");

        ResponseEntity<Employee> responseEntity = restTemplate.postForEntity(
                REST_URL + "/create",
                employee,
                Employee.class);

        logger.debug("created: " + employee);
    }

    public void update(Employee employee) {
        logger.debug("called method update");

        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(employee);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                REST_URL + "/update",
                HttpMethod.PUT,
                requestEntity,
                Void.class);

        logger.debug("updated: " + employee);
    }

    public List<Employee> search(Date dateOfBirth) {
        logger.debug("called method search");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Employee[]> response = restTemplate.exchange(
                REST_URL + "/search/" + "{dateOfBirth}",
                HttpMethod.GET,
                entity,
                Employee[].class,
                dateOfBirth
        );

        Employee[] employees = response.getBody();
        if (employees == null) {
           logger.debug("employees by date(" + dateOfBirth.toString() + ") not found");
           return new ArrayList<>();
        }

        logger.debug("Found by date(" + dateOfBirth.toString() + ") " + employees.length +  employees);
        logger.debug(Arrays.toString(employees));

        return Arrays.asList(employees);
    }

    public List<Employee> searchBetweenDates(Date from, Date to) {
        logger.debug("called method searchBetweenDates");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Employee[]> response = restTemplate.exchange(
                REST_URL + "/search" + "/{from}" + "/{to}",
                HttpMethod.GET,
                entity,
                Employee[].class,
                from, to
        );

        Employee[] employees = response.getBody();
        if (employees == null) {
            logger.debug("employees by date from(" + from.toString() + ") and" +
                    " to(" + to.toString() + ") not found");
            return new ArrayList<>();
        }

        logger.debug("Found by date from(" + from.toString() + ") and" +
                        " to(" + to.toString() + ")" + employees.length +  employees);
        logger.debug(Arrays.toString(employees));

        return Arrays.asList(employees);
    }

}
