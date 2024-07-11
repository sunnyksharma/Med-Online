package com.medonline.order.exception;

import java.io.Serial;

public class MedOnlineException extends  Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public MedOnlineException(String exceptionMessage)
    {
        super(exceptionMessage);
    }
}
