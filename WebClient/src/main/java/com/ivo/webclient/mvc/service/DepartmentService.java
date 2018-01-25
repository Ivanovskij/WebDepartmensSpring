package com.ivo.webclient.mvc.service;

import com.ivo.webclient.mvc.model.AvgSalary;
import com.ivo.webclient.mvc.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    private final String REST_URL = "http://localhost:8080/webservice/departments";

    @Autowired
    RestTemplate restTemplate;

    public Department read(int departmentId) {
        logger.debug("called method read");

        ResponseEntity<Department> responseEntity = restTemplate.getForEntity(
                REST_URL + "{id}",
                Department.class,
                departmentId);

        Department department = responseEntity.getBody();

        logger.debug("Found Department: " + department);

        return department;
    }

    public List<Department> list() {
        logger.debug("called method list");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Department[]> response = restTemplate.exchange(
                REST_URL,
                HttpMethod.GET,
                entity,
                Department[].class
        );

        Department[] departments = response.getBody();
        if (departments == null) {
            logger.debug("departments not found");
            return new ArrayList<>();
        }

        logger.debug("Found " + departments.length + " departments");
        logger.debug(Arrays.toString(departments));

        return Arrays.asList(departments);
    }

    public void delete(int departmentId) {
        logger.debug("called method delete");

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                REST_URL + "/delete/" + "{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                departmentId);

        logger.debug("Department with id " + departmentId + " deleted");
    }

    public void create(Department department) {
        logger.debug("called method create");

        ResponseEntity<Department> responseEntity = restTemplate.postForEntity(
                REST_URL + "/create",
                department,
                Department.class);

        logger.debug("created: " + department);
    }

    public void update(Department department) {
        logger.debug("called method update");

        HttpEntity<Department> requestEntity = new HttpEntity<Department>(department);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                REST_URL + "/update",
                HttpMethod.PUT,
                requestEntity,
                Void.class);

        logger.debug("updated: " + department);
    }

    public List<AvgSalary> averageSalary() {
        logger.debug("called method averageSalary");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<AvgSalary[]> response = restTemplate.exchange(
                REST_URL + "/averageSalary",
                HttpMethod.GET,
                entity,
                AvgSalary[].class
        );

        AvgSalary[] salaries = response.getBody();
        if (salaries == null) {
            logger.debug("salaries by department not found");
            return new ArrayList<>();
        }

        logger.debug("Found " + salaries.length + " average salaries by departments");
        logger.debug(Arrays.toString(salaries));

        return Arrays.asList(salaries);
    }

}
