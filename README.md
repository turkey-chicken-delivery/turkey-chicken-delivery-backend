<div align=center>
수정중  

![KakaoTalk_20241107_092336426](https://github.com/user-attachments/assets/4b7c9fca-bff6-42b5-a49c-e726ce7ce72f)

## 👊 아웃소싱 프로젝트 👊
### Spring을 활용하여 배달 어플리케이션의 다양한 기능들을 개발❗ 협업 능력을 곁들인➕ <br>
#### 24.11.01 ~ 24.11.08
<br>

## 👨‍👦‍👦 M E M B E R 👨‍👦‍👦
| 이름 | 역할 |담당 기능|
|-----|-----|-----|
|[이시우](https://github.com/matino0216)|팀 원|Menu|
|[이지훈](https://github.com/LEEJI-HOON1)|팀 원|Store|
|[신진오](https://github.com/lastdove)|팀 원|Comment & Review|
|[조성준](https://github.com/seongjun1130)|팀 원|Signup & Like|
|[홍주영](https://github.com/92jy38)|팀 장|Order|


## Tools
### 🖥 language & Server 🖥

<img src="https://img.shields.io/badge/intellij idea-207BEA?style=for-the-badge&logo=intellij%20idea&logoColor=white"><img src="https://img.shields.io/badge/JDK 17-666666?style=for-the-badge&logo-bitdefender&logoColor=FFFFFF"/></a>
 <br>
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"><img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
 <br>
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/ 8.0-666666?style=for-the-badge&logo-bitdefender&logoColor=FFFFFF"/></a>
 <br>
### 👏 Cowork Tools 👏
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <br> 
<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=FFFFFF"/></a><img src="https://img.shields.io/badge/slack-FE5196?style=for-the-badge&logo=slack&logoColor=FFFFFF"/></a>

<br>
<hr/>

## 와이어 프레임
![image](https://github.com/user-attachments/assets/658b1700-c28b-4c77-a253-79d7a654639a)
![image](https://github.com/user-attachments/assets/7a42cd68-f54f-4046-8a1d-615a0ae15436)
![image](https://github.com/user-attachments/assets/08b10622-4d0b-4d6e-b467-a666d8dd7835)



## API 명세
<table> <tr> <th>API</th> <th>Method</th> <th>EndPoint</th> <th>Request</th> <th>Request Type</th> <th>Response</th> <th>Response Type</th> <th>Status</th> <th>Role</th> </tr> <!-- User Registration --> <tr> <td>회원가입</td> <td>POST</td> <td><code>/api/users</code></td> <td> <pre lang="json">{ "email": "avc@gmail.com", "password": "q1!w2@e3#r4$", "name": "김사장", "type": "OWNER" }</pre> </td> <td><code>application/json</code></td> <td> <pre lang="json">{ "id": 1, "email": "avc@gmail.com", "name": "김사장", "type": "OWNER" }</pre> </td> <td><code>application/json</code></td> <td>201 CREATED, 409 CONFLICT</td> <td></td> </tr> <!-- User Login --> <tr> <td>로그인</td> <td>POST</td> <td><code>/api/users/login</code></td> <td> <pre lang="json">{ "email": "avc@gmail.com", "password": "q1!w2@e3#r4$" }</pre> </td> <td><code>application/json</code></td> <td>N/A (Response Header SetCookie, Bearer JWT_TOKEN)</td> <td>N/A</td> <td>200 OK, 401 UNAUTHORIZED, 404 NOT_FOUND</td> <td></td> </tr> <!-- Update User Name --> <tr> <td>회원 정보 수정(이름)</td> <td>PUT</td> <td><code>/api/users/{Id}/name</code></td> <td> <pre lang="json">{ "name": "김유저" }</pre> </td> <td><code>application/json</code></td> <td> <pre lang="json">{ "id": 1, "name": "김유저" }</pre> </td> <td><code>application/json</code></td> <td>200 OK, 401 UNAUTHORIZED, 403 FORBIDDEN, 404 NOT_FOUND</td> <td>User</td> </tr> <!-- Update User Password --> <tr> <td>회원 정보 수정(비밀번호)</td> <td>PUT</td> <td><code>/api/users/{Id}/password</code></td> <td> <pre lang="json">{ "currentPassword": "q1!w2@e3#r4$", "changePassword": "$4r#3e@2w!1q" }</pre> </td> <td><code>application/json</code></td> <td>N/A</td> <td>N/A</td> <td>204 NO_CONTENT, 409 CONFLICT</td> <td>User</td> </tr> <!-- Delete User --> <tr> <td>회원 정보 삭제(탈퇴)</td> <td>DELETE</td> <td><code>/api/users/{Id}</code></td> <td> <pre lang="json">{ "password": "q1!w2@e3#r4$" }</pre> </td> <td><code>application/json</code></td> <td>N/A</td> <td>N/A</td> <td>204 NO_CONTENT, 404 NOT_FOUND</td> <td>User</td> </tr> <!-- Register Store --> <tr> <td>가게 등록</td> <td>POST</td> <td><code>/api/stores</code></td> <td> <pre lang="json">{ "name": "가게이름", "openTime": "오픈시간", "closeTime": "마감시간", "minimumPrice": "최소주문금액" }</pre> </td> <td><code>application/json</code></td> <td> <pre lang="json">{ "id": 1, "name": "가게이름", "openTime": "오픈시간", "closeTime": "마감시간", "minimumPrice": "최소주문금액" }</pre> </td> <td><code>application/json</code></td> <td>201 CREATED</td> <td>Owner</td> </tr> <!-- Get Store List --> <tr> <td>가게 리스트 조회</td> <td>GET</td> <td><code>/api/stores?page=1&size=10</code></td> <td>N/A</td> <td>N/A</td> <td> <pre lang="json">{ "content": [ { "id": 3, "name": "김가게", "openTime": "00:00:01", "closeTime": "23:59:59", "minimumPrice": 20000 } ], "pageable": { "pageNumber": 0, "pageSize": 10 } }</pre> </td> <td><code>application/json</code></td> <td>200 OK</td> <td>User</td> </tr> <!-- Get Store Details --> <tr> <td>가게 상세 조회</td> <td>GET</td> <td><code>/api/stores/{Id}</code></td> <td>N/A</td> <td>N/A</td> <td> <pre lang="json">{ "id": "1", "name": "가게이름", "openTime": "오픈시간", "closeTime": "마감시간", "minimumPrice": "최소주문금액", "ownerMessage": "사장님 한마디", "menuList": [ { "menuId": "01", "menuName": "메뉴 이름1", "price": "가격1" } ], "reviewList": [ { "reviewId": "001", "rating": "5", "comment": "리뷰 코멘트1" } ] }</pre> </td> <td><code>application/json</code></td> <td>200 OK</td> <td>User</td> </tr> <!-- Update Store --> <tr> <td>가게 수정</td> <td>PUT</td> <td><code>/api/stores/{Id}</code></td> <td> <pre lang="json">{ "openTime": "수정오픈시간", "closeTime": "마감시간", "minimumPrice": "최소주문금액" }</pre> </td> <td><code>application/json</code></td> <td> <pre lang="json">{ "id": "1", "openTime": "수정오픈시간", "closeTime": "마감시간", "minimumPrice": "최소주문금액" }</pre> </td> <td><code>application/json</code></td> <td>200 OK</td> <td>Owner</td> </tr> <!-- Delete Store --> <tr> <td>가게 삭제</td> <td>DELETE</td> <td><code>/api/stores/{Id}</code></td> <td>N/A</td> <td>N/A</td> <td>N/A</td> <td>N/A</td> <td>200 OK</td> <td>Owner</td> </tr> <!-- Create Menu --> <tr> <td>메뉴 생성</td> <td>POST</td> <td><code>/api/stores/{storeId}/menus</code></td> <td> <pre lang="json">{ "name": "메뉴 이름", "price": "가격" }</pre> </td> <td><code>application/json</code></td> <td> <pre lang="json">{ "id": "1", "name": "메뉴 이름", "price": "가격", "createdAt": "메뉴 생성 시각" }</pre> </td> <td><code>application/json</code></td> <td>201 CREATED, 401 UNAUTHORIZED</td> <td>Owner</td> </tr> <!-- Get Menu List --> <tr> <td>메뉴 리스트 조회</td> <td>GET</td> <td><code>/api/stores/{storeId}/menus?page=1&size=10</code></td> <td>N/A</td> <td>N/A</td> <td> <pre lang="json">{ "content": [ { "id": 3, "name": "김밥", "price": 20000, "createdAt": "2024-11-07T02:18:21.777555" } ], "pageable": { "pageNumber": 0, "pageSize": 10 } }</pre> </td> <td><code>application/json</code></td> <td>200 OK, 401 UNAUTHORIZED</td> <td>User</td> </tr> <!-- Update Menu --> <tr> <td>메뉴 수정</td> <td>PUT</td> <td><code>/api/stores/{storeId}/menus/{Id}</code></td> <td> <pre lang="json">{ "name": "메뉴 이름", "price": "가격" }</pre> </td> <td><code>application/json</code></td> <td> <pre lang="json">{ "id": "1", "storeId": "1", "name": "메뉴 이름", "price": "가격", "modifiedAt": "메뉴 수정 시각" }</pre> </td> <td><code>application/json</code></td> <td>200 OK, 401 UNAUTHORIZED</td> <td>Owner</td> </tr> <!-- Delete Menu --> <tr> <td>메뉴 삭제(soft)</td> <td>DELETE</td> <td><code>/api/stores/{storeId}/menus/{Id}</code></td> <td>N/A</td> <td>N/A</td> <td>N/A</td> <td>N/A</td> <td>204 NO_CONTENT</td> <td>Owner</td> </tr> <!-- Create Order --> <tr> <td>주문 생성</td> <td>POST</td> <td><code>/api/stores/{storeId}/orders</code></td> <td> <pre lang="json">{ "menuId": 123, "quantity": 2 }</pre> </td> <td><code>application/json</code></td> <td> <pre lang="json">{ "orderId": 1, "quantity": 2, "totalPrice": 30000, "orderStatus": "PENDING", "createdAt": "주문 생성 시각" }</pre> </td> <td><code>application/json</code></td> <td>201 CREATED, 401 UNAUTHORIZED, 403 FORBIDDEN</td> <td>User</td> </tr> <!-- Get Order List --> <tr> <td>주문 리스트 조회</td> <td>GET</td> <td><code>/api/users/{id}/orders?page=1&size=10</code></td> <td>N/A</td> <td>N/A</td> <td> <pre lang="json">{ "content": [ { "orderId": 7, "storeInfo": {}, "menuInfo": {}, "totalPrice": 400000, "quantity": 20, "status": "PENDING", "modifiedAt": "2024-11-07T00:06:01.389808" } ], "pageable": { "pageNumber": 0, "pageSize": 10 } }</pre> </td> <td><code>application/json</code></td> <td>200 OK, 403 FORBIDDEN</td> <td>User</td> </tr> <!-- Get Order Details --> <tr> <td>주문 상세 조회</td> <td>GET</td> <td><code>/api/stores/{storeId}/orders/{Id}</code></td> <td>N/A</td> <td>N/A</td> <td> <pre lang="json">{ "id": 1, "storeId": 789, "userId": 456, "menuId": 123, "quantity": 2, "totalPrice": 30000, "orderStatus": "COMPLETED", "createdAt": "주문 생성 시각" }</pre> </td> <td><code>application/json</code></td> <td>200 OK, 401 UNAUTHORIZED</td> <td>User</td> </tr> <!-- Update Order Status (User) --> <tr> <td>주문 상태 변경 (유저)</td> <td>PUT</td> <td><code>/api/stores/{storeId}/orders/{id}</code></td> <td> <pre lang="json">{ "orderStatus": "CANCELED" }</pre> </td> <td><code>application/json</code></td> <td> <pre lang="json">{ "id": 1, "orderStatus": "CANCELED", "modifiedAt": "수정 날짜" }</pre> </td> <td><code>application/json</code></td> <td>200 OK, 401 UNAUTHORIZED</td> <td>User</td> </tr> <!-- Update Order Status (Owner) --> <tr> <td>주문 상태 변경 (사장님)</td> <td>PUT</td> <td><code>/api/stores/{storeId}/orders/{id}/owner</code></td> <td> <pre lang="json">{ "orderStatus": "COMPLETED" }</pre> </td> <td><code>application/json</code></td> <td> <pre lang="json">{ "id": 1, "orderStatus": "COMPLETED", "modifiedAt": "수정 날짜" }</pre> </td> <td><code>application/json</code></td> <td>200 OK, 400 BAD_REQUEST, 401 UNAUTHORIZED</td> <td>Owner</td> </tr> <!-- Delete Order --> <tr> <td>주문 기록 단일 삭제</td> <td>DELETE</td> <td><code>/api/stores/{storeId}/orders/{id}</code></td> <td>N/A</td> <td>N/A</td> <td>N/A</td> <td>N/A</td> <td>204 NO_CONTENT</td> <td>User/Owner</td> </tr> <!-- Create Review --> <tr> <td>리뷰 생성</td> <td>POST</td> <td><code>/api/stores/{storeId}/orders/{orderId}/reviews</code></td> <td> <pre lang="json">{ "comment": "내용", "star": 5 }</pre> </td> <td><code>application/json</code></td> <td> <pre lang="json">{ "id": 1, "comment": "내용", "star": 5 }</pre> </td> <td><code>application/json</code></td> <td>201 CREATED</td> <td>User</td> </tr> <!-- Get Reviews --> <tr> <td>리뷰 조회</td> <td>GET</td> <td><code>/api/stores/{storeId}/reviews</code></td> <td>N/A</td> <td>N/A</td> <td> <pre lang="json">{ "contents": [ { "comment": "내용", "star": "별점" } ], "size": "사이즈 크기", "page": "페이지 값", "totalPages": "전체페이지 수" }</pre> </td> <td><code>application/json</code></td> <td>200 OK</td> <td>User</td> </tr> <!-- Update Review --> <tr> <td>리뷰 수정</td> <td>PUT</td> <td><code>/api/stores/{storeId}/reviews/{Id}</code></td> <td> <pre lang="json">{ "comment": "내용", "star": "별점" }</pre> </td> <td><code>application/json</code></td> <td> <pre lang="json">{ "id": "아이디 값", "comment": "내용", "star": "별점" }</pre> </td> <td><code>application/json</code></td> <td>200 OK</td> <td>User</td> </tr> <!-- Delete Review --> <tr> <td>리뷰 삭제</td> <td>DELETE</td> <td><code>/api/stores/{storeId}/reviews/{Id}</code></td> <td>N/A</td> <td>N/A</td> <td>N/A</td> <td>N/A</td> <td>204 NO_CONTENT</td> <td>User</td> </tr> <!-- Create Comment --> <tr> <td>댓글 생성</td> <td>POST</td> <td><code>/api/stores/{storeId}/reviews/{reviewId}/comments</code></td> <td> <pre lang="json">{ "content": "내용" }</pre> </td> <td><code>application/json</code></td> <td> <pre lang="json">{ "id": "아이디 값", "content": "내용" }</pre> </td> <td><code>application/json</code></td> <td>201 CREATED</td> <td>User/Owner</td> </tr> <!-- Get Comments with Reviews --> <tr> <td>리뷰 조회 (댓글 값 추가)</td> <td>GET</td> <td><code>/api/stores/{storeId}/reviews/{review}/comments</code></td> <td>N/A</td> <td>N/A</td> <td> <pre lang="json">{ "contents": [ { "content": "내용", "star": "별점", "reply": { "content": "댓글내용" } } ], "size": "사이즈 크기", "page": "페이지 값", "totalPages": "전체 페이지 값" }</pre> </td> <td><code>application/json</code></td> <td>200 OK</td> <td>User</td> </tr> <!-- Update Comment --> <tr> <td>댓글 수정</td> <td>PUT</td> <td><code>/api/stores/{storeId}/comments/{id}</code></td> <td>N/A</td> <td>N/A</td> <td>200 OK</td> <td>N/A</td> <td>200 OK</td> <td>User/Owner</td> </tr> <!-- Delete Comment --> <tr> <td>댓글 삭제</td> <td>DELETE</td> <td><code>/api/stores/{storeId}/comments/{id}</code></td> <td>N/A</td> <td>N/A</td> <td>204 NO_CONTENT</td> <td>N/A</td> <td>204 NO_CONTENT</td> <td>User/Owner</td> </tr> <!-- Add to Favorites --> <tr> <td>즐겨찾기 등록</td> <td>POST</td> <td><code>/api/stores/{storeId}/likes</code></td> <td>N/A</td> <td>N/A</td> <td>N/A</td> <td>N/A</td> <td>201 CREATED, 404 NOT_FOUND, 409 CONFLICT</td> <td>User</td> </tr> <!-- Remove from Favorites --> <tr> <td>즐겨찾기 취소</td> <td>DELETE</td> <td><code>/api/stores/{storeId}/likes/{id}</code></td> <td>N/A</td> <td>N/A</td> <td>N/A</td> <td>N/A</td> <td>204 NO_CONTENT, 401 UNAUTHORIZED, 404 NOT_FOUND</td> <td>User</td> </tr> </table>

## 프로젝트 구조
```plaintext
📦 
├─ .gitattributes
├─ .gitignore
├─ README.md
├─ build.gradle
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ settings.gradle
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ sparta
   │  │        └─ i_am_delivery
   │  │           ├─ IAmDeliveryApplication.java
   │  │           ├─ comment
   │  │           │  ├─ controller
   │  │           │  │  └─ CommentController.java
   │  │           │  ├─ dto
   │  │           │  │  ├─ request
   │  │           │  │  │  └─ CommentRequestDto.java
   │  │           │  │  └─ response
   │  │           │  │     ├─ CommentCreationResponseDto.java
   │  │           │  │     └─ CommentResponseDto.java
   │  │           │  └─ service
   │  │           │     └─ CommentService.java
   │  │           ├─ common
   │  │           │  ├─ annotation
   │  │           │  │  ├─ AuthUser.java
   │  │           │  │  ├─ ValidEnum.java
   │  │           │  │  └─ ValidPasswordPatten.java
   │  │           │  ├─ aop
   │  │           │  │  └─ OrdersLogAop.java
   │  │           │  ├─ config
   │  │           │  │  ├─ ObjectMapperConfig.java
   │  │           │  │  ├─ WebConfig.java
   │  │           │  │  ├─ jwt
   │  │           │  │  │  └─ JwtHelper.java
   │  │           │  │  └─ security
   │  │           │  │     ├─ PasswordEncoderConfig.java
   │  │           │  │     └─ WebSecurityConfig.java
   │  │           │  ├─ entity
   │  │           │  │  └─ TimeStamped.java
   │  │           │  ├─ exception
   │  │           │  │  ├─ CustomException.java
   │  │           │  │  ├─ ErrorCode.java
   │  │           │  │  ├─ ErrorResponse.java
   │  │           │  │  └─ GlobalExceptionHandler.java
   │  │           │  ├─ filter
   │  │           │  │  ├─ AuthenticationExceptionHandlerFilter.java
   │  │           │  │  └─ JwtAuthenticationFilter.java
   │  │           │  └─ validator
   │  │           │     ├─ PasswordValidator.java
   │  │           │     └─ ValueOfEnumValidator.java
   │  │           ├─ domain
   │  │           │  ├─ comment
   │  │           │  │  ├─ entity
   │  │           │  │  │  └─ Comment.java
   │  │           │  │  └─ repository
   │  │           │  │     └─ CommentRepository.java
   │  │           │  ├─ like
   │  │           │  │  ├─ entity
   │  │           │  │  │  └─ Like.java
   │  │           │  │  └─ repository
   │  │           │  │     └─ LikeRepository.java
   │  │           │  ├─ menu
   │  │           │  │  ├─ entity
   │  │           │  │  │  └─ Menu.java
   │  │           │  │  └─ repository
   │  │           │  │     └─ MenuRepository.java
   │  │           │  ├─ order
   │  │           │  │  ├─ entity
   │  │           │  │  │  └─ Order.java
   │  │           │  │  └─ repository
   │  │           │  │     └─ OrderRepository.java
   │  │           │  ├─ review
   │  │           │  │  ├─ entity
   │  │           │  │  │  └─ Review.java
   │  │           │  │  └─ repository
   │  │           │  │     └─ ReviewRepository.java
   │  │           │  ├─ store
   │  │           │  │  ├─ entity
   │  │           │  │  │  └─ Store.java
   │  │           │  │  └─ repository
   │  │           │  │     └─ StoreRepository.java
   │  │           │  └─ user
   │  │           │     ├─ entity
   │  │           │     │  └─ User.java
   │  │           │     └─ repository
   │  │           │        └─ UserRepository.java
   │  │           ├─ like
   │  │           │  ├─ controller
   │  │           │  │  └─ LikeController.java
   │  │           │  ├─ dto
   │  │           │  │  └─ response
   │  │           │  │     └─ LikeAddResponseDto.java
   │  │           │  └─ service
   │  │           │     └─ LikeService.java
   │  │           ├─ menu
   │  │           │  ├─ controller
   │  │           │  │  └─ MenuController.java
   │  │           │  ├─ dto
   │  │           │  │  ├─ info
   │  │           │  │  │  └─ MenuInfo.java
   │  │           │  │  ├─ request
   │  │           │  │  │  └─ MenuRequestDto.java
   │  │           │  │  └─ response
   │  │           │  │     ├─ MenuPageReadResponseDto.java
   │  │           │  │     └─ MenuResponseDto.java
   │  │           │  └─ service
   │  │           │     └─ MenuService.java
   │  │           ├─ order
   │  │           │  ├─ controller
   │  │           │  │  └─ OrderController.java
   │  │           │  ├─ dto
   │  │           │  │  ├─ request
   │  │           │  │  │  ├─ OrderRequestDto.java
   │  │           │  │  │  └─ OrderStatusRequestDto.java
   │  │           │  │  └─ response
   │  │           │  │     ├─ CreateResponseDto.java
   │  │           │  │     ├─ DeliveryStatusResponseDto.java
   │  │           │  │     ├─ OrderDetailResponseDto.java
   │  │           │  │     └─ UpdatedResponseDto.java
   │  │           │  ├─ enums
   │  │           │  │  └─ OrderStatus.java
   │  │           │  └─ service
   │  │           │     └─ OrderService.java
   │  │           ├─ review
   │  │           │  ├─ controller
   │  │           │  │  └─ ReviewController.java
   │  │           │  ├─ dto
   │  │           │  │  ├─ request
   │  │           │  │  │  └─ ReviewRequestDto.java
   │  │           │  │  └─ response
   │  │           │  │     ├─ CommentUpdateResponseDto.java
   │  │           │  │     ├─ ReviewCreationResponseDto.java
   │  │           │  │     ├─ ReviewListResponseDto.java
   │  │           │  │     ├─ ReviewResponseDto.java
   │  │           │  │     └─ ReviewUpdateResponseDto.java
   │  │           │  └─ service
   │  │           │     └─ ReviewService.java
   │  │           ├─ store
   │  │           │  ├─ controller
   │  │           │  │  └─ StoreController.java
   │  │           │  ├─ dto
   │  │           │  │  ├─ info
   │  │           │  │  │  └─ StoreInfo.java
   │  │           │  │  ├─ request
   │  │           │  │  │  ├─ StoreCreateRequestDto.java
   │  │           │  │  │  ├─ StoreDetailReqestDto.java
   │  │           │  │  │  └─ StoreUpdateRequestDto.java
   │  │           │  │  └─ response
   │  │           │  │     ├─ StoreCreateResponseDto.java
   │  │           │  │     ├─ StoreDetailResponseDto.java
   │  │           │  │     ├─ StorePageReadResponseDto.java
   │  │           │  │     └─ StoreUpdateResponseDto.java
   │  │           │  └─ service
   │  │           │     └─ StoreService.java
   │  │           └─ user
   │  │              ├─ controller
   │  │              │  └─ UserController.java
   │  │              ├─ dto
   │  │              │  ├─ request
   │  │              │  │  ├─ UserDeleteRequestDto.java
   │  │              │  │  ├─ UserLogInRequestDto.java
   │  │              │  │  ├─ UserSignUpRequestDto.java
   │  │              │  │  ├─ UserUpdateNameRequestDto.java
   │  │              │  │  └─ UserUpdatePasswordRequestDto.java
   │  │              │  └─ response
   │  │              │     ├─ OrderPageReadResponseDto.java
   │  │              │     ├─ UserSignUpResponseDto.java
   │  │              │     └─ UserUpdateNameResponseDto.java
   │  │              ├─ enums
   │  │              │  └─ UserType.java
   │  │              ├─ resolver
   │  │              │  └─ AuthUserResolver.java
   │  │              └─ service
   │  │                 └─ UserService.java
   │  └─ resources
   │     └─ application.properties
   └─ test
      └─ java
         └─ com
            └─ sparta
               └─ i_am_delivery
                  ├─ IAmDeliveryApplicationTests.java
                  ├─ user
                  │  └─ service
                  │     └─ UserServiceTest.java
                  └─ validator
                     └─ PassWordValidatorTest.java
```

## 개체 관계도 (ERD)

![image](https://github.com/user-attachments/assets/8eb13ee3-0f43-478d-895a-be2b008d6b9f)


## Application 기능 구현






## Application 핵심 기능 시연 영상



