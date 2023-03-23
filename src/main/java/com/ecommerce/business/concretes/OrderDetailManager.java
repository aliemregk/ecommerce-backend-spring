package com.ecommerce.business.concretes;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.OrderDetailService;
import com.ecommerce.business.abstracts.ProductService;
import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.orderdetail.AddOrderDetailRequest;
import com.ecommerce.business.requests.orderdetail.DeleteOrderDetailRequest;
import com.ecommerce.business.requests.orderdetail.UpdateOrderDetailRequest;
import com.ecommerce.business.responses.orderdetail.GetAllByOrderIdOrderDetailResponse;
import com.ecommerce.business.responses.orderdetail.GetAllOrderDetailResponse;
import com.ecommerce.business.responses.orderdetail.GetByIdOrderDetailResponse;
import com.ecommerce.business.rules.OrderDetailBusinessRules;
import com.ecommerce.core.exceptions.BusinessException;
import com.ecommerce.core.utilities.mapper.MapperUtil;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.SuccessResult;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.SuccessDataResult;
import com.ecommerce.dataaccess.abstracts.OrderDetailRepository;
import com.ecommerce.entities.concretes.OrderDetail;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderDetailManager implements OrderDetailService {

    private static final String MESSAGE = "Order detail";
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailBusinessRules orderDetailBusinessRules;
    private final ProductService productService;

    @Override
    public DataResult<List<GetAllOrderDetailResponse>> getAll() {
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(orderDetailRepository.findAll(Sort.by(Sort.Direction.ASC, "id")),
                        GetAllOrderDetailResponse.class));
    }

    @Override
    public DataResult<List<GetAllByOrderIdOrderDetailResponse>> getAllByOrderId(int id) {
        orderDetailBusinessRules.checkIfOrderExists(id);
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(orderDetailRepository.getAllByOrderId(id), GetAllByOrderIdOrderDetailResponse.class));
    }

    @Override
    public DataResult<GetByIdOrderDetailResponse> getById(int id) {
        OrderDetail result = orderDetailRepository.findById(id)
                .orElseThrow(() -> new BusinessException("No order detail found with given ID."));
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.map(result, GetByIdOrderDetailResponse.class));
    }

    @Override
    public Result add(AddOrderDetailRequest addOrderDetailRequest) {
        orderDetailBusinessRules.checkIfOrderExists(addOrderDetailRequest.getOrder().getId());
        orderDetailBusinessRules.checkIfOrderProductExists(addOrderDetailRequest.getProduct().getId());
        orderDetailRepository.save(MapperUtil.map(addOrderDetailRequest, OrderDetail.class));
        productService.changeStock(addOrderDetailRequest.getProduct().getId(), addOrderDetailRequest.getQuantity());
        return new SuccessResult(MESSAGE + Messages.ADDED);
    }

    @Override
    public Result update(UpdateOrderDetailRequest updateOrderDetailRequest) {
        orderDetailBusinessRules.checkIfOrderDetailExists(updateOrderDetailRequest.getId());
        orderDetailBusinessRules.checkIfOrderExists(updateOrderDetailRequest.getOrder().getId());
        orderDetailBusinessRules.checkIfOrderProductExists(updateOrderDetailRequest.getProduct().getId());
        orderDetailRepository.save(MapperUtil.map(updateOrderDetailRequest, OrderDetail.class));
        return new SuccessResult(MESSAGE + Messages.UPDATED);
    }

    @Override
    public Result delete(DeleteOrderDetailRequest deleteOrderDetailRequest) {
        orderDetailBusinessRules.checkIfOrderDetailExists(deleteOrderDetailRequest.getId());
        orderDetailRepository.deleteById(deleteOrderDetailRequest.getId());
        return new SuccessResult(MESSAGE + Messages.DELETED);
    }

}
