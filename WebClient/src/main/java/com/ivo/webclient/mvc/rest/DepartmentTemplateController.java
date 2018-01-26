package com.ivo.webclient.mvc.rest;

import com.ivo.webclient.mvc.exception.CustomException;
import com.ivo.webclient.mvc.model.AvgSalary;
import com.ivo.webclient.mvc.model.Department;
import com.ivo.webclient.mvc.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentTemplateController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentTemplateController.class);

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/{departmentId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ModelAndView read(@PathVariable int departmentId) {
        Department department = departmentService.read(departmentId);
        return new ModelAndView("/department/read", "result", department);
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
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

    @RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json")
    public void createDepartment(@RequestBody Department department) {
        logger.debug("called createDepartment, method POST");
        departmentService.create(department);
        logger.debug("create " + department);
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
    public void updateDepartment(@RequestBody Department department) {
        logger.debug("called updateDepartment, method PUT");
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
    public void deleteDepartment(@PathVariable(value = "departmentId") int departmentId) {
        logger.debug("called deleteDepartment, method DELETE");
        departmentService.delete(departmentId);
    }

    @RequestMapping(value = "averageSalary", method = RequestMethod.GET, headers = "Accept=application/json")
    public ModelAndView averageSalary() {
        logger.debug("called averageSalary, method GET");

        List<AvgSalary> salaries = departmentService.averageSalary();

        ModelAndView mv = new ModelAndView("/department/common");

        mv.addObject("title", "Average salary");
        mv.addObject("userClickAverageSalary", true);
        mv.addObject("result", salaries);

        return mv;
    }

    @ExceptionHandler(CustomException.class)
    public ModelAndView handleCustomException(CustomException ex) {
        ModelAndView mv = new ModelAndView("/errors/error");
        mv.addObject("errCode", ex.getErrCode());
        mv.addObject("errMsg", ex.getErrMsg());
        return mv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView mv = new ModelAndView("/errors/error");
        mv.addObject("errMsg", ex.getMessage());
        return mv;
    }


}
