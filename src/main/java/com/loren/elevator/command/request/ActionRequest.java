package com.loren.elevator.command.request;

import com.loren.elevator.command.wrapper.CommandWrap;

import java.util.List;

import lombok.Data;

@Data
public class ActionRequest extends Request {
    private List<CommandWrap> commands;
}
