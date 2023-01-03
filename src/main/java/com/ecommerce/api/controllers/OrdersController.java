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

import com.ecommerce.business.abstracts.OrderService;
import com.ecommerce.business.requests.order.AddOrderRequest;
import com.ecommerce.business.requests.order.DeleteOrderRequest;
import com.ecommerce.business.requests.order.UpdateOrderRequest;
import com.ecommerce.business.responses.order.GetAllByUserIdOrderResponse;
import com.ecommerce.business.responses.order.GetAllOrderResponse;
import com.ecommerce.business.responses.order.GetByIdOrderResponse;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrdersController {

    private final OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(path = "/getall")
    public DataResult<List<GetAllOrderResponse>> getAll() {
        return orderService.getAll();
    }

    @GetMapping(path = "/getallbyuserid")
    public DataResult<List<GetAllByUserIdOrderResponse>> getAllByUserId(@RequestParam int id) {
        return orderService.getAllByUserId(id);
    }

    @GetMapping(path = "/getbyid")
    public DataResult<GetByIdOrderResponse> getById(@RequestParam int id) {
        return orderService.getById(id);
    }

    @PostMapping(path = "/add")
    public Result add(@RequestBody @Valid AddOrderRequest addOrderRequest) {
        return orderService.add(addOrderRequest);
    }

    @PutMapping(path = "/update")
    public Result update(@RequestBody @Valid UpdateOrderRequest updateOrderRequest) {
        return orderService.update(updateOrderRequest);
    }

    @DeleteMapping(path = "/delete")
    public Result delete(@RequestBody @Valid DeleteOrderRequest deleteOrderRequest) {
        return orderService.delete(deleteOrderRequest);
    }
}
