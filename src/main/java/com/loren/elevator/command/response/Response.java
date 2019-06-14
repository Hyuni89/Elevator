package com.loren.elevator.command.response;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Response {
    private int status;

    public static int STATUS_OK = 200;
    public static int STATUS_NOTOK = -400;
}
