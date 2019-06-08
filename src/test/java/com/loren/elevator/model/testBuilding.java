package com.loren.elevator.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Building.class)
public class testBuilding {
    @Autowired
    private Building building;

    private int height = 20;
    private int elevatorCnt = 4;
    private int maxPeople = 8;
    private int totalCall = 200;

    @Before
    public void setUp() {
        building.init(height, elevatorCnt, maxPeople, totalCall);
    }

    @Test
    public void testUpward() {
        for(int i = 0; i < elevatorCnt; i++) {
            for(int j = 0; j < height + 1; j++) {
                Assert.assertTrue(building.up(i));
            }
        }
    }

    @Test
    public void testDownward() {
        for(int i = 0; i < elevatorCnt; i++) {
            for(int j = 0; j < height + 1; j++) {
                Assert.assertTrue(building.down(i));
            }
        }
    }

    @Test
    public void testStop() {
        for(int i = 0; i < elevatorCnt; i++) {
            for(int j = 0; j < 2; j++) {
                Assert.assertTrue(building.stop(i));
            }
        }
    }

    @Test
    public void testOpen() {
        for(int i = 0; i < elevatorCnt; i++) {
            for(int j = 0; j < 2; j++) {
                Assert.assertTrue(building.open(i));
            }
        }
    }

    @Test
    public void testClose() {
        for(int i = 0; i < elevatorCnt; i++) {
            for(int j = 0; j < 2; j++) {
                Assert.assertTrue(building.close(i));
            }
        }
    }

    @Test
    public void testCall() {
        Assert.assertTrue(building.call());
    }
}
