package com.humuson.assignment.content.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.humuson.assignment.common.ExternalSystem;
import com.humuson.assignment.common.error.CustomException;
import com.humuson.assignment.common.error.ExceptionCode;
import com.humuson.assignment.content.order.model.OrderModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final ExternalSystem externalSystem;
    private Map<Long, OrderModel> orderDataMap = new HashMap<>();

    private String externalOrderUrl = "https://713f37a2-13fa-4736-b0a7-e4f3d652d20b.mock.pstmn.io//mock-server/order";

    public Long getExternalOrder() throws CustomException {
        // 1. 데이터를 가져오기 -> 외부 데이터 get, send폼 공통화
        // 1-1. 데이터 가져올 때 에러 처리하기는 externalSystem에서 공통화
//        String requestBody = "{\"orderId\": \"" + orderId + "\"}";
        String response = externalSystem.getFetchData(externalOrderUrl + "/get", null);

        // 2. 가져온 JSON 데이터 -> OrderModel로 변환
        // 2-1. 데이터를 변환할때 에러 처리하기 parseOrderData함수에서 공통화 (DataFormatException)
        OrderModel orderModel = externalSystem.parseObjectData(response, OrderModel.class);
        // 3. 가져온 데이터(OrderModel) 내부 메모리(map)에 저장하기
        orderDataMap.put(orderModel.getOrderId(), orderModel);

        return orderModel.getOrderId();
    }

    public Long sendToExternalOrder(OrderModel.SendOrderReq sendOrderReq) throws CustomException {

        OrderModel order = getOrder(sendOrderReq.getOrderId());

        // 1. orderData -> JSON 형태로 변경
        // 1-1. convertOrderToJson 내부에서 변환시 에러처리 공통화
        String jsonData = externalSystem.convertObjectToJson(order);

        // 2. 데이터 send
        // 2-1. externalSystem안에서 에러처리 공통화
        String response = externalSystem.postSendData(externalOrderUrl + "/send", jsonData);

        if(response == null) {
            throw new CustomException(ExceptionCode.NOT_EXIST, "응답값 없음");
        }

        OrderModel orderModel = externalSystem.parseObjectData(response, OrderModel.class);
        return orderModel.getOrderId();
    }

    // Map에 저장된 order 데이터 orderId로 가져오기
    public OrderModel getOrder(Long orderId) throws CustomException {
        OrderModel orderModel = orderDataMap.get(orderId);

        if(orderModel == null){
            throw new CustomException(ExceptionCode.NOT_EXIST, "존재하지않은 주문");
        }

        return orderModel;
    }

    // Map에서 저장된 모든 데이터를 list형태로 반환하는 기능 필요
    public List<OrderModel> getOrderListFromMap() {
        return new ArrayList<>(orderDataMap.values());
    }
}
