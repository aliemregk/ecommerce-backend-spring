package com.ecommerce.business.abstracts;

import java.util.List;

import com.ecommerce.business.requests.order.AddOrderRequest;
import com.ecommerce.business.requests.order.DeleteOrderRequest;
import com.ecommerce.business.requests.order.UpdateOrderRequest;
import com.ecommerce.business.responses.order.GetAllByUserIdOrderResponse;
import com.ecommerce.business.responses.order.GetAllOrderResponse;
import com.ecommerce.business.responses.order.GetByIdOrderResponse;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

public interface OrderService {

    DataResult<List<GetAllOrderResponse>> getAll();

    DataResult<List<GetAllByUserIdOrderResponse>> getAllByUserId(int id);

    DataResult<GetByIdOrderResponse> getById(int id);

    Result add(AddOrderRequest order);

    Result update(UpdateOrderRequest order);

    Result delete(DeleteOrderRequest id);
}
