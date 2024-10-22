package com.humuson.assignment.content.order.controller;

import com.humuson.assignment.common.error.CustomException;
import com.humuson.assignment.common.model.ApiResponseModel;
import com.humuson.assignment.content.order.model.OrderModel;
import com.humuson.assignment.content.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ApiResponseModel<Long> getExternalOrder() throws CustomException {
        return new ApiResponseModel<>(orderService.getExternalOrder());
    }

    @PostMapping("/send")
    public ApiResponseModel<Long> sendToExternalOrder(@RequestBody OrderModel.SendOrderReq sendOrderReq) throws CustomException {
        return new ApiResponseModel<>(orderService.sendToExternalOrder(sendOrderReq));
    }

    @GetMapping("/{orderId}")
    public ApiResponseModel<OrderModel> getOrder(@PathVariable("orderId") Long orderId) throws CustomException {
        return new ApiResponseModel<>(orderService.getOrder(orderId));
    }

    @GetMapping("/list")
    public ApiResponseModel<List<OrderModel>> getOrders() {
        return new ApiResponseModel<>(orderService.getOrderListFromMap());
    }
}
