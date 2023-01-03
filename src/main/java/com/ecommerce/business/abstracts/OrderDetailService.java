package com.ecommerce.business.abstracts;

import java.util.List;

import com.ecommerce.business.requests.orderdetail.AddOrderDetailRequest;
import com.ecommerce.business.requests.orderdetail.DeleteOrderDetailRequest;
import com.ecommerce.business.requests.orderdetail.UpdateOrderDetailRequest;
import com.ecommerce.business.responses.orderdetail.GetAllByOrderIdOrderDetailResponse;
import com.ecommerce.business.responses.orderdetail.GetAllOrderDetailResponse;
import com.ecommerce.business.responses.orderdetail.GetByIdOrderDetailResponse;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

public interface OrderDetailService {

    DataResult<List<GetAllOrderDetailResponse>> getAll();

    DataResult<List<GetAllByOrderIdOrderDetailResponse>> getAllByOrderId(int id);

    DataResult<GetByIdOrderDetailResponse> getById(int id);

    Result add(AddOrderDetailRequest order);

    Result update(UpdateOrderDetailRequest order);

    Result delete(DeleteOrderDetailRequest id);
}
