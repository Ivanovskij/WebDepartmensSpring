package com.ivo.webclient.mvc.service;

import com.ivo.webclient.mvc.model.Employee;
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

    private final String REST_URL = "http://localhost:8080/webservice/employees";

    @Autowired
    RestTemplate restTemplate;

    public Employee read(int employeeId) {
        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(
                REST_URL + "{id}",
                Employee.class,
                employeeId);

        Employee employee = responseEntity.getBody();
        return employee;
    }

    public List<Employee> list() {
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

        Employee[] employee = response.getBody();
        if (employee == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(employee);
    }

    public void delete(int employeeId) {
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                REST_URL + "/delete/" + "{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                employeeId);
    }

    public void create(Employee employee) {
        ResponseEntity<Employee> responseEntity = restTemplate.postForEntity(
                REST_URL + "/create",
                employee,
                Employee.class);
    }

    public void update(Employee employee) {
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(employee);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                REST_URL + "/update",
                HttpMethod.PUT,
                requestEntity,
                Void.class);
    }

    public List<Employee> search(Date dateOfBirth) {
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

        Employee[] employee = response.getBody();
        if (employee == null) {
           return new ArrayList<>();
        }
        return Arrays.asList(employee);
    }

    public List<Employee> searchBetweenDates(Date from, Date to) {
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

        Employee[] employee = response.getBody();
        if (employee == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(employee);
    }

}
