<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- shop_listDel.jsp 주문페이지에서 배송지관리 -->

  <!-- css 파일 연결하기 -->
   <script src="resources/js/jquery-3.7.0.js"></script>
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link href="resources/css/bootstrap.min.css" rel="stylesheet">
   <link href="resources/css/utilities.min.css" rel="stylesheet">
   <script src="resources/js/bootstrap.bundle.min.js"></script>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
	
	function send() {
		
		f.submit();
		window.opener.sendDel();
	
	}
	
</script>

<div class="d-flex justify-content-center">
	<div class="container px-1 py-4">
		<h5 class="border-bottom"><b>새 배송지 추가</b></h5><br>
			<form name="f" action="shop_updateDel.do" method="post">
			<input type="hidden" name="del_num" value="${getDelivery.del_num}">
				<table class="table" border="1" width="100%" align="center" style="font-size:12pt">
					<tr valign="middle">
						<th class="table-light">&nbsp배송지명</th>
						<td><input type="text" class="form-control" name="del_title" id="del_title" value="${getDelivery.del_title}" placeholder="예) 집, 회사"></td>
					</tr>
					<tr valign="middle">
						<th class="table-light">&nbsp받으실 분 <font color="blue"><b>*</b></font></th>
						<td><input type="text" class="form-control" name="del_name" id="del_name" value="${getDelivery.del_name}"></td><td> </td>
					</tr>
					<tr valign="middle">
						<th rowspan="4" class="table-light">&nbsp받으실 곳 <font color="blue"><b>*</b></font></th>
						<td><input type="text" class="form-control" name="del_address1" id="sample6_postcode" value="${getDelivery.del_address1}"></td>
						<td><button class="btn btn-outline-dark" type="button" onclick="sample6_execDaumPostcode()">우편번호검색</button></td>
					</tr>		
					<tr valign="middle">
						<td><input type="text" class="form-control" name="del_address2" id="sample6_address" value="${getDelivery.del_address2}"></td>
					</tr>
					<tr valign="middle">
						<td><input type="text" class="form-control" name="del_address3" id="sample6_detailAddress" value="${getDelivery.del_address3}"></td>
					</tr>	
					<tr valign="middle">
						<td style="border-top: hidden"><input type="text" class="form-control" id="sample6_extraAddress"></td>
					</tr>												
					<tr valign="middle">
						<th class="table-light">&nbsp전화번호 <font color="blue"><b>*</b></font></th>
						<td><input type="text" class="form-control" name="del_hp" value="${getDelivery.del_hp}"></td><td> </td>
					</tr>
				</table>
				<div align="center">
					<button class="btn btn-outline-dark btn-sm" type="button" onclick="send()">등록</button>
					<button class="btn btn-outline-dark btn-sm" type="button" onclick="window.close()">취소</button>	
				</div>
			</form>
	</div>
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("sample6_postcode").value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>