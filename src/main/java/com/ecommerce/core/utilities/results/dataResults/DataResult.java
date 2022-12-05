package com.ecommerce.core.utilities.results.dataresults;

import com.ecommerce.core.utilities.results.Result;

import lombok.Getter;

@Getter
public class DataResult<T> extends Result {

    private T data;

    public DataResult(Boolean success, T data) {
        super(success);
        this.data = data;
    }

    public DataResult(Boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }

}
