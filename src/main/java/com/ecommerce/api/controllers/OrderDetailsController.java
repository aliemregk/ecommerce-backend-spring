package com.ecommerce.api.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.business.abstracts.OrderDetailService;
import com.ecommerce.business.requests.orderdetail.AddOrderDetailRequest;
import com.ecommerce.business.requests.orderdetail.DeleteOrderDetailRequest;
import com.ecommerce.business.requests.orderdetail.UpdateOrderDetailRequest;
import com.ecommerce.business.responses.orderdetail.GetAllByOrderIdOrderDetailResponse;
import com.ecommerce.business.responses.orderdetail.GetAllOrderDetailResponse;
import com.ecommerce.business.responses.orderdetail.GetByIdOrderDetailResponse;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

@RestController

@RequestMapping("/api/orderdetails")

@CrossOrigin
public class OrderDetailsController {

    private final OrderDetailService detailService;

    @Autowired
    public OrderDetailsController(OrderDetailService detailService) {
        this.detailService = detailService;
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(path = "/getall")
    public DataResult<List<GetAllOrderDetailResponse>> getAll() {
        return detailService.getAll();
    }

    @GetMapping(path = "/getallbyorderid")
    public DataResult<List<GetAllByOrderIdOrderDetailResponse>> getAllByOrderId(@RequestParam int id) {
        return detailService.getAllByOrderId(id);
    }

    @GetMapping(path = "/getbyid")
    public DataResult<GetByIdOrderDetailResponse> getById(@RequestParam int id) {
        return detailService.getById(id);
    }

    @PostMapping(path = "/add")
    public Result add(@RequestBody @Valid AddOrderDetailRequest addOrderRequest) {
        return detailService.add(addOrderRequest);
    }

    @PutMapping(path = "/update")
    public Result update(@RequestBody @Valid UpdateOrderDetailRequest updateOrderRequest) {
        return detailService.update(updateOrderRequest);
    }

    @DeleteMapping(path = "/delete")
    public Result delete(@RequestBody @Valid DeleteOrderDetailRequest deleteOrderRequest) {
        return detailService.delete(deleteOrderRequest);
    }
}
