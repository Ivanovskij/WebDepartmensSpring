package com.ivo.webclient.mvc.service;

import com.ivo.webclient.mvc.model.AvgSalary;
import com.ivo.webclient.mvc.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DepartmentService {

    private final String REST_URL = "http://localhost:8080/webservice/departments";

    @Autowired
    RestTemplate restTemplate;

    public Department read(int departmentId) {
        ResponseEntity<Department> responseEntity = restTemplate.getForEntity(
                REST_URL + "{id}",
                Department.class,
                departmentId);

        Department department = responseEntity.getBody();
        return department;
    }

    public List<Department> list() {
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
            return new ArrayList<>();
        }
        return Arrays.asList(departments);
    }

    public void delete(int departmentId) {
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                REST_URL + "/delete/" + "{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                departmentId);
    }

    public void create(Department department) {
        ResponseEntity<Department> responseEntity = restTemplate.postForEntity(
                REST_URL + "/create",
                department,
                Department.class);
    }

    public void update(Department department) {
        HttpEntity<Department> requestEntity = new HttpEntity<Department>(department);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                REST_URL + "/update",
                HttpMethod.PUT,
                requestEntity,
                Void.class);
    }

    public List<AvgSalary> averageSalary() {
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
        return Arrays.asList(response.getBody());
    }

}
