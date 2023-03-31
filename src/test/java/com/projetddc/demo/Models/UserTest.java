package com.projetddc.demo.Models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

    @Test
    void testIdIsProperlyGenerated(){
        //GIVEN
        User testUsers = new User();
        //WHEN
        UUID testId = testUsers.getId();
        //THEN
        assertNotNull(testId);
    }
    
}
