# 휴머스온 과제 전형
<과제 안내>

[데이터 연동] 주문 데이터 연동 인터페이스 설계 
- 주문 관리 시스템을 설계하고, 외부 시스템과의 데이터 연동을 위한 인터페이스를 구현하세요. 이 시스템은 외부에서 주문 데이터를 가져와 저장하고, 내부 데이터를 외부로 전송하는 기능을 제공합니다. 

## 과제 설명
- 서버구현: Spring Boot
- 테스트: Postman

- 실행방법(gradle를 사용): clean bootRun -Pprofile=local
<img width="791" alt="스크린샷 2024-10-22 오후 4 39 22" src="https://github.com/user-attachments/assets/9def80b8-8ff1-4b3b-97f0-c44baf0dcb4b">

## 프로젝트 구조 및 파일 설명
<pre>
humuson/
├── src/
│   ├── main/
│   │   ├── java/com/humuson/assignment/
│   │   │   ├── common/                    # 서버 공통 로직 및 에러 처리 정의
│   │   │        ├── error/                  1. 에러처리파일 관리 (에러 번호, 에러 핸들러, CustomException)
│   │   │        ├── model/                  2. 공통 모델 파일 관리 (ApiResponseModel 정의)
│   │   │        ├── ExternalSystem          3. 외부 API 통신 로직 구현체 
│   │   │        └── ExternalSystemClient    3-1. 외부 API 통신 인터페이스
│   │   │   └── content/order/             # API 개발 (비즈니스 로직 처리)
│   │   │        ├── controller/             4. controller 파일 관리  
│   │   │        ├── model/                  5. model 파일 관리 
│   │   │        └── service/                6. service 파일 관리
│   │   └── resources/
│   │       ├── profiles/                  # 환경별 yml 설정 분리
│   │       │   ├── dev/                     # dev 환경 설정
│   │       │   ├── local/                   # local 환경 설정
│   │       │   ├── prod/                    # prod 환경 설정
│   │       └── application-core.yml         # 공통 환경 설정 파일
└── ...
</pre>

1. 에러처리파일 관리 (에러 번호, 에러 핸들러, CustomException)
- (class) CustomException: Exception을 상속받아 자체적으로 만든 ERROR CODE값과 DATA를 갖음.
- (enum) ExceptionCode: 에러 값에 CODE와 설명을 부여
- (class) RestControllerExceptionHandler: @RestControllerAdvice를 통해서 에러 인터셉터 처리

2. 공통 모델 파일 관리 (ApiResponseModel 정의)
- (class) ApiResponseModel: 제네릭 형태로 공통 API 응답값을 정의
<pre>
ex) {
    "header": {
        "resultCode": 0, // 에러 코드값(성공시 0)
        "resultMessage": "SUCCESS" // 에러 상태
    },
    "data": 1, // 결과값
    "errorData": null // 에러 데이터
} 
</pre>

3. 외부 API 통신 로직 인터페이스 & 구현체 
- (class) ExternalSystem & (interface) ExternalSystemClient
    - 외부 시스템과의 데이터 통신을 위한 인터페이스를 정의 및 구현
    - 통신로직: RestTemplate를 통한 HTTP 통신
    - 데이터변환로직: ObjectMapper를 통한 Object <-> JSON

4. controller/
- (class) OrderController
    - getExternalOrder(): 외부데이터 요청
    - sendToExternalOrder(): 내부 데이터 -> 외부로 전송
    - getOrder(): 저장된 주문 가져옴
    - getOrders(): 저장된 주문 리스트로 가져옴

5. model/
- (class) OrderModel: 주문 ID, 고객 명, 주문 날짜, 주문 상태등의 속성 정의
- (enum) OrderStateType: 주문 상태정의

6. service/
- getExternalOrder(): 외부데이터를 가져와 객체로 변환 후 메모리에 저장
- sendToExternalOrder(): 내부에 저장된 주문을 외부로 전송
- getOrder(): 주문 id로 메모리에 저장된 주문데이터 반환
- getOrderListFromMap(): 메모리에 저장된 주문들을 리스트화하여 반환


