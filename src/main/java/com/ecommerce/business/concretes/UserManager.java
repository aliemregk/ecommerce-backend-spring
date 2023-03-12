package com.ecommerce.business.concretes;

import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.UserService;
import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.user.DeleteUserRequest;
import com.ecommerce.business.requests.user.UpdateUserRequest;
import com.ecommerce.business.responses.user.GetAllUserResponse;
import com.ecommerce.business.responses.user.GetByIdUserResponse;
import com.ecommerce.business.rules.UserBusinessRules;
import com.ecommerce.core.dataaccess.UserRepository;
import com.ecommerce.core.entities.User;
import com.ecommerce.core.exceptions.BusinessException;
import com.ecommerce.core.utilities.mapper.MapperUtil;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.SuccessResult;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.SuccessDataResult;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private static final String MESSAGE = "User";
    private final UserRepository userRepository;
    private final UserBusinessRules userBusinessRules;

    @Cacheable(value = "users")
    @Override
    public DataResult<List<GetAllUserResponse>> getAll() {
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(userRepository.findAll(Sort.by(Sort.Direction.ASC, "id")), GetAllUserResponse.class));
    }

    @Override
    public DataResult<GetByIdUserResponse> getById(int id) {
        User result = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("No user found with given ID."));
        return new SuccessDataResult<>(Messages.LISTED, MapperUtil.map(result, GetByIdUserResponse.class));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public Result update(UpdateUserRequest updateUserRequest) {
        userBusinessRules.checkIfUserEmailChanged(updateUserRequest.getId(), updateUserRequest.getEmail());
        userRepository.save(MapperUtil.map(updateUserRequest, User.class));
        return new SuccessResult(MESSAGE + Messages.UPDATED);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public Result delete(DeleteUserRequest deleteUserRequest) {
        userBusinessRules.checkIfUserExists(deleteUserRequest.getId());
        userRepository.deleteById(deleteUserRequest.getId());
        return new SuccessResult(MESSAGE + Messages.DELETED);
    }

}
