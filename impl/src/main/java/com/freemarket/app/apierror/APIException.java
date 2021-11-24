package com.freemarket.app.apierror;

public class APIException extends RuntimeException {

    private final APIErrorType errorType;
    private final String[] args;

    public APIException(APIErrorType errorType, String... args) {
        this(errorType, null, args);
    }

    public APIException(APIErrorType errorType, Throwable cause, String... args) {
        super(cause);
        this.errorType = errorType;
        this.args = args;
    }

    public static APIException missingParam(String paramName) {
        return new APIException(APIErrorType.MISSING_PARAM, paramName);
    }

}
