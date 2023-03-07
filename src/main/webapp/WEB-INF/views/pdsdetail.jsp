<%@page import="mul.cam.a.dto.PdsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	PdsDto dto = (PdsDto)request.getAttribute("pdsdetail");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>상세자료</h1>
	<hr>
	<div align="center">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td>
					<%=dto.getId() %>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><%=dto.getTitle() %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=dto.getReadcount() %></td>
			</tr>
			<tr>
				<th>다운로드수</th>
				<td><%=dto.getDowncount() %></td>
			</tr>
			<tr>
				<th>파일명</th>
				<td><%=dto.getFilename() %></td>
			</tr>
			<tr>
				<th>등록일</th>
				<td><%=dto.getRegdate() %></td>
			</tr>
			<tr>
				<th>첨부파일 다운</th>
				<td>
					<input type="button" value="다운로드" onclick="filedown(<%=dto.getSeq() %>, '<%=dto.getNewfilename() %>', '<%=dto.getFilename() %>')">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="10" cols="50" name="content" readonly="readonly"><%=dto.getContent() %></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" onclick="GoPdslist()">자료목록</button>
					<button type="button" onclick="pdsUpdate(<%=dto.getSeq() %>)">수정</button>
				</td>
			</tr>
		</table>
	</div>
	
	<form name="file_down" action="filedownLoad.do" method="post">
		<input type="hidden" name="newfilename">
		<input type="hidden" name="filename">
		<input type="hidden" name="seq">
	</form>
	
	<script type="text/javascript">
	function GoPdslist() {
		location.href = "pdslist.do";
	}
	
	function filedown(seq, newfilename, filename) {
		document.file_down.newfilename.value = newfilename;
		document.file_down.filename.value = filename;
		document.file_down.seq.value = seq;
		document.file_down.submit();
	}
	
	function pdsUpdate(seq) {
		location.href = "pdsUpdate.do?seq=" + seq;
	}
	</script>
</body>
</html>