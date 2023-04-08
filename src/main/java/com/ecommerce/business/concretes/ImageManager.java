package com.ecommerce.business.concretes;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.ImageService;
import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.image.AddImageRequest;
import com.ecommerce.business.requests.image.DeleteImageRequest;
import com.ecommerce.business.requests.image.UpdateImageRequest;
import com.ecommerce.business.responses.image.GetAllImageResponse;
import com.ecommerce.business.responses.image.GetByIdImageResponse;
import com.ecommerce.business.rules.ImageBusinessRules;
import com.ecommerce.core.exceptions.BusinessException;
import com.ecommerce.core.utilities.mapper.MapperUtil;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.SuccessResult;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.SuccessDataResult;
import com.ecommerce.dataaccess.abstracts.ImageRepository;
import com.ecommerce.entities.concretes.Image;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImageManager implements ImageService {

    private static final String MESSAGE = "Image";
    private ImageRepository imageRepository;
    private ImageBusinessRules imageBusinessRules;

    @Override
    public DataResult<List<GetAllImageResponse>> getAll() {
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(imageRepository.findAll(Sort.by(Sort.Direction.ASC, "id")),
                        GetAllImageResponse.class));
    }

    @Override
    public DataResult<GetByIdImageResponse> getById(int id) {
        Image result = imageRepository.findById(id)
                .orElseThrow(() -> new BusinessException("No image found with given ID."));
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.map(result, GetByIdImageResponse.class));
    }

    @Override
    public Result add(AddImageRequest addImageRequest) {
        imageBusinessRules.checkIfUrlExists(addImageRequest.getUrl());
        imageRepository.save(MapperUtil.map(addImageRequest, Image.class));
        return new SuccessResult(MESSAGE + Messages.ADDED);
    }

    @Override
    public Result update(UpdateImageRequest updateImageRequest) {
        imageBusinessRules.checkIfImageExists(updateImageRequest.getId());
        imageRepository.save(MapperUtil.map(updateImageRequest, Image.class));
        return new SuccessResult(MESSAGE + Messages.UPDATED);
    }

    @Override
    public Result delete(DeleteImageRequest deleteImageRequest) {
        imageBusinessRules.checkIfImageExists(deleteImageRequest.getId());
        imageRepository.deleteById(deleteImageRequest.getId());
        return new SuccessResult(MESSAGE + Messages.DELETED);
    }

}
