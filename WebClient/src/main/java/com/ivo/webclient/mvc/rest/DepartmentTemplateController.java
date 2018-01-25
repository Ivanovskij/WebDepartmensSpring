package com.ivo.webclient.mvc.rest;

import com.ivo.webclient.mvc.model.AvgSalary;
import com.ivo.webclient.mvc.model.Department;
import com.ivo.webclient.mvc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentTemplateController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/{departmentId}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView read(@PathVariable int departmentId) {
        Department department = departmentService.read(departmentId);
        return new ModelAndView("/department/read", "result", department);
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView list() {
        List<Department> departments = departmentService.list();

        ModelAndView mv = new ModelAndView("/department/common");

        mv.addObject("title", "read");
        mv.addObject("userClickReadList", true);
        mv.addObject("result", departments);

        return mv;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("/department/common");
        mv.addObject("title", "create");
        mv.addObject("userClickCreate", true);
        return mv;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String createDepartment(Department department) {
        departmentService.create(department);
        return "/department/create";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView update() {
        ModelAndView mv = new ModelAndView("/department/common");

        List<Department> departments = departmentService.list();
        mv.addObject("title", "update");
        mv.addObject("result", departments);

        mv.addObject("userClickUpdate", true);
        return mv;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public void updateDepartment(@RequestBody Department department) {
        departmentService.update(department);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete() {
        List<Department> departments = departmentService.list();

        ModelAndView mv = new ModelAndView("/department/common");

        mv.addObject("title", "delete");
        mv.addObject("userClickDelete", true);
        mv.addObject("result", departments);

        return mv;
    }

    @RequestMapping(value = "/delete/{departmentId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteDepartment(@PathVariable(value = "departmentId") int departmentId) {
        departmentService.delete(departmentId);
        return "/department/delete";
    }

    @RequestMapping(value = "averageSalary", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView averageSalary() {
        List<AvgSalary> salaries = departmentService.averageSalary();

        ModelAndView mv = new ModelAndView("/department/common");

        mv.addObject("title", "Average salary");
        mv.addObject("userClickAverageSalary", true);
        mv.addObject("result", salaries);

        return mv;
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "FORBIDDEN ACCESS (PROVIDE YOUR CUSTOM REASON HERE)")
    public void handleException(Exception ex) {
        System.out.println("@RestTemplateControllerExample handleException");
        System.out.println(ex);
    }


}
