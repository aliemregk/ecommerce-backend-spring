package com.ecommerce.core.utilities.results;

import org.springframework.http.ResponseEntity;

public final class ResultChecker {

    private ResultChecker() {
    }

    public static <T> ResponseEntity<T> checkResult(T resultToCheck) {
        if (((Result) resultToCheck).isSuccess()) {
            return ResponseEntity.ok(resultToCheck);
        }
        return ResponseEntity.badRequest().body(resultToCheck);
    }
}
