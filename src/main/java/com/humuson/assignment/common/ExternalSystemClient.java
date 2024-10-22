package com.humuson.assignment.common;

import com.humuson.assignment.common.error.CustomException;
import com.humuson.assignment.content.order.model.OrderModel;

public interface ExternalSystemClient {

    /**
     * 외부 시스템으로부터 데이터를 가져오는 메서드
     *
     * @param url         외부 시스템의 API 엔드포인트
     * @param requestBody 요청 본문
     * @return 외부 시스템에서 받은 응답 데이터
     * @throws CustomException 데이터 가져오기 실패 시 발생하는 예외
     */
    String getFetchData(String url, String requestBody) throws CustomException;

    /**
     * 외부 시스템에 데이터를 전송하는 메서드
     *
     * @param url  외부 시스템의 API 엔드포인트
     * @param data 전송할 데이터
     * @throws CustomException 데이터 전송 실패 시 발생하는 예외
     */
    String postSendData(String url, String data) throws CustomException;

    /**
     * JSON 문자열을 Object 객체로 변환하는 메서드
     *
     * @param jsonData JSON 문자열
     * @return 변환된 Object 객체
     * @throws CustomException 변환 실패 시 발생하는 예외
     */
    <T> T parseObjectData(String jsonData, Class<T> classType) throws CustomException;

    /**
     * Object 객체를 JSON 문자열로 변환하는 메서드
     *
     * @param object 변환할 Object 객체
     * @return JSON 문자열
     * @throws CustomException 변환 실패 시 발생하는 예외
     */
    String convertObjectToJson(Object object) throws CustomException;
}
