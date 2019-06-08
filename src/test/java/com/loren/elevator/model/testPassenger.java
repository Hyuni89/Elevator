package com.loren.elevator.model;

import org.junit.Assert;

public class testPassenger {
    public static void main(String[] args) {
        Passenger p = new Passenger(10, 0);
        int source = p.getSourceFloor();
        int target = p.getTargetFloor();
        int floor = p.getFloor();

        Assert.assertNotEquals(source, target);
        Assert.assertEquals(source, floor);

        if(source > target) {
            for(int i = 0; i < source - target - 1; i++) {
                Assert.assertFalse(p.down());
            }
            Assert.assertTrue(p.down());
            Assert.assertFalse(p.down());
        } else {
            for(int i = 0; i < target - source - 1; i++) {
                Assert.assertFalse(p.up());
            }
            Assert.assertTrue(p.up());
            Assert.assertFalse(p.up());
        }
    }
}
