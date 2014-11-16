<%-- 
    Document   : login redirect
    Created on : Apr 19, 2011, 10:41:55 PM
    Author     : naomi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en" id="mychum_html">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="styles/main-rollup.css" />
	<title>MyChum Community Forums</title>
	<link rel="stylesheet" type="text/css" href="styles/additional.css" />

</head>
<body>
   <%--jsp:forward page="/listSentPost.do"/--%>
<div class="standard_error">
	<form class="block vbform" method="post" action="forumMyChum.cgi" name="postvarform">
		<h2 class="blockhead">Redirecting...</h2>
		<div class="blockbody formcontrols">
                    <%String username  =(String)session.getAttribute("usernameSession");//request.getAttribute("products");//products.getProducts(1);%>

                    <p class="blockrow restore">Thank you for logging in, <%= username%></p>

		</div>
		<div class="blockfoot actionbuttons redirect_button">
			<div class="group" id="redirect_button">

					<a href="#" class="textcontrol">Click here if your browser does not automatically redirect you.</a>

			</div>
		</div>
	</form>

</div>



<noscript>
<meta http-equiv="Refresh" content="2; URL=forumMyChum.cgi" />
</noscript>

<script type="text/javascript">
<!--
function exec_refresh()
{
	window.status = "Redirecting..." + myvar;
	myvar = myvar + " .";
	var timerID = setTimeout("exec_refresh();", 100);
	if (timeout > 0)
	{
		timeout -= 1;
	}
	else
	{
		clearTimeout(timerID);
		//window.status = "";
		location.href = "redirectPage.do";

	}
}

var myvar = "";
var timeout = 10;
exec_refresh();
//-->
</script>





</body>
</html>
