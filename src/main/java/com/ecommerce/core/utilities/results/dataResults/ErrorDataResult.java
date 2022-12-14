package com.ecommerce.core.utilities.results.dataresults;

public class ErrorDataResult<T> extends DataResult<T> {

    public ErrorDataResult(String message, T data) {
        super(false, message, data);
    }

    public ErrorDataResult(T data) {
        super(false, data);
    }
}
