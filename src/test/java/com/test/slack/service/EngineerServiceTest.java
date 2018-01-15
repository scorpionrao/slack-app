package com.test.slack.service;

import com.test.slack.controller.EngineerController;
import com.test.slack.dao.EngineerDao;
import com.test.slack.model.Engineer;
import com.test.slack.model.Engineers;

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

public class EngineerServiceTest {

    private EngineerService service;

    @Mock
    private EngineerDao dao;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        service = new EngineerService(dao);
    }

    @Test
    public void testGetAllEngineers() {

        String name = "foo";
        Engineer engineer = new Engineer(name);
        Set<Engineer> requestEngineerSet = new HashSet();
        requestEngineerSet.add(engineer);
        when(dao.getAllEngineers()).thenReturn(requestEngineerSet);

        Set<Engineer> engineers = service.getAllEngineers();
        assertNotNull(engineers);
        assertFalse(engineers.isEmpty());

        assertTrue(engineers.contains(new Engineer(name)));
    }

    @Test
    public void testGetAllEngineersServerError() {

        String errorMessage = "Error retrieving";
        when(dao.getAllEngineers()).thenThrow(new RuntimeException(errorMessage));
        try {
            service.getAllEngineers();
            fail();
        } catch (RuntimeException e) {
            assertTrue(errorMessage.equals(e.getMessage()));
        }
    }

}