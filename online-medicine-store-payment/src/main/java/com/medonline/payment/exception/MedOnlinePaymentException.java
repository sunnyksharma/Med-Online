package com.medonline.payment.exception;

import java.io.Serial;

public class MedOnlinePaymentException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    public MedOnlinePaymentException(String message) {
        super(message);
    }
}
