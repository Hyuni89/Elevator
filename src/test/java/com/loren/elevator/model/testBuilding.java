package com.loren.elevator.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class testBuilding {
    @Autowired
    private Building building;

    @Before
    public void setUp() {
        building.init(20, 4, 8, 200);
    }

    @Test
    public void test001() {

    }
}
