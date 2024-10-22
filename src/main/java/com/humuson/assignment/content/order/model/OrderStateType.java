package com.humuson.assignment.content.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public enum OrderStateType implements Serializable {

    PROCESSING("PROCESSING")
    ,DELIVERING("DELIVERING")
    ,COMPLETION("COMPLETION");

    private final String state;

}
