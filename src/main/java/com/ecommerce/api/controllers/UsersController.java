package com.ecommerce.api.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.business.abstracts.UserService;
import com.ecommerce.business.requests.user.DeleteUserRequest;
import com.ecommerce.business.requests.user.UpdateUserRequest;
import com.ecommerce.business.responses.user.GetAllUserResponse;
import com.ecommerce.business.responses.user.GetByIdUserResponse;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.ResultChecker;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(path = "/getall")
    public ResponseEntity<DataResult<List<GetAllUserResponse>>> getAll() {
        return ResultChecker.checkResult(userService.getAll());
    }

    @GetMapping(path = "/getbyid")
    public ResponseEntity<DataResult<GetByIdUserResponse>> getById(@RequestParam int id) {
        return ResultChecker.checkResult(userService.getById(id));
    }

    @RolesAllowed("ROLE_USER")
    @PutMapping(path = "/update")
    public ResponseEntity<Result> update(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return ResultChecker.checkResult(userService.update(updateUserRequest));
    }

    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(path = "/delete")
    public ResponseEntity<Result> delete(@RequestBody @Valid DeleteUserRequest deleteUserRequest) {
        return ResultChecker.checkResult(userService.delete(deleteUserRequest));
    }

}
