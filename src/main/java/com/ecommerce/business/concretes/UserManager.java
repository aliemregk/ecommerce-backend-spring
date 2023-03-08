package com.ecommerce.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.UserService;
import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.auth.RegisterRequest;
import com.ecommerce.business.requests.user.DeleteUserRequest;
import com.ecommerce.business.requests.user.UpdateUserRequest;
import com.ecommerce.business.responses.user.GetAllUserResponse;
import com.ecommerce.business.responses.user.GetByIdUserResponse;
import com.ecommerce.core.dataaccess.UserRepository;
import com.ecommerce.core.entities.User;
import com.ecommerce.core.utilities.mapper.MapperUtil;
import com.ecommerce.core.utilities.results.ErrorResult;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.SuccessResult;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.ErrorDataResult;
import com.ecommerce.core.utilities.results.dataresults.SuccessDataResult;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private static final String MESSAGE = "User";
    private final UserRepository userRepository;

    @Cacheable(value = "users")
    @Override
    public DataResult<List<GetAllUserResponse>> getAll() {
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(userRepository.findAll(Sort.by(Sort.Direction.ASC, "id")), GetAllUserResponse.class));
    }

    @Override
    public DataResult<GetByIdUserResponse> getById(int id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return new SuccessDataResult<>(Messages.LISTED, MapperUtil.map(result.get(), GetByIdUserResponse.class));
        }
        return new ErrorDataResult<>(MESSAGE + Messages.NOT_FOUND, null);
    }

    @Override
    public DataResult<User> getByEmail(String email) {
        Optional<User> result = userRepository.getByEmail(email);
        if (result.isPresent()) {
            return new SuccessDataResult<>(Messages.LISTED, result.get());
        }
        return new ErrorDataResult<>(Messages.EMAIL_MSG + email, null);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public DataResult<User> add(RegisterRequest addUserRequest) {
        return new SuccessDataResult<>(MESSAGE + Messages.ADDED,
                userRepository.save(MapperUtil.map(addUserRequest, User.class)));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public Result update(UpdateUserRequest updateUserRequest) {
        Optional<User> result = userRepository.findById(updateUserRequest.getId());
        if (result.isPresent()) {
            userRepository.save(MapperUtil.map(updateUserRequest, User.class));
            return new SuccessResult(MESSAGE + Messages.UPDATED);
        }
        return new ErrorResult(MESSAGE + Messages.NOT_FOUND);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public Result delete(DeleteUserRequest deleteUserRequest) {
        Optional<User> result = userRepository.findById(deleteUserRequest.getId());
        if (result.isPresent()) {
            userRepository.delete(result.get());
            return new SuccessResult(MESSAGE + Messages.DELETED);
        }
        return new ErrorResult(MESSAGE + Messages.NOT_FOUND);
    }

}
