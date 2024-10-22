package com.humuson.assignment.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.humuson.assignment.common.error.CustomException;
import com.humuson.assignment.common.error.ExceptionCode;
import com.humuson.assignment.content.order.model.OrderModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

@Component
public class ExternalSystem implements ExternalSystemClient{

    // 데이터를 외부 시스템에서 가져오는 메서드
    @Override
    public String getFetchData(String url, String requestBody) throws CustomException {
        try {
            // HTTP 요청을 보내 외부 시스템에서 데이터를 가져오는 로직
            // RestTemplate 등을 사용하여 구현 가능
            // 예시: JSON 형식 데이터 수신
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            // 클라이언트 측 에러 (4xx)
            throw new CustomException(ExceptionCode.BAD_REQUEST, "클라이언트 요청 오류: " + e.getMessage());
        } catch (HttpServerErrorException e) {
            // 서버 측 에러 (5xx)
            throw new CustomException(ExceptionCode.INTERNAL_SERVER_ERROR, "서버 응답 오류: " + e.getMessage());
        } catch (ResourceAccessException e) {
            // 네트워크 오류
            throw new CustomException(ExceptionCode.NETWORK_ERROR, "네트워크 오류: " + e.getMessage());
        } catch (HttpMessageConversionException e) {
            // Http 응답데이터 파싱 오류 (JSON 파싱 오류)
            throw new CustomException(ExceptionCode.MESSAGE_CONVERT_ERROR, "응답 데이터 파싱 오류: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // 잘못된 요청 파라미터
            throw new CustomException(ExceptionCode.INVALID_ARGUMENT, "잘못된 요청 데이터: " + e.getMessage());
        } catch (RestClientException e) {
            // 그 외 RestTemplate 관련 예외
            throw new CustomException(ExceptionCode.INTERNAL_SERVER_ERROR, "예상치 못한 오류 발생: " + e.getMessage());
        }
    }

    // 데이터를 외부 시스템으로 보내는 메서드
    @Override
    public String postSendData(String url, String data) throws CustomException {
        try {
            // HTTP 요청을 보내 데이터를 외부 시스템으로 전송하는 로직
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(data, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            // 4xx 클라이언트 오류 처리 (잘못된 요청 등)
            throw new CustomException(ExceptionCode.BAD_REQUEST, "클라이언트 요청 오류: " + e.getMessage());
        } catch (HttpServerErrorException e) {
            // 5xx 서버 오류 처리
            throw new CustomException(ExceptionCode.INTERNAL_SERVER_ERROR, "서버 오류 발생: " + e.getMessage());
        } catch (ResourceAccessException e) {
            // 네트워크 연결 오류 (외부 시스템에 접근 불가)
            throw new CustomException(ExceptionCode.NETWORK_ERROR, "네트워크 오류: " + e.getMessage());
        } catch (HttpMessageConversionException e) {
            // Http 요청데이터 파싱 오류 (JSON 파싱 오류)
            throw new CustomException(ExceptionCode.MESSAGE_CONVERT_ERROR, "요청 데이터 파싱 오류: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // 잘못된 URL 또는 요청 파라미터
            throw new CustomException(ExceptionCode.INVALID_ARGUMENT, "잘못된 요청 데이터: " + e.getMessage());
        } catch (RestClientException e) {
            // 그 외 RestTemplate 관련 예외
            throw new CustomException(ExceptionCode.INTERNAL_SERVER_ERROR, "예상치 못한 오류 발생: " + e.getMessage());
        }
    }

    @Override
    public <T> T parseObjectData(String jsonData, Class<T> classType) throws CustomException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // JSON 문자열을 OrderModel 객체로 변환
            return objectMapper.readValue(jsonData, classType);
        } catch (JsonProcessingException e) {
            // JSON 데이터 파싱 실패 시 예외 처리
            throw new CustomException(ExceptionCode.JSON_PARSE_ERROR, "JSON 변환 오류: " + e.getMessage());
        }
    }

    @Override
    public String convertObjectToJson(Object object) throws CustomException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // OrderModel 객체를 JSON 문자열로 변환
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            // JSON 변환 실패 시 예외 처리
            throw new CustomException(ExceptionCode.JSON_WRITE_ERROR, "JSON 변환 오류: " + e.getMessage());
        }
    }
}
