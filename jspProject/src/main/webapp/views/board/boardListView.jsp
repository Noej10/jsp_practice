<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.kh.board.model.vo.Board ,com.kh.common.medel.vo.PageInfo, java.util.ArrayList"  %>
    
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.outer{
    background: black;
    color: white;
    width: 1000px;
    height: 500px;
    margin: auto;
    margin-top: 50px;
    

}

.outer2{
    background: black;
    color: white;
    width: 1000px;
    height: 550px;
    margin: auto;
    margin-top: 50px;
}

.list-area{
    border: 1px solid white;
    text-align: center;
}

.list-area > tbody > tr:hover{
    background: gray;
    cursor: pointer;
}



</style>
</head>
<body>

    <%@ include file="../common/menubar.jsp"%>

  	<% if(loginUser!=null) {%>
  	<div class="outer2">
  	<%}else{ %>
    <div class="outer">
    <%} %>
        <br>
        <h2 align="center">일반 게시판</h2>
        <br>

        <!-- 로그인한 회원보이게-->
        <% if(loginUser!=null){ %>
        <div align="right" style="width: 850px; height: 40px;">
            <button class="btn btn-sm btn-secondary">글 작성</button>
            <br><br>
        </div>
		<%} %>


        <table align="center" class="list-area">
            <thead>
                <tr>
                    <th width="70">글번호</th>
                    <th width="80">카테고리</th>
                    <th width="300">제목</th>
                    <th width="100">작성자</th>
                    <th width="50">조회수</th>
                    <th width="100">작성일</th>
                </tr>
            </thead>
            <tbody>
                <!-- 게시글 없을경우 -->
                <% if(list.isEmpty()){ %>
                <tr>
                    <td colspan="6">조회된 게시글이 없습니다.</td>
                </tr>
                <%} else { %>
                <!-- 게시글 있을경우 -->
                <% for(Board b : list){ %>
                <tr>
                    <td><%=b.getBoardNo() %></td>
                    <td><%=b.getCategory() %></td>
                    <td><%=b.getBoardTitle() %></td>
                    <td><%=b.getBoardWriter() %></td>
                    <td><%=b.getCount() %></td>
                    <td><%=b.getCreateDate() %></td>
                </tr>
                
         	<%}
         	} %>
            </tbody>
        </table>
        <br><br>

        <div class="paging-area" align="center">
            <button>&lt;</button>
            <button>1</button>
            <button>2</button>
            <button>3</button>
            <button>4</button>
            <button>5</button>
            <button>6</button>
            <button>7</button>
            <button>8</button>
            <button>9</button>
            <button>10</button>
            <button>&gt;</button>
        </div>

    </div>

</body>
</html>