package com.test.slack.controller;

import com.test.slack.model.Engineer;
import com.test.slack.model.Engineers;
import com.test.slack.service.EngineerService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class EngineerControllerTest {

    private EngineerController controller;

    @Mock
    private EngineerService engineerService;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        controller = new EngineerController(engineerService);
    }

    @Test
    public void testGetAllEngineers() throws Exception {

        String name = "foo";
        Engineer engineer = new Engineer(name);
        Set<Engineer> requestEngineerSet = new HashSet();
        requestEngineerSet.add(engineer);
        when(engineerService.getAllEngineers()).thenReturn(new Engineers(requestEngineerSet));
        ResponseEntity<Engineers> responseEntity = controller.getEngineers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Engineers engineers = responseEntity.getBody();
        assertNotNull(engineers);

        Set<Engineer> responseEngineerSet = engineers.getEngineers();
        assertNotNull(responseEngineerSet);
        assertFalse(responseEngineerSet.isEmpty());

        assertTrue(responseEngineerSet.contains(new Engineer(name)));
    }

    @Test
    public void testGetAllEngineersServerError() throws Exception {

        String errorMessage = "Error retrieving";
        when(engineerService.getAllEngineers()).thenThrow(new RuntimeException(errorMessage));
        try {
            controller.getEngineers();
            fail();
        } catch (RuntimeException e) {
            assertTrue(errorMessage.equals(e.getMessage()));
        }
    }

}