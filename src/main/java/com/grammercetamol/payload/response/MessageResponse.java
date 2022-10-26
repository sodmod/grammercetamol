package com.grammercetamol.payload.response;

import lombok.Data;

@Data
public class MessageResponse extends Response{


    public MessageResponse(String message, int responseCode){
        super.message = message;
        super.responseCode = responseCode;
    }
}
