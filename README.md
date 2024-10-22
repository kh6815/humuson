# 휴머스온 과제 전형
<과제 안내>

[데이터 연동] 주문 데이터 연동 인터페이스 설계 

주문 관리 시스템을 설계하고, 외부 시스템과의 데이터 연동을 위한 인터페이스를 구현하세요. 이 시스템은 외부에서 주문 데이터를 가져와 저장하고, 내부 데이터를 외부로 전송하는 기능을 제공합니다. 

## 프로젝트 구조
<pre>
humuson/
├── src/
│   ├── main/
│   │   ├── java/com/humuson/assignment/
│   │   │   ├── common/                    # 서버 공통 로직 및 에러 처리 정의
│   │   │        ├── error/                  # 에러처리파일 관리 (에러 번호, 에러 핸들러, CustomException)
│   │   │        ├── model/                  # 공통 모델 파일 관리 (ApiResponseModel 정의)
│   │   │        ├── ExternalSystem          # 외부 API 통신 로직 구현체 
│   │   │        └── ExternalSystemClient    # 외부 API 통신 인터페이스
│   │   │   └── content/order/             # API 개발 (비즈니스 로직 처리)
│   │   │        ├── controller/             # controller 파일 관리  
│   │   │        ├── model/                  # model 파일 관리 
│   │   │        └── service/                # service 파일 관리
│   │   └── resources/
│   │       ├── profiles/                  # 환경별 yml 설정 분리
│   │       │   ├── dev/                     # dev 환경 설정
│   │       │   ├── local/                   # local 환경 설정
│   │       │   ├── prod/                    # prod 환경 설정
│   │       └── application-core.yml         # 공통 환경 설정 파일
└── ...
</pre>

## 과제 구현 설명
### 1. 각 클래스 설명
- 


