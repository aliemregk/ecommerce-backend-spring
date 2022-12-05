package com.ecommerce.core.utilities.results;

import lombok.Getter;

@Getter
public class Result  {
    private boolean success;
    private String message;

    public Result(Boolean success, String message) {
        this(success);
        this.message = message;
    }

    public Result(Boolean success) {
        this.success = success;
    }
}
