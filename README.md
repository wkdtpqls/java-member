
# Java 프로젝트
- 데이터베이스 연동과 Swing을 기반으로 제작한 자바 프로젝트입니다.
- 이 프로젝트은 오픈소스 프로그램을 참고하여 제작되었습니다. 이전 프로그램을 분석하고 그 기능과 구조를 이해한 뒤, 필요한 수정과 추가 기능을 직접 구현하였습니다. 
- 따라서, 이 프로그램은 기존 프로그램을 기반으로 하되, 개선된 기능과 사용자 친화적인 인터페이스를 제공하고 있습니다.
-
## 출처 
https://blog.naver.com/javaking75/140190267626


<br>

# 프로젝트 명
회원 관리 프로그램

<br>

# 프로젝트 개요
1. 효율적인 회원 관리: 이 프로그램은 기업이나 단체에서 회원들의 정보를 체계적으로 관리하기 위해 개발되었습니다. 가입, 수정, 삭제 기능을 제공하여 회원 정보를 쉽게 업데이트하고 관리할 수 있습니다.
2. 데이터 분석 및 통계: 회원의 나이와 성별에 대한 통계를 차트 형태로 제공함으로써, 회원들의 특성과 성향을 파악할 수 있습니다. 

<br>

# 프로젝트 수정한 부분
1. 오라클 -> MySql로 수정
2. 성별 필드 추가
3. 가입일 필드 추가
4. 메뉴바 추가
5. 차트 추가

<br>

# :notebook_with_decorative_cover: 프로그램 구성
- 테이블
<table>
  <tr rowspan="2" >
    <td>member</td>
  </tr>
  <tr>
    <td>id</td>
     <td>varchar(20)</td>
  </tr>
    <tr>
    <td>name</td>
     <td>varchar(20)</td>
  </tr>
   <tr>
    <td>age</td>
     <td>int</td>
  </tr>
    <tr>
    <td>gender</td>
     <td>varchar(2)</td>
  </tr>
  <tr>
    <td>addr</td>
     <td>varchar(20)</td>
  </tr>
   <tr>
    <td>reg_date</td>
     <td>date</td>
  </tr>
</table>  

<br>

## 회원 관리 프로그램
### 메인화면
![메인이미지](https://github.com/wkdtpqls/java-member/assets/112832631/26791fe3-7716-4932-a63e-b005a944e408)
<pre>
- 프로그램을 처음 실행했을 때 메인화면입니다.
- 먼저, 등록된 회원들을 최신순으로 조회할 수 있습니다.
- 그리고 메뉴바에서 회원 등록, 수정, 삭제가 가능합니다
- 또한 프로그램 종료도 가능합니다.
</pre>

<br>

### 회원 등록, 수정, 삭제 화면
![수정삭제](https://github.com/wkdtpqls/java-member/assets/112832631/ff03e998-c9ae-489b-98bc-aba1deb7f754)
<pre>
- 회원을 등록, 수정, 삭제 시 다이얼로그로 화면창이 뜹니다.
- 원하는 행을 선택하고 등록 시에는 아이디 중복을 체크합니다.
- 수정 시에는 아이디는 수정되면 안되므로 Enabled 처리되어 있습니다.
</pre>

<br>

### 통계 메뉴 화면
![스크린샷 2023-06-12 010029](https://github.com/wkdtpqls/java-member/assets/112832631/87dbe259-f2d5-4135-927f-73e6a03c04e0)
<pre>
- 두번째 메뉴 탭입니다.
- 회원의 차트를 확인할 수 있습니다.
</pre>

<br>

### 회원 별 차트 화면
![스크린샷 2023-06-12 010040](https://github.com/wkdtpqls/java-member/assets/112832631/613b9db3-6b1d-4685-b9be-eca80108c801)

<pre>
- 첫번째는 회원의 나이별 차트입니다
- 두번째는 회원의 성별 별 차트입니다
</pre>

<br>

<!--footer-->
<div align=left>
<img src="https://capsule-render.vercel.app/api?section=footer&type=waving&color=gradient&customColorList=0,12,21,14,3&height=200&text=Thank%20You&fontSize=50&animation=blink&fontAlignY=70" />	
</div>
