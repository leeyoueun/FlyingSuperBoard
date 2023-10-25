# FlyingSuperBoard
<h3>🍃 팀프로젝트 / 보드게임 복합 커뮤니티</h3>

<br>  
  
![image](https://github.com/leeyoueun/FlyingSuperBoard/assets/138740005/201dd549-7799-4402-94de-71268a3ba9c1)

---
<h2>✏️ 목차 </h2>

1. 개요
2. 개발환경
3. 담당기능
4. 그 외 구현 가능한 기능
5. 개선점 / 추가로 구현하고 싶은 기능
6. 문제해결능력

---
<h2> ✓ 개요</h2>

1. 프로젝트 소개<br>
   ◦ Spring Framework를 기반으로 회원가입, 보드게임구매, 보드게임소개, 친구추가, 알림, SNS, 친구추가, 자유(익명)게시판 등 사용가능한 웹페이지
   
2. 개발기간<br>
   ◦ 설계 및 분석 : 23.05.22 - 23.06.02 (프로토타입, uml, db) 
   ◦ 기능 구현 : 2023.06.12 - 2023.07.25
3. 개발인원<br>
   ◦ 6인 
4. 담당역할<br>
   ◦ 로그인(카카오로그인)/ 아이디,비밀번호 찾기 / 이메일 인증 / 핸드폰 문자 인증 / 회원가입 / 마이페이지(계정 수정, 내가 쓴 글, 쿠폰함, 알림함, 보관함,탈퇴) / 공지사항&자주묻는질문

---
<h2> ✓ 개발환경(툴, 라이브러리, api)</h2>
- OS : Window 10
  
- Kit : JDK
  
- Server : Apache Tomcat 9.0
   
- 언어
  - Java
  - JavaScript
        
- Tool
  - Eclipse
  - Spring
  - Oracle
  - oracle sqldeveloper
      
- Library
  - MyBatis
  - JQuery
  - Json
  - Ajax
  - BootStrap/CSS
    
- Open Source
  - Naver developers
  - KaKao developers

---
<h2>✓  담당기능</h2>

1. 설계
   
<img width="789" alt="image" src="https://github.com/leeyoueun/FlyingSuperBoard/assets/138740005/d2245aa5-0895-461c-857e-0137d7a8ec4e">

- DB Table을 통해 부정확하고 중복되는 값들을 최소화 하고 작업 시 일어날 일에 대해 예측
- Use Case를 그려 기본적인 흐름을 파악하고 관계 정리
- 클래스 다이어그램을 이용하여 구성요소들을 파악하고 관계 이해
- 시퀀스 다이어그램으로 개체와 구성요소가 어떻게 서로 상호작용 하는지 이해

---

2. 로그인, 회원가입, 개인정보 동의서
   
![image](https://github.com/leeyoueun/FlyingSuperBoard/assets/138740005/e6c7539d-279e-4631-bbed-b74ccfff60e6)
- 기능<br>
   Bootstrap과 css를 활용한 회원가입창 / 디비체크를 통한 Id중복 확인이 가능하고 일반회원,사업자 회원을 mode로 구분하여 DB 저장<br>
   쿠키에 저장하여 아이디 기억하기, 세션에 저장해서 로그인 가능<br>
   카카오 api를 이용하여 카카오 로그인이 가능하며, 멤버 DB에 없는 아이디일 경우 자동으로 추가<br>
   javascript를 이용하여 전체 선택시 모두 클릭되는 개인정보동의서 생성<br>
<br>
<br>


3. 문자전송, 메일전송, 아이디찾기, 비밀번호찾기, 비밀번호 재설정
   
![image](https://github.com/leeyoueun/FlyingSuperBoard/assets/138740005/26101e07-5afc-42d4-a26b-8317a0ff9742)
- 기능<br>
  Jsp를 이용해 인증요청을 누르면 인증확인 칸이 추가 되는 아이디 찾기 생성<br>
  메일발송 api(mail sender)를 통한 인증번호 발송<br>
  문자발송 api를 통한 인증번호 발송<br>
  javascript 조건식을 통해 특수문자, 자릿수 조건이 있는 비밀번호 재설정<br>
<br>
<br>

4. 마이페이지 (프로필 이미지 수정, 비밀번호 변경, 닉네임 변경, 핸드폰번호 등록)
   
![image](https://github.com/leeyoueun/FlyingSuperBoard/assets/138740005/d1ffd277-4ea2-4516-83db-385861cd4f95)
- 기능<br>
  파일업로더를 통한 이미지 저장, ajax를 이용해 기본이미지로 변경 가능<br>
  비밀번호변경 : javascript 활용해 비밀번호 입력,재입력 두 번호가 일치시 입력버튼 활성화<br>
  닉네임변경 : 중복확인 후 DB저장<br>
  핸드폰번호 : 휴대전화 인증후 저장<br>
<br>
<br>

5. 마이페이지 ( 뱃지설정, 해쉬태그, 회원탈퇴)
   
  ![image](https://github.com/leeyoueun/FlyingSuperBoard/assets/138740005/998850cc-9e1d-41bf-b71d-75737df2b15e)
- 기능<br>
  sql 쿼리문을 통해 일정 조건을 걸고, 만족시 뱃지,태그 체크 버튼 활성화, 저장된 설정은 탭에 바로 뜨게끔, 저장시 바로 반영
  회원탈퇴 : 비밀번호 불일치시 탈퇴 불가하며 일치시 메인화면으로 이동하면서 db삭제, 세션 풀림
<br>
<br>

6. 마이페이지 (내가 쓴 게시판 별 글 목록, 댓글목록)
   
  ![image](https://github.com/leeyoueun/FlyingSuperBoard/assets/138740005/ad354ea1-f5dc-41dc-b05e-6092bc33e3a5)
- 기능
  쿼리문 사용하여 게시판별 쓴 글과 댓글 안내, 클릭시 해당 댓글, 글 모음 리스트로 이동하며 게시물 확인 가능<br>

<br>
<br>
<br>

7. 알림(리스트, 확인, 삭제)
   
![image](https://github.com/leeyoueun/FlyingSuperBoard/assets/138740005/c3f38881-04c0-407e-8b3d-f7f26ede1cee)
- 기능<br>
  설정해둔 영역에 한해 알람이 울리며 클릭시 해당 내용으로 이동(보드게임, 커뮤니티 등)<br>
  알람 확인 버튼 클릭시 읽지 않은 알람 갯수가 변경되고 확인 버튼이 비활성화 됨, 삭제시 DB에서 삭제됨<br>
<br>
<br>
<br>

8. 쿠폰리스트, 찜한 보드게임 리스트, 공지사항, 자주 묻는 질문 리스트
   
![image](https://github.com/leeyoueun/FlyingSuperBoard/assets/138740005/3b64f66c-c609-4335-aaa4-da10a99ffba1)
- 기능<br>
  List를 이용해 발급받은 쿠폰목록, 찜한 보드게임 목록을 볼 수 있고 확인 삭제 가능하다<br>
  관리자 페이지에서 작성한 공지사항, 자주묻는 질문들을 볼 수 있다.<br>
<br>


---
<h2>✓ 그 외 구현 가능한 기능</h2>

<img width="784" alt="image" src="https://github.com/leeyoueun/FlyingSuperBoard/assets/138740005/b44df57e-4d51-47b0-9378-c0b602b4418d">

- 댓글, 답글형 게시판 / 관리자,사용자용 쇼핑몰 / 파일 단일,다중 업로드 / aws를 이용한 웹페이지 배포 경험 등 <br>
  개발에 필요한 요소들을 공부하고 있음

---
<h2>✓ 개선점 / 추가로 구현하고 싶은 기능</h2>

**1. 추가하거나 구현해보고 싶은 기능**

-  문자/이메일 인증시 시간제한
  -  한달에 닉네임 수정가능 횟수 제한
  -  더 많은 알람 목록
  -  생일 설정(달력 추가)
  -  닉네임 설정시 욕설 방지
  -  문자 전송 외 핸드폰 인증 api 구현
  -  결제, 배송 api 구현

<br>
<br>

**2. 개선점**

- UML에 대한 이해도가 낮은 상태로 다이어그램을 그린 것 
    - 수료 후 다이어그램에 대한 공부를 통해 보완 완료
- 주석, 매퍼를 항목별로 구분
   - 대략 완성 후 구분하고 주석 달기 완료
   - 다음 프로젝트부터는 시작부터 제대로!
- 메소드,함수명 통일성 및 가독성
   - 팀원들과 시작시 프로젝트에 맞는 이름으로 결정하기!
- 프론트 디자인에 대한 아쉬움
   - 현재 부트스트랩, CSS에 대한 공부 진행 중
- DB 설계확실히, 코드를 간략하게 

---














