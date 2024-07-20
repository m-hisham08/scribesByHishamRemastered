package org.hisham.ScribesByHisham.exceptions.CustomExceptions;

public class PaymentException extends RuntimeException{
    public PaymentException(String message){
        super(message);
    }

    public PaymentException(String message, Throwable cause){
        super(message, cause);
    }
}
