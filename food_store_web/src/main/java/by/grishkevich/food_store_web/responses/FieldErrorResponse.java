package by.grishkevich.food_store_web.responses;

import by.grishkevich.food_store_web.errors.CustomFieldError;

import java.util.List;

public class FieldErrorResponse {
    private List<CustomFieldError> fieldErrors;

    public List<CustomFieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<CustomFieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
