package com.humuson.assignment.content.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderModel {
    private Long orderId;
    private String customerName;
    private String orderData;
    private OrderStateType orderState;

    @Getter
    public static class SendOrderReq {
        private Long orderId;
    }
}
