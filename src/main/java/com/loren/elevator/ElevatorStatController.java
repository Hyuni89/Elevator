package com.loren.elevator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ElevatorStatController {

    @Autowired
    ElevatorService elevatorService;

    @GetMapping("/stat")
    public ModelAndView stat() {
        ModelAndView mv = new ModelAndView("stat");

        mv.addObject("height", elevatorService.getHeight());
        mv.addObject("cnt", elevatorService.getElevatorCnt());
        mv.addObject("run", elevatorService.isRunning());
        mv.addObject("call", elevatorService.getPassengers());
        mv.addObject("elevator", elevatorService.getElevatorWrapStatus());

        return mv;
    }
}
