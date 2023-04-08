package com.ecommerce.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.business.abstracts.ImageService;
import com.ecommerce.business.requests.image.AddImageRequest;
import com.ecommerce.business.requests.image.DeleteImageRequest;
import com.ecommerce.business.requests.image.UpdateImageRequest;
import com.ecommerce.business.responses.image.GetAllImageResponse;
import com.ecommerce.business.responses.image.GetByIdImageResponse;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
@AllArgsConstructor
public class ImagesController {

    private ImageService imageService;

    @GetMapping(path = "/getall")
    public DataResult<List<GetAllImageResponse>> getAll() {
        return imageService.getAll();
    }

    @GetMapping(path = "/getbyid")
    public DataResult<GetByIdImageResponse> getById(@RequestParam int id) {
        return imageService.getById(id);
    }

    @PostMapping(path = "/add")
    public Result add(@RequestBody @Valid AddImageRequest addImageRequest) {
        return imageService.add(addImageRequest);
    }

    @PutMapping(path = "/update")
    public Result update(@RequestBody @Valid UpdateImageRequest updateImageRequest) {
        return imageService.update(updateImageRequest);
    }

    @DeleteMapping(path = "/delete")
    public Result delete(@RequestBody @Valid DeleteImageRequest deleteImageRequest) {
        return imageService.delete(deleteImageRequest);
    }
}
