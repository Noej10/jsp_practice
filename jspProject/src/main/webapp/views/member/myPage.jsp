<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer{
        background: black;
        color: white;
        margin: auto;
        margin-top: 50px;
    }

    #mypage-form table{
        margin: auto;
    }
    #mypage-form input{
        margin: 5px;
    }

</style>

</head>
<body>
    <%@ include file="../common/menubar.jsp"%>
    
    <%
    	String userId = loginUser.getUserId();
    	String userName = loginUser.getUserName();
    	String phone = loginUser.getPhone() == null ? "" : loginUser.getPhone();
    	String email = loginUser.getEmail()== null ? "" : loginUser.getEmail();
    	String address = loginUser.getAddress()== null ? "" : loginUser.getAddress();
    	String interest = loginUser.getInterest()== null ? "" : loginUser.getInterest();
    
     %>
    <div class="outer">
        <br>
        <h2 align="center">마이페이지</h2>

        <form action="<%=contextPath %>/update.me" id="mypage-form" method="post">
            <table>
                <tr>
                    <td align="center" style="font-size: 18px;">* 아이디</td>
                    <td><input type="text" class="form-control" name="userId" maxlength="12" value="<%= userId %>" readonly></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="center" style="font-size: 18px;">* 이름</td>
                    <td><input type="text" class="form-control" name="userName" maxlength="6" value="<%= userName %>" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="center" style="font-size: 18px;">전화번호</td>
                    <td><input type="text" class="form-control" name="phone" placeholder="- 포함해서 입력" value="<%= phone %>"></td>                    <td></td>
                </tr>
                <tr>
                    <td align="center" style="font-size: 18px;">이메일</td>
                    <td><input type="email" class="form-control" name="email" value="<%= email %>" ></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="center" style="font-size: 18px;">주소</td>
                    <td><input type="text" class="form-control" name="address" value="<%= address %>" ></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="center" style="font-size: 18px;">관심분야</td>
                    <td colspan="2">
                        <!-- input과 label 매핑 시 id와 for로 연결-->
                        <input type="checkbox" name="interest" id="sports" value="운동">
                        <label for="sports">운동</label>

                        <input type="checkbox" name="interest" id="climbing" value="등산">
                        <label for="climbing">등산</label>

                        <input type="checkbox" name="interest" id="fishing" value="낚시">
                        <label for="fishing">낚시</label>
                        <br>
                        <input type="checkbox" name="interest" id="cooking" value="요리">
                        <label for="cooking">요리</label>

                        <input type="checkbox" name="interest" id="game" value="게임">
                        <label for="game">게임</label>

                        <input type="checkbox" name="interest" id="movie" value="영화">
                        <label for="movie">영화</label>
                    </td>
                </tr>
            </table>

            <br><br>
            
            <script>
            	const interest = "<%=interest%>"; //운동, 낚시
            	const inputArr = document.querySelectorAll("input[name=interest]");
            	console.log(interest);
                console.log(inputArr);
                
                for( let input of inputArr){
                	if(interest.includes(input.value)){
                		input.checked = true;
                	}
                }
            </script>

            <div align = "center">
                <button type="submit" class="btn btn-sm btn-secondary">정보변경</button>
                <button type="button" class="btn btn-sm btn-warning" data-bs-toggle="modal" data-bs-target="#updatePwdModal">비밀번호 변경</button>
                <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">회원 탈퇴</button>
            </div>

            <br><br>

        </form>

    </div>

    <!-- 비밀번호 변경용 모달 -->
    <div class="modal" id="updatePwdModal">
        <div class="modal-dialog">
            <div class="modal-content">
  
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">비밀번호 변경</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
  
                <!-- Modal body -->
                <div class="modal-body" align = "center">
                    <form action="<%=contextPath%>/updatePwd.me" method="post">
                        <input type="hidden" name="userId" value="<%=userId%>">
                        <table>
                            <tr>
                                <td>현재 비밀번호</td>
                                <td><input class="form-control" type="password" name="userPwd" required></td>
                            </tr>
                            <tr>
                                <td>변경할 비밀번호</td>
                                <td><input class="form-control" type="password" name="updatePwd" required></td>
                            </tr>
                            <tr>
                                <td>변경할 비밀번호 확인</td>
                                <td><input class="form-control" type="password" name="checkPwd" required></td>
                            </tr>
                        </table>
                        <br>
                        <button onclick="return checkPwd2();" type="submit" class="btn btn-sm btn-secondary">비밀번호 변경</button>
                    </form>

                    <script>
                        function checkPwd2(){
                            let updatePwd = document.querySelector(".modal-body td input[name=updatePwd]");
                            let checkPwd = document.querySelector(".modal-body td input[name=checkPwd]");

                            if(updatePwd.value!==checkPwd.value){
                                alert("비밀번호가 일치하지 않습니다.");
                                return false;
                            }

                            


                        }



                    </script>
                </div>
            </div>
        </div>
    </div>
    
    
    
    <!-- 회원 탈퇴용 모달 -->
    <div class="modal" id="deleteModal">
        <div class="modal-dialog">
            <div class="modal-content">
  
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">회원 탈퇴</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
  
                <!-- Modal body -->
                <div class="modal-body" align = "center">
                    <form action="<%=contextPath%>/delete.me" method="post">
                        <input type="hidden" name="userId" value="<%=userId%>">
                        <b>탈퇴 후 복구가 불가능합니다. <br>정말로 탈퇴하시겠습니까?</b>
                        <br><br>
                        
                        비밀번호 : <input type="password" name="userPwd" required>
                        <br><br>
                        <button type="submit" class="btn btn-sm btn-danger">회원 탈퇴</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

</body>
</html>