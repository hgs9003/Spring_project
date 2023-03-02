<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>Pricing example · Bootstrap v4.6</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/pricing/">

    
<%@include file="/WEB-INF/views/include/common.jsp" %>
    



    <!-- Favicons 
<link rel="apple-touch-icon" href="/docs/4.6/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/4.6/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/4.6/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon.ico">
<meta name="msapplication-config" content="/docs/4.6/assets/img/favicons/browserconfig.xml">
-->
<meta name="theme-color" content="#563d7c">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    <script>
      let msg = '${msg}';
      if(msg != '') {
        alert(msg);
      }

    </script>
    
    
  </head>
  <body>
    
<%@include file="/WEB-INF/views/include/header.jsp" %>

<h3>비밀번호 재확인</h3>

<div class="container">
  <div class="mb-3 text-center">
	  <form id="confirmPWForm" action="confirmPW" method="post">
		  <div class="form-group row">
		    <label for="mem_pw" class="col-sm-2 col-form-label">비밀번호</label>
		    <div class="col-sm-5">
		      <input type="password" class="form-control" id="mem_pw" name="mem_pw" placeholder="비밀번호를  8~15이내로 입력" required>
		    </div>
		  </div>
		  <div class="form-group row">
			  <div class="col-sm-9 text-center">
			  	<button type="submit" class="btn btn-dark" id="btnconfirmPW">확인</button>
			  	<button type="button" class="btn btn-dark" id="btnCancel">취소</button>
			  </div>			
		  </div>
	 </form>
  </div>


  <!--  footer.jsp -->
  <%@include file="/WEB-INF/views/include/footer.jsp" %>
</div>

  <script>

    $(document).ready(function() {
      $("#btnSearchIDPW").on("click", function() {
        location.href = "/member/lostpass";
      });
    });

  </script>
  </body>
</html>
