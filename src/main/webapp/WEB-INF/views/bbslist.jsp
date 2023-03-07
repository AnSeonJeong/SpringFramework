<%@page import="mul.cam.a.util.Utility"%>
<%@page import="mul.cam.a.dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<!-- https://github.com/josecebe/twbs-pagination -->
<script src="./jquery/jquery.twbsPagination.min.js"></script>

</head>
<body>

<%
	List<BbsDto> list = (List<BbsDto>)request.getAttribute("bbslist");
	int pageBbs = (Integer)request.getAttribute("pageBbs");
	int pageNumber = (Integer)request.getAttribute("pageNumber");
	String choice = (String)request.getAttribute("choice");
	String search = (String)request.getAttribute("search");
%>

<h1>게시판</h1>

<a href="pdslist.do">자료실</a>
<hr>
<br>

<a href="calendar.do">일정관리</a>

<hr>
<br>

<div align="center">
	<select id="choice" name="choice">
		<option value="" >검색</option>
		<option value="title">제목</option>
		<option value="content">내용</option>
		<option value="writer">작성자</option>
	</select>
	
	<input type="text" id="search" value="<%=search %>" name="search">
	
	<button type="button" onclick="searchBtn()">검색</button>
	<br><br>
	<table border="1">
		<col width="70">
		<col width="600">
		<col width="100">
		<col width="150">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>조회수</th>
				<th>작성자</th>
			</tr>
		</thead>
		<tbody>
			<%
			if(list == null || list.size() == 0) {
				%>
				<!-- 글이 없을 때 -->
				<tr>
					<td colspan="4">작성된 글이 없습니다</td>
				</tr>
				<%
			} else {
				
				for(int i = 0; i < list.size(); i++) {
					BbsDto dto = list.get(i);
				%>
				<!-- 글이 있을 때 -->
				<tr>
					<th><%=i + 1 + (pageNumber * 10) %></th>
					<td>
						<%=Utility.arrow(dto.getDepth()) %>
							<%	
								if(dto.getDel() == 1) {
									%>
										<font color="#ff0000">** 이 글은 관리자에 의해 삭제된 글입니다.</font>
									<%
								} else {
									%>
									<a href="bbsdetail.do?seq=<%=dto.getSeq() %>">
										<input type="hidden" name="seq" value="<%=dto.getSeq() %>">
										<%=dto.getTitle() %>
									</a>
									<%
								}
							%>
					</td>
					<td><%=dto.getReadcount() %></td>
					<td><%=dto.getId() %></td>
				</tr>
				<%
				}
			}
			%>
		</tbody>
	</table>
	<br>
	<div class="container">
    	<nav aria-label="Page navigation">
        	<ul class="pagination" id="pagination" style="justify-content:center"></ul>
    	</nav>
	</div>
		<%-- <%
			for(int i = 0; i < pageBbs; i++) {
				// 현재 페이지
				if(pageNumber == i) {
					%>
					<span style="font-size:15pt;color:#0000ff;font-weight:bold;">
						<%=i+1 %>
					</span>
					<%
				} else {
					// 나머지 페이지
					%>
					<a href="#none" title="<%=i+1 %>페이지" onclick="goPage(<%=i %>)"
						style="font-size: 15pt;color: #000;font-weight: bold;text-decoration: none;">
						[<%=i+1 %>]
					</a>
					<%	
				}
			}
		%> --%>
	
	<br><br>
	
	<a href="bbsWrite.do">글쓰기</a>
</div>

<script type="text/javascript">

let search = "<%=search %>";
console.log("search = " + search);
if(search != ""){
	let obj = document.getElementById("choice");
	obj.value = "<%=choice %>";
	obj.setAttribute("selected", "selected");
}

function searchBtn() {
	let choice = document.getElementById("choice").value;
	let search = document.getElementById("search").value;

	location.href = "bbslist.do?choice=" + choice + "&search=" + search;
}

function goPage(pageNumber) {
	let choice = document.getElementById("choice").value;
	let search = document.getElementById("search").value;
	
	location.href = "bbslist.do?choice=" + choice + "&search=" + search + "&pageNumber=" + pageNumber;
}

$('#pagination').twbsPagination({
	startPage: <%=pageNumber+1 %>,
    totalPages: <%=pageBbs %>,
    visiblePages: 10,
    first:'<span srid-hidden="true">«</span>',
    prev:"이전",
    next:"다음",
    last:'<span srid-hidden="true">»</span>',
    initiateStartPageClick:false, 	// onPageClick 자동 실행되지 않도록
    onPageClick: function (event, page) {
        //console.info(page + ' (from options)');
        let choice = document.getElementById("choice").value;
		let search = document.getElementById("search").value;
		
        location.href = "bbslist.do?choice=" + choice + "&search=" + search + "&pageNumber=" + (page-1);
    }
});
</script>

</body>
</html>