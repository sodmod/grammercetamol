package com.grammercetamol.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response{

    protected String message;
    protected int responseCode;

    public Response(String msg, int code){
        message = msg;
        responseCode = code;
    }

}
