<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html >
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="table.css">
<script>

</script>
</head>
<body>
	<%-- <%=application.getRealPath("/") %> --%>
	<!--  /home/sist/bigdataDev2/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMusicProject/  -->
	<center>
		<table id="table_content" width=600>
			<tr>
				<td colspan="2">
					<embed src="${vo.link }" type="" width="600" height="400"/>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">노래명</td>
				<td width="85%">${vo.title }</td>
			</tr>
			<tr>
				<td width="15%" align="right">가수</td>
				<td width="85%">${vo.singer}</td>
			</tr>
			<tr>
				<td width="15%" align="right">랭크</td>
				<td width="85%">${vo.rank } </td>
			</tr>
		</table>
		<table id="table_content" width=600>
			<tr>
				<td align="center">
					<img src="barchart.png" alt="" />
				</td>
				<td align="center">
					<img src="wordcloud.png" alt="" />
				</td>
			</tr>
		</table>
	</center>
</body>
</html>