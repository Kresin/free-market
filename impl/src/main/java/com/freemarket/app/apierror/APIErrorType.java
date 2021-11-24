package com.freemarket.app.apierror;

import javax.ws.rs.core.Response.Status;

public enum APIErrorType {

    ENTITY_NOT_FOUND("404.entity-not-found", Status.NOT_FOUND),

    MISSING_PARAM("400.missing-param", Status.BAD_REQUEST);

    private final String rawCode;
    private final Status status;

    APIErrorType(String rawCode, Status status) {
        this.rawCode = rawCode;
        this.status = status;
    }

    public String getRawCode() {
        return rawCode;
    }

    public Status getStatus() {
        return status;
    }

}
