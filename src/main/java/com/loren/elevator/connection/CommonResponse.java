package com.loren.elevator.connection;

import lombok.Data;

@Data
public class CommonResponse {
    private int status;
    public static int STATUS_OK = 200;
    public static int STATUS_NOTOK = -400;
}
