package com.desenvolver.cursomc.resources.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
private List<FieldMessage> errors = new ArrayList<>();
    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getError() {
        return errors;
    }

    public void addError(String fieldName,String message) {
        this.errors.add(new FieldMessage(fieldName,message));
    }
}
