package com.ecommerce.business.abstracts;

import java.util.List;

import com.ecommerce.business.requests.auth.RegisterRequest;
import com.ecommerce.business.requests.user.DeleteUserRequest;
import com.ecommerce.business.requests.user.UpdateUserRequest;
import com.ecommerce.business.responses.user.GetAllUserResponse;
import com.ecommerce.business.responses.user.GetByIdUserResponse;
import com.ecommerce.core.entities.User;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

public interface UserService {

    DataResult<List<GetAllUserResponse>> getAll();

    DataResult<GetByIdUserResponse> getById(int id);

    DataResult<User> getByEmail(String email);

    DataResult<User> add(RegisterRequest addUserRequest);

    Result update(UpdateUserRequest updateUserRequest);

    Result delete(DeleteUserRequest deleteUserRequest);
}
