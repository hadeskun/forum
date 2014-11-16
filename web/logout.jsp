<%-- 
    Document   : closesession
    Created on : Jun 25, 2011, 3:28:20 AM
    Author     : naomi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    session.invalidate();   
%>
 <jsp:forward page="loginstart.do"/>