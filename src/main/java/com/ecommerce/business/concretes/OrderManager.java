package com.ecommerce.business.concretes;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.OrderDetailService;
import com.ecommerce.business.abstracts.OrderService;
import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.order.AddOrderRequest;
import com.ecommerce.business.requests.order.DeleteOrderRequest;
import com.ecommerce.business.requests.order.OrderDetailOrderModel;
import com.ecommerce.business.requests.order.UpdateOrderRequest;
import com.ecommerce.business.requests.orderdetail.AddOrderDetailRequest;
import com.ecommerce.business.requests.product.OrderDetailProductModel;
import com.ecommerce.business.responses.order.GetAllByUserIdOrderResponse;
import com.ecommerce.business.responses.order.GetAllOrderResponse;
import com.ecommerce.business.responses.order.GetByIdOrderResponse;
import com.ecommerce.business.rules.OrderBusinessRules;
import com.ecommerce.core.exceptions.BusinessException;
import com.ecommerce.core.utilities.mapper.MapperUtil;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.SuccessResult;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.SuccessDataResult;
import com.ecommerce.dataaccess.abstracts.OrderRepository;
import com.ecommerce.entities.concretes.Order;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {

    private static final String MESSAGE = "Order";
    private final OrderRepository orderRepository;
    private final OrderDetailService detailService;
    private final OrderBusinessRules orderBusinessRules;

    @Override
    public DataResult<List<GetAllOrderResponse>> getAll() {
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(orderRepository.findAll(Sort.by(Sort.Direction.ASC, "id")),
                        GetAllOrderResponse.class));
    }

    @Override
    public DataResult<List<GetAllByUserIdOrderResponse>> getAllByUserId(int userId) {
        orderBusinessRules.checkIfUserExists(userId);
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(orderRepository.getAllByUserId(userId), GetAllByUserIdOrderResponse.class));
    }

    @Override
    public DataResult<GetByIdOrderResponse> getById(int id) {
        Order result = orderRepository.findById(id)
                .orElseThrow(() -> new BusinessException("No order found with given ID."));
        return new SuccessDataResult<>(Messages.LISTED, MapperUtil.map(result, GetByIdOrderResponse.class));
    }

    @Override
    public Result add(AddOrderRequest addOrderRequest) {
        orderBusinessRules.checkIfUserExists(addOrderRequest.getUser().getId());
        final Order newOrder = orderRepository.save(MapperUtil.map(addOrderRequest, Order.class));
        final OrderDetailOrderModel orderForOrderDetail = MapperUtil.map(newOrder, OrderDetailOrderModel.class);
        for (Map.Entry<Integer, Integer> entry : addOrderRequest.getOrderProducts().entrySet()) {
            detailService.add(new AddOrderDetailRequest(new OrderDetailProductModel(entry.getKey()),
                    orderForOrderDetail, entry.getValue()));
        }
        return new SuccessResult(MESSAGE + Messages.ADDED);
    }

    @Override
    public Result update(UpdateOrderRequest updateOrderRequest) {
        orderBusinessRules.checkIfOrderExists(updateOrderRequest.getId());
        orderBusinessRules.checkIfUserExists(updateOrderRequest.getUser().getId());
        orderRepository.save(MapperUtil.map(updateOrderRequest, Order.class));
        return new SuccessResult(MESSAGE + Messages.UPDATED);
    }

    @Override
    public Result delete(DeleteOrderRequest deleteOrderRequest) {
        orderBusinessRules.checkIfOrderExists(deleteOrderRequest.getId());
        orderRepository.deleteById(deleteOrderRequest.getId());
        return new SuccessResult(MESSAGE + Messages.DELETED);
    }

}
