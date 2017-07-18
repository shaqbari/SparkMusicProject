<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
  <div class="content">
    <div class="facets">
      <div class="facet">
        <div class="facet-title">감성별 장르</div>
        <div id="genres">
         <ul style="list-style-type: none">
             <li>
             	<form action="music_feel_find.do" method="post">
             	<span class="genre_span" >감정/상황</span>
             	<select name="feel_data" id="">
             		<c:forEach var="fd" items="${feel_data }">
             			<option>${fd }</option>
             		</c:forEach>
             	</select>
             	<input type="submit" value="전송" />
             	</form>
             </li>
             </li>
             <li>
               	<form action="music_genre_find.do" method="post">
             	<span class="genre_span" >이슈/장르</span>
             	<select name="genre_data" id="">
	             	<c:forEach var="gd" items="${genre_data }">
	             		<option>${gd }</option>	
	             	</c:forEach>
             	</select>
             	<input type="submit" value="전송" />
             	</form>
             </li>
             </form>
         </ul>
       </div>
       </div>
       <div class="facet">
        <div class="facet-title">오늘의 날씨</div>
        <div id="ratings">
        	<c:forEach var="vo" items="${wlist }">
        		${vo.location }&nbsp; <img src="http://www.kma.go.kr/${vo.image }" alt="" />&nbsp;${vo.weather }<br>        	
        	</c:forEach>
        </div>
     
      </div>
    </div>
   <div class="ais-hits" data-reactroot="">
   <div class="ais-hits--item">
     <c:forEach var="vo" items="${list}" varStatus="status">
     	<c:if test="${status.index<5 }">
    <article class="movie">
    <embed class="movie-image" src="${vo.link }" />
    <div class="movie-meta">
      <div class="movie-title">
        <a href="detail.do?title=${vo.title }">${vo.title }</a>
        <span class="movie-year">
        ${ vo.singer }
        </span>
      </div>

      <div class="movie-rating">
         ${vo.rank }
      </div>

    </div>
   </article>
   </c:if>
  </c:forEach>
     <div id="pagination">
       <a href="main.do?page=${curpage>1?curpage-1:curpage }">이전</a>&nbsp;
       <a href="main.do?page=${curpage<totalpage?curpage+1:curpage }">다음</a>&nbsp;&nbsp;
       ${curpage } page / ${totalpage } pages
      </div>
   </div>
   </div>
    
  </div>
</body>
</html>