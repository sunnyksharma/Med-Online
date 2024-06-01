package com.medonline.cart.exception;

import java.io.Serial;

public class MedOnlineCartException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public static String MED_NOT_FOUND = "The medicine is not available in cart.";
    public MedOnlineCartException(String message) {
        super(message);
    }
}
