package com.ecommerce.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.OrderDetailService;
import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.orderdetail.AddOrderDetailRequest;
import com.ecommerce.business.requests.orderdetail.DeleteOrderDetailRequest;
import com.ecommerce.business.requests.orderdetail.UpdateOrderDetailRequest;
import com.ecommerce.business.responses.orderdetail.GetAllByOrderIdOrderDetailResponse;
import com.ecommerce.business.responses.orderdetail.GetAllOrderDetailResponse;
import com.ecommerce.business.responses.orderdetail.GetByIdOrderDetailResponse;
import com.ecommerce.core.utilities.mapper.MapperUtil;
import com.ecommerce.core.utilities.results.ErrorResult;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.SuccessResult;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.ErrorDataResult;
import com.ecommerce.core.utilities.results.dataresults.SuccessDataResult;
import com.ecommerce.dataaccess.abstracts.OrderDetailRepository;
import com.ecommerce.entities.concretes.OrderDetail;

@Service
public class OrderDetailManager implements OrderDetailService {

    private static final String MESSAGE = "Order detail";
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailManager(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public DataResult<List<GetAllOrderDetailResponse>> getAll() {
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(orderDetailRepository.findAll(Sort.by(Sort.Direction.ASC, "id")),
                        GetAllOrderDetailResponse.class));
    }

    @Override
    public DataResult<List<GetAllByOrderIdOrderDetailResponse>> getAllByOrderId(int id) {
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(orderDetailRepository.getAllByOrderId(id), GetAllByOrderIdOrderDetailResponse.class));
    }

    @Override
    public DataResult<GetByIdOrderDetailResponse> getById(int id) {
        Optional<OrderDetail> result = orderDetailRepository.findById(id);
        if (result.isPresent()) {
            return new SuccessDataResult<>(Messages.LISTED,
                    MapperUtil.map(result.get(), GetByIdOrderDetailResponse.class));
        }
        return new ErrorDataResult<>(MESSAGE + Messages.NOT_FOUND, null);
    }

    @Override
    public Result add(AddOrderDetailRequest orderDetail) {
        orderDetailRepository.save(MapperUtil.map(orderDetail, OrderDetail.class));
        return new SuccessResult(MESSAGE + Messages.ADDED);
    }

    @Override
    public Result update(UpdateOrderDetailRequest orderDetail) {
        Optional<OrderDetail> result = orderDetailRepository.findById(orderDetail.getId());
        if (result.isPresent()) {
            orderDetailRepository.save(MapperUtil.map(orderDetail, OrderDetail.class));
            return new SuccessResult(MESSAGE + Messages.UPDATED);
        }
        return new ErrorResult(MESSAGE + Messages.NOT_FOUND);
    }

    @Override
    public Result delete(DeleteOrderDetailRequest orderDetail) {
        Optional<OrderDetail> result = orderDetailRepository.findById(orderDetail.getId());
        if (result.isPresent()) {
            orderDetailRepository.delete(MapperUtil.map(orderDetail, OrderDetail.class));
            return new SuccessResult(MESSAGE + Messages.DELETED);
        }
        return new ErrorResult(MESSAGE + Messages.NOT_FOUND);
    }

}
