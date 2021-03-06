package com.test.slack.controller;

import com.test.slack.model.Engineers;
import com.test.slack.service.EngineerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static com.test.slack.controller.EngineerController.ENDPOINT;

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
    @Async
    public CompletableFuture<ResponseEntity<Engineers>> getEngineers() throws Exception {

        Engineers engineers = engineerService.getAllEngineers();

        ResponseEntity<Engineers> responseEntity =
                ResponseEntity.ok()
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .body(engineers);
        return CompletableFuture.completedFuture(responseEntity);
    }
}
