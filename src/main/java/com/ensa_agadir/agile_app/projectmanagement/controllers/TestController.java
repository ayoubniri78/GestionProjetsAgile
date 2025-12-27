package com.ensa_agadir.agile_app.projectmanagement.controllers;

import com.ensa_agadir.agile_app.projectmanagement.models.TestProject;
import com.ensa_agadir.agile_app.projectmanagement.service.TestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/find/{id}")
    public TestProject findById(@PathVariable("id") int id){
        return testService.findProjectById(id);
    }

    @PostMapping("/save-project")
    public TestProject saveProject(@RequestBody TestProject testProject){
        return testService.saveProject(testProject);
    }
}
