package com.ecommerce.business.concretes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.OrderDetailService;
import com.ecommerce.business.abstracts.OrderService;
import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.order.AddOrderRequest;
import com.ecommerce.business.requests.order.DeleteOrderRequest;
import com.ecommerce.business.requests.order.UpdateOrderRequest;
import com.ecommerce.business.requests.orderdetail.AddOrderDetailRequest;
import com.ecommerce.business.responses.order.GetAllByUserIdOrderResponse;
import com.ecommerce.business.responses.order.GetAllOrderResponse;
import com.ecommerce.business.responses.order.GetByIdOrderResponse;
import com.ecommerce.core.utilities.mapper.MapperUtil;
import com.ecommerce.core.utilities.results.ErrorResult;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.SuccessResult;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.ErrorDataResult;
import com.ecommerce.core.utilities.results.dataresults.SuccessDataResult;
import com.ecommerce.dataaccess.abstracts.OrderRepository;
import com.ecommerce.entities.concretes.Order;
import com.ecommerce.entities.concretes.Product;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {

    private static final String MESSAGE = "Order";
    private final OrderRepository orderRepository;
    private final OrderDetailService detailService;

    @Override
    public DataResult<List<GetAllOrderResponse>> getAll() {
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(orderRepository.findAll(Sort.by(Sort.Direction.ASC, "id")),
                        GetAllOrderResponse.class));
    }

    @Override
    public DataResult<List<GetAllByUserIdOrderResponse>> getAllByUserId(int id) {
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(orderRepository.getAllByUserId(id), GetAllByUserIdOrderResponse.class));
    }

    @Override
    public DataResult<GetByIdOrderResponse> getById(int id) {
        Optional<Order> result = orderRepository.findById(id);
        if (result.isPresent()) {
            return new SuccessDataResult<>(Messages.LISTED, MapperUtil.map(result.get(), GetByIdOrderResponse.class));
        }
        return new ErrorDataResult<>(MESSAGE + Messages.NOT_FOUND, null);
    }

    @Override
    public Result add(AddOrderRequest order) {
        final Order newOrder = orderRepository.save(MapperUtil.map(order, Order.class));

        // TODO Refactor
        for (Map.Entry<Integer, Integer> entry : order.getOrderProducts().entrySet()) {
            detailService.add(new AddOrderDetailRequest(new Product(entry.getKey()),
                    newOrder, entry.getValue()));
        }

        return new SuccessResult(MESSAGE + Messages.ADDED);
    }

    @Override
    public Result update(UpdateOrderRequest order) {
        Optional<Order> result = orderRepository.findById(order.getId());
        if (result.isPresent()) {
            orderRepository.save(MapperUtil.map(order, Order.class));
            return new SuccessResult(MESSAGE + Messages.UPDATED);
        }
        return new ErrorResult(MESSAGE + Messages.NOT_FOUND);
    }

    @Override
    public Result delete(DeleteOrderRequest order) {
        Optional<Order> result = orderRepository.findById(order.getId());
        if (result.isPresent()) {
            orderRepository.delete(MapperUtil.map(order, Order.class));
            return new SuccessResult(MESSAGE + Messages.DELETED);
        }
        return new ErrorResult(MESSAGE + Messages.NOT_FOUND);
    }

}
