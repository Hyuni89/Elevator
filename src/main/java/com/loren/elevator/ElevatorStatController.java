package com.loren.elevator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ElevatorStatController {

    @Autowired
    ElevatorService elevatorService;

    @GetMapping("/stat")
    public String stat() {
        return "stat";
    }
}
