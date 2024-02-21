package com.iag.iagApp.exceptions;

import java.util.function.Supplier;

public class InvalidOfferIdException extends Exception{
    public InvalidOfferIdException() {
        System.out.println("Invalid OFFER id exception");
    }
}
