package com.loren.elevator.connection;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class CommonResponse {
    private int status;

    public static int STATUS_OK = 200;
    public static int STATUS_NOTOK = -400;
}
