package com.test.slack.engineers.controller;

import com.test.slack.engineers.model.Engineers;
import com.test.slack.engineers.service.EngineerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.test.slack.engineers.controller.EngineerController.ENDPOINT;

@RestController
@RequestMapping(value = {ENDPOINT}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class EngineerController {

    public static final String ENDPOINT = "/engineers";

    private final EngineerService engineerService;

    @Autowired
    public EngineerController(EngineerService engineerService) {
        this.engineerService = engineerService;
    }

    @GetMapping
    public ResponseEntity<Engineers> getEngineers() {

        Engineers engineers = new Engineers(engineerService.getAllEngineers());

        return ResponseEntity.ok()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(engineers);
    }
}
