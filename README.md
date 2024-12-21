# 🏠 iM집사
## 🏠 목차
-  [🏠 Project](#-project)
- [🏠 Design](#-design)  
  - [용어 정의](#용어-정의)  
  - [클래스 다이어그램](#클래스-다이어그램)  
  - [ERD](#erd)  
  - [Skills](#skills)  
  - [공공데이터 포털 API](#공공데이터-포털-api)  
  - [네이버 지도 API - Geocoding](#네이버-지도-api---geocoding)  
  - [주택 정보 업데이트 플로우](#주택-정보-업데이트-플로우)  
- [🏠 Getting Started](#-getting-started)  
   - [1. application.yml 추가](#1-applciationyml-추가)  
   - [2. docker compose 실행](#2-docker-compose-실행)  
   - [3. 원하는 조건에 맞게 테스트 코드 수정](#3-원하는-조건에-맞게-테스트-코드-수정)  


## 🏠 Project
> - 사용자 자산 맞춤형 주택 검색 서비스
> - 공공데이터 포털 API를 통한 주택 실거래가 데이터 조회 테스트 코드 제공
> - 네이버 지도 API를 통한 주소(읍면동 + 지번)을 위치(위도, 경도)로 변환하는 테스트 코드 제공
> - 기존에 저장된 주택을 데이터를 기반으로 필터링(거리, 매매가, 월세) 검색 기능에 대한 테스트 코드 제공
> - 공공데이터 포털 API + 네이버 지도 API를 활용한 사용자 DB에 주택 데이터 저장 API 제공
<br>

## 🏠 Design
### 용어 정의
| **영문**           | **한글**        |
|---------------------|----------------|
| Apartment           | 아파트         |
| Detached House      | 단독/다가구     |
| Multiplex House     | 연립다세대      |
| Officetel           | 오피스텔       |

### 클래스 다이어그램
![image](https://github.com/user-attachments/assets/3c0f49f8-974c-4598-9e3a-c85efdbf8030)

### ERD
![image](https://github.com/user-attachments/assets/d4ca6b88-d8dd-4c76-b38f-11ce41e3a6de)


### Skills
`Spring Boot 3.1`<br>
`JPA`, `QueryDSL`<br>
`MySQL`, `hibernate-spatial`<br>
`Docker`, `docker-compse`

<br>

### 공공데이터 포털 API
**[ 활용 링크 ]**
- [국토교통부_아파트 매매 실거래가 자료](https://www.data.go.kr/data/15126469/openapi.do)
- [국토교통부_아파트 전월세 실거래가 자료](https://www.data.go.kr/data/15126474/openapi.do)
- [국토교통부_단독/다가구 매매 실거래가 자료](https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15126465)
- [국토교통부_단독/다가구 전월세 실거래가 자료](https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15126472)
- [국토교통부_연립다세대 매매 실거래가 자료](https://www.data.go.kr/data/15126467/openapi.do)
- [국토교통부_연립다세대 전월세 실거래가 자료](https://www.data.go.kr/data/15126473/openapi.do)
- [국토교통부_오피스텔 매매 실거래가 자료](https://www.data.go.kr/data/15126464/openapi.do)
- [국토교통부_오피스텔 전월세 실거래가 자료](https://www.data.go.kr/data/15126475/openapi.do)

**[ API Response 분석 ]**
| **항목**                | **아파트 전월세** | **아파트 매매** | **단독다가구 전월세** | **단독다가구 매매** | **오피스텔 전월세** | **오피스텔 매매** | **연립다세대 전월세** | **연립다세대 매매** |
|--------------------------|------------------|----------------|----------------------|--------------------|--------------------|------------------|-----------------------|---------------------|
| **umdNm (법정동)**       | o                | o              | o                    | o                  | o                  | o                | o                     | o                   |
| **jibun (지번)**         | o                | o              |                      |                    | o                  | o                | o                     | o                   |
| **sggNm (시군구)**       |                  |                |                      |                    |                    |                  | o                     | o                   |
| **dealAmount (거래금액)**|                  | o              |                      | o                  |                    | o                |                      | o                   |
| **deposit (보증금)**     | o                |                | o                    |                    | o                  |                  | o                     |                     |
| **monthlyRent (월세)**   | o                |                | o                    |                    | o                  |                  | o                     |                     |
| **houseType (단독 or 다가구)** |                  |                | o                    | o                  |                    |                  |                       |                     |
| **aptNm (아파트명)**     | o                | o              |                      |                    |                    |                  |                       |                     |
| **offiNm (오피스텔명)**  |                  |                |                      |                    | o                  | o                |                       |                     |
| **mhouseNm (단지명)**    |                  |                |                      |                    |                    |                  | o                     | o                   |

<br>

### 네이버 지도 API - Geocoding
**[ 활용 링크 ]**
- [NAVER CLOUD PLATFORM - Geocoding](https://www.ncloud.com/product/applicationService/maps#detail)

**[ 활용 방법 ]**

![image](https://github.com/user-attachments/assets/d3999fd3-cd61-474d-ba35-f347ebd5fda8)

<br>

### 주택 정보 업데이트 플로우
> 공공데이터 포털의 주택 실거래가 API 갱신 주기에 맞춰 스케줄러를 통해 1달 단위로 주택 데이터 갱신

![image](https://github.com/user-attachments/assets/ae2a1368-605d-465a-8891-2a24f2a5bce5)

<br>

## 🏠 Getting Started
### 1. applciation.yml 추가
- 공공데이터 포털의 decoding-key, 네이버 클라우드의 client-id, client-secret 발급
- resources/applciation.yml에 저장
  ``` yml
  spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/residence_db?useSSL=false&allowPublicKeyRetrieval=true
    username: root
    password: 1234

  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true
    properties:
      hibernate.format_sql: true
      hibernate.dialect: org.hibernate.dialect.MySQL8Dialect

  data:
    decoding-key: {decoding-key}
  
    apartment-rent:
      endpoint: http://apis.data.go.kr/1613000/RTMSDataSvcAptRent/getRTMSDataSvcAptRent
    apartment-trade:
      endpoint: http://apis.data.go.kr/1613000/RTMSDataSvcAptTrade/getRTMSDataSvcAptTrade
    detached-house-rent:
      endpoint: http://apis.data.go.kr/1613000/RTMSDataSvcSHRent/getRTMSDataSvcSHRent
    detached-house-trade:
      endpoint: http://apis.data.go.kr/1613000/RTMSDataSvcSHTrade/getRTMSDataSvcSHTrade
    multiplex-house-hold-rent:
      endpoint: http://apis.data.go.kr/1613000/RTMSDataSvcRHRent/getRTMSDataSvcRHRent
    multiplex-house-hold-trade:
      endpoint: http://apis.data.go.kr/1613000/RTMSDataSvcRHTrade/getRTMSDataSvcRHTrade
    officetel-rent:
      endpoint: http://apis.data.go.kr/1613000/RTMSDataSvcOffiRent/getRTMSDataSvcOffiRent
    officetel-trade:
      endpoint: http://apis.data.go.kr/1613000/RTMSDataSvcOffiTrade/getRTMSDataSvcOffiTrade
  
  naver-maps:
    endpoint: https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode
    client-id: {client-id}
    client-secret:  {client-secret}
  ```

### 2. docker compose 실행
- 프로젝트 루트 디렉토리 이동
- docker compose 실행
  ``` shell
  docker-compose up --build -d
  ```
  
### 3. 원하는 조건에 맞게 테스트 코드 수정
| **테스트 클래스**           | **기능**        |
|---------------------|----------------|
| DataApiTest           | 공공데이터 포털 API를 통한 주택 실거래가 조회         |
| NaverMapsApiTest      | 주소(읍면동 + 지번)을 위치(위도, 경도)로 변환     |
| IntegrationTest     | 공공데아터 포털 API + 네이버 지도 API     |
| ResidenceSearchServiceTest     | 필터링 검색       |
| ScenarioTest    | 특정 주택에 대한 정보를 직접 저장 후 필터링 검색       |

| **변수**           | **의미**        |
|---------------------|----------------|
| lawdCd           | 법정동 코드         |
| dealYm      | 계약 년/월     |
| radius      | 거리m (검색 조건)     |
| assets      | 자산 (검색 조건)     |
| loan        | 대출금액 (검색 조건)      |
| minMonthlyRent           | 최소 월세 (검색 조건)       |
| maxMonthlyRent      | 최대 월세 (검색 조건)     |

