package com.sulay.photoapp.exception;

import java.util.function.Supplier;

public class PhotoAppException extends RuntimeException {
    public PhotoAppException(String errorMessage) {
        super(errorMessage);
    }
}
