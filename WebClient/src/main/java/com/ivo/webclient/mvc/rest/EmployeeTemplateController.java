package com.ivo.webclient.mvc.rest;

import com.ivo.webclient.mvc.model.Employee;
import com.ivo.webclient.mvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeTemplateController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView read(@PathVariable int employeeId) {
        Employee employee = employeeService.read(employeeId);
        return new ModelAndView("/employee/read", "result", employee);
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView list() {
        List<Employee> employees = employeeService.list();

        ModelAndView mv = new ModelAndView("/employee/common");

        mv.addObject("title", "read");
        mv.addObject("userClickReadList", true);
        mv.addObject("result", employees);

        return mv;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("/employee/common");
        mv.addObject("title", "create");
        mv.addObject("userClickCreate", true);
        return mv;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String createEmployee(Employee employee) {
        System.out.println(employee);
        employeeService.create(employee);
        return "/employee/create";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView update() {
        ModelAndView mv = new ModelAndView("/employee/common");

        List<Employee> departments = employeeService.list();
        mv.addObject("title", "update");
        mv.addObject("result", departments);

        mv.addObject("userClickUpdate", true);
        return mv;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public void updateDepartment(@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete() {
        List<Employee> employees = employeeService.list();

        ModelAndView mv = new ModelAndView("/employee/common");

        mv.addObject("title", "delete");
        mv.addObject("userClickDelete", true);
        mv.addObject("result", employees);

        return mv;
    }

    @RequestMapping(value = "/delete/{employeeId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteDepartment(@PathVariable(value = "employeeId") int employeeId) {
        employeeService.delete(employeeId);
        return "/employee/delete";
    }

    @RequestMapping(value = "/search/{dateOfBirth}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView search(@PathVariable("dateOfBirth")Date dateOfBirth) {
        List<Employee> employees = employeeService.search(dateOfBirth);

        ModelAndView mv = new ModelAndView("/employee/common");

        mv.addObject("title", "search");
        mv.addObject("userClickReadList", true);
        mv.addObject("result", employees);

        return mv;
    }

    @RequestMapping(value = "/search/{from}/{to}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView search(@PathVariable("from")Date from,
                               @PathVariable("to")Date to) {
        List<Employee> employees = employeeService.searchBetweenDates(from, to);

        ModelAndView mv = new ModelAndView("/employee/common");

        mv.addObject("title", "search");
        mv.addObject("userClickReadList", true);
        mv.addObject("result", employees);

        return mv;
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "FORBIDDEN ACCESS (PROVIDE YOUR CUSTOM REASON HERE)")
    public void handleException(Exception ex) {
        System.out.println("@RestTemplateControllerExample handleException");
        System.out.println(ex);
    }

}
