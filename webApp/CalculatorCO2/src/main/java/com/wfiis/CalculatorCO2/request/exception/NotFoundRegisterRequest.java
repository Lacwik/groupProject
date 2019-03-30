package com.wfiis.CalculatorCO2.request.exception;

public class NotFoundRegisterRequest extends RuntimeException {
    public NotFoundRegisterRequest(Long companyId) {
        super("Not found register request for company id: " + companyId);
    }
}
