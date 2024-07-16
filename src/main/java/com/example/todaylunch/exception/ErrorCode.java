package com.example.todaylunch.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    /*
     * 400 : Bad Request (클라이언트가 잘못된 요청을 해서 서버가 요청을 처리할 수 없음)
     * 401 : Unauthorized (클라이언트가 해당 리소스에 대한 인증이 필요함)
     * 403 : Forbidden (서버가 요청을 이해했지만 승인을 거부함)
     * 404 : Not Found (요청 리소스를 찾을 수 없음)
     * 500 : Internal Server Error (서버 문제로 오류 발생)
     * 503 : Service Unavailable (서비스 이용 불가)
     */
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Method Not Allowed"),
    NULL_POINT_ERROR(404, "C005", "Null Point Exception"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),
    INTERNAL_SERVER_ERROR(500, "C007", "Internal Server Error"),
    //Auth-Token
    ACCESS_TOKEN_EXPIRED(401, "JWT_001", "Access Token Expired"),
    REFRESH_TOKEN_EXPIRED(403, "JWT_002", "Refresh Token Expired"),
    ACCESS_TOKEN_NOT_FOUND(404, "JWT_003", "토큰을 찾을 수 없습니다"),
    INVALID_TOKEN_SIGNATURE(403, "JWT_004", "Invalid Token"),
    INVALID_CLAIM(401, "JWT_005", "Invalid Claim");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
