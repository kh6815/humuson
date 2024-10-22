package com.humuson.assignment.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    /*
    * HTTP
    * */
    // 잘못된 요청
    BAD_REQUEST(400, "Bad Request", HttpStatus.BAD_REQUEST),

    // 잘못된 id 요청
    BAD_ID_REQUEST(400, "Bad ID Request", HttpStatus.BAD_REQUEST),

    // 잘못된 pw 요청
    BAD_PW_REQUEST(400, "Bad PW Request", HttpStatus.BAD_REQUEST),

    // 인증 실패
    UNAUTHORIZED(401, "Unauthorized", HttpStatus.UNAUTHORIZED),

    // 접근 거부
    FORBIDDEN(403, "Forbidden", HttpStatus.FORBIDDEN),

    // 리소스 없음
    NOT_FOUND(404, "Not Found", HttpStatus.NOT_FOUND),

    // 서버 에러
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),

    /*
    * Custom ErrorCode
    * */

    // 네트워크 에러
    NETWORK_ERROR(1001, "Network Error", HttpStatus.OK),

    // Http 요청중 메세지 변환 에러 (Http 통신 중에 데이터가 JSON 형식이 아닌 경우)
    MESSAGE_CONVERT_ERROR(1002, "Message Convert Error", HttpStatus.OK),

    // JSON -> Object parse 에러
    JSON_PARSE_ERROR(1003, "JSON To Object Parse Error", HttpStatus.OK),

    // Object -> JSON parse 에러
    JSON_WRITE_ERROR(1004, "Object To JSON parse Error", HttpStatus.OK),

    // 잘못된 요청 데이터 (필수 필드가 없거나 형식이 맞지 않을 때)
    INVALID_ARGUMENT(1005, "Invalid Argument", HttpStatus.OK),

    // 알수없는 오류
    UNKNOWN_ERROR(2000, "UNKNOWN ERROR", HttpStatus.OK),

    // 존재하지 않음
    NOT_EXIST(2001, "NOT EXIST", HttpStatus.OK),

    // 삭제됨
    DELETED(2002, "DELETED", HttpStatus.OK),

    // 이미 존재함
    ALREADY_EXIST(2003, "ALREADY EXIST", HttpStatus.OK),

    // 유효하지 않음
    INVALID(2004, "INVALID", HttpStatus.OK)
    ;

    private final Integer resultCode;
    private final String resultMessage;
    private final HttpStatus statusCode;
}
