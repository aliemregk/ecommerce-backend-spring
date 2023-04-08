package com.ecommerce.business.abstracts;

import java.util.List;

import com.ecommerce.business.requests.image.AddImageRequest;
import com.ecommerce.business.requests.image.DeleteImageRequest;
import com.ecommerce.business.requests.image.UpdateImageRequest;
import com.ecommerce.business.responses.image.GetAllImageResponse;
import com.ecommerce.business.responses.image.GetByIdImageResponse;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

public interface ImageService {

    DataResult<List<GetAllImageResponse>> getAll();

    DataResult<GetByIdImageResponse> getById(int id);

    Result add(AddImageRequest addImageRequest);

    Result update(UpdateImageRequest updateImageRequest);

    Result delete(DeleteImageRequest deleteImageRequest);
}
