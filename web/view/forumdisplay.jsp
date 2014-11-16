<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
    Document   : index
    Created on : Mar 31, 2011, 11:26:31 AM
    Author     : Marta Lusarreta ,Julian, Armando
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">




<% if(null == session.getAttribute("usernameSession")){%>
   <jsp:forward page="loginstart.do"/>
<% }else{
   // User IS logged in.
 }  %>

<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<script type='text/javascript' language='javascript'>
var state = 'none';
function selectUser(destinationUser) {
document.getElementById("user_target").value=destinationUser;
}
function showhide(layer_ref) {
if (state == 'block') {
state = 'none';
}
else {
state = 'block';
}
if (document.all) { //IS IE 4 or 5 (or 6 beta)
eval( 'document.all.' + layer_ref + '.style.display = state');
}
if (document.layers) { //IS NETSCAPE 4 or below
document.layers[layer_ref].display = state;
}
if (document.getElementById &&!document.all) {
hza = document.getElementById(layer_ref);
hza.style.display = state;
}
}
</script>
<link rel='stylesheet' type='text/css' href='styles/main-rollup.css' />
<link rel='stylesheet' type='text/css' href='styles/forumdisplay-rollup.css'/>
<meta  name='keywords' content='user, community, Forum my Chum 2011' />
<meta  name='description' content='The stable release of forum my chum ' />
<title> Forum My Chum @ have funny here</title>
<!--script type='text/javascript' src='clientscript/vbulletin_lightbox.js?v=410'></script-->
 <link rel='stylesheet' type='text/css' href='styles/showthread-rollup.css' />
 <link rel='stylesheet' type='text/css' href='styles/additional.css' />
</head>

<body>
<!-- BEGIN Chum Header -->


<div id='my_chum_header'>
    <div id='my_chum_logo' >
        <table align='center' bgcolor='white' border='0' cellpadding='0' cellspacing='0' style='margin:0px;'   >
        <tr>
          <td style='margin:0px;'>
            <a href='http://forums.mychum/' title='Chum Home'>
             <img src='imagenes/my chum.png' alt='My Forum Chum' border='0'/>
            </a>

          </td>
            <td style='padding-top: 50px;'>
                <h1>
                <span class='threadtitle'><a href='regster' rel='nofollow'>Forum my chum  </a></span>
                </h1>
             </td>
         </tr>
             </table>

    </div>
    <!--right menu header-->
    <div id='my_chum_menu'>
   <div id='my_chum_contact_menu' class='time shade footer_time' styles>
   
              <span class='time'> Today is Thursday June 23, 2011</span>
               <img height="30" src='imagenes/news.gif' alt='extra, extra !!!' border='0' />
        </div>
    </div>
    <div class='clear'></div>

</div>
<!-- END Chum Header -->
<div class='above_body' > <!-- closing tag is in template navbar -->
<div id='header' class='floatcontainer doc_header' >
	<div id='toplinks' class='toplinks'  >
<ul class="isuser">
<li><a href="logout.jsp" onclick="return log_out('Are you sure you want to log out?')">Log Out</a></li>
<li><a href="maintenancePage.do">Settings</a></li>
<li><a href="maintenancePage.do">My Profile</a></li>
<li class="downpage.cgi" id="onotifications">
<a class="popupctrl" href="maintenancePage.do">future ideas</a>

<ul class="popupbody popuphover">
<li>No new messages</li>
<li><a href="maintenancePage.do">Inbox</a></li>
</ul>
</li>
 
<li class="welcomelink">Welcome, <a href="maintenancePage.do"><c:out value="${usernameSession}"/> </a></li>
</ul>
</div>
<hr/>
</div>
<div id='navbar' class='navbar'>
<form id='formlistAllpost' name ='formlistAllpost'  action='forumMychum.cgi' method='post'>
<input type='hidden'  name='sentreceivedpost' value='1' />

<ul id='navtabs' class='navtabs floatcontainer'>
<li class='selected'><a class='navtab' href='#'>Forum</a>
<ul class='floatcontainer'>
<li><a href='listReceivedPost.do'>Today's posts</a></li>
<li class='popupmenu'>
<li><a href="javascript: void(0)" onclick ="document.formlistAllpost.action='showthread.do'; document.formlistAllpost.submit(); return false;">View all Posts</a></li>
<li><a href="javascript: void(0)" onclick =" document.formlistAllpost.sentreceivedpost.value='1';document.formlistAllpost.action='listReceivedPost.do'; document.formlistAllpost.submit(); return false;">View inBox</a></li>
<li><a href="javascript: void(0)" onclick =" document.formlistAllpost.sentreceivedpost.value='0';document.formlistAllpost.action='listSentPost.do'; document.formlistAllpost.submit(); return false;">View sent Post</a></li>
<ul class='popupbody popuphover'>
<li><a href='maintenancePage.do'>option</a></li>
</ul>

</ul>
</li>
<li>
<a class='navtab' href='maintenancePage.do'  accesskey='2'>
What's New? aah!! nothing we just want to sleep a little
</a>
</li>
</ul>
</form>
</div>
</div><!-- closing div for above_body -->
<div class='body_wrapper'>
<div id='breadcrumb' class='breadcrumb'>
<hr />
</div>
<!--Pagination-->
<div id='above_postlist' class='above_postlist'>

    <a href='#quickReply' onclick='showhide("quickReply");' class='newcontent_textcontrol' id='newreplylink_bottom'>
        <span>+</span> Reply to Thread</a>
	    <img style='display:none' id='progress_newreplylink_top' src=''  alt='' />
		<div id='pagination_top' class='pagination_top'>
<form action='listPosts.cgi' name='formPagination' id='formPagination' method='POST' class='pagination popupmenu nohovermenu'>
<span><a href='javascript://' class='popupctrl'>Page 1 of 1</a></span>

<input type='hidden'  name='num_page' value='1' />
<ul class="popupbody popuphover">

<li class="formsubmit jumptopage"><label>Jump to page: <input type="text" name="page" size="4" /></label> <input type="submit" class="button" value="Go" /></li>
</ul>
</form>
<div id='postpagestats_above' class='postpagestats'>
Results 1 to 10 of 10
			</div>
		</div>
</div>
<div id='pagetitle' class='pagetitle'>
	<!--un solve-->
</div>
<div id='threadlist' class='threadlist'>

			<form id='formlistpost' name ='formlistpost'  action='showthread.do' method='post'>
<input type='hidden'  name='id_post' value='1' />
<input type='hidden'  name='date_post' value='1' />
<input type='hidden'  name='time_post' value='1' />
		<h2 class='hidden'>Post Forum my Chum</h2>
		<!--div-->
			<div class='threadlisthead table'>
				<div>
				<span class='threadinfo'>
					<span class='threadtitle'><a href='#' rel='nofollow'>Posts</a> </span>

				</span>
					<span class='threadstats td'>
                                            <a href='#' rel='nofollow'>Delete         </a>
                                        </span>
					<span class='threadlastpost td'>
                                            <a href='#' rel='nofollow'>Receiver
                                            </a></span>
				</div>
			</div>



     <%

    // java.util.List posts = (java.util.List)session.getAttribute("messages");

//     posts.size();
  /*   while(posts.next()){
     String subjectpost = posts.getString("subjectpost");
     String replydate = posts.getString("replydate");
     String sender = posts.getString("sender");
     String  receiver = posts.getString("receiver");*/
     //java.util.List posts = (java.util.List)request.getAttribute("messages");
  //   posts.size();
    %>
    
    <c:forEach items="${sessionScope.messages}" var="row" >
   



<li class='threadbit hot' id='${row.numpost}'>
	    <div class='rating0 sticky'>
		<div class='threadinfo' title='Title of Post number ${row.numpost} - 07-May-11 23:58:34' >
			<!--  status icon block -->

                        <!--  status icon block -->
			<a class='threadstatus' ></a>
			<!-- title / author block -->
			<div class='inner'>
				<h3 class='threadtitle'>
                                  <img src='imagenes/icon1.png' alt='Post' border='0' />
                                  <span class='prefix understate'>Post:</span>
                                        <a class='title' href="javascript: void(0)"   onclick=" document.formlistpost.id_post.value='${row.numpost}'; document.formlistpost.time_post.value='23:58:34'; document.formlistpost.date_post.value='${row.replaydate}';     document.formlistpost.submit();    return false;" title = ' ${row.subjectpost}'>

                                          ${row.subjectpost}
                                        </a>
				</h3>
				<div class='threadmeta'>
					<p class='threaddesc'>${row.replaydate}</p>
					<div class='author'>
           					<span class='label'>Started by &nbsp;<a href='#' class='username understate' title='Started by kelly '> ${row.sender}</a>,&nbsp;${row.replaydate} </span>

					</div>
				</div>
			</div>
		</div>
		<ul class='threadstats td alt' title=''>
                    <li>
                    <a class='username offline popupctrl' href="javascript: void(0)"   onclick=' document.formlistpost.id_post.value="${row.numpost}"; document.formlistpost.time_post.value="1"; document.formlistpost.date_post.value="1"; document.formlistpost.action="deletePost.do";     document.formlistpost.submit();    return false;' title='user'>
                                <strong>delete</strong>

                            </a>
                     <input  type='checkbox' name='2'   name='2' value='2'  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
		</ul>
		<!-- lastpost -->
		<dl class='threadlastpost td'>
                   <dd><div class='popupmenu memberaction'>
                            <a class='username offline popupctrl' href='#' title='user'>
                                <strong> ${row.receiver}</strong>
                            </a>
                  </div>

                   </dd>
			<dd> Received: ${row.replaydate} <em class='time'> </em>
			<a href='#' class='lastpostdate understate' title='''>
                            <img src='imagenes/lastpost-right.png' alt='User' /></a>
			</dd>
		</dl>
	</div>

</li>

</c:forEach>

<hr />
   <div class='noinlinemod forumfoot'>
		</div>
	</form>
   </div>
<div class='clear'></div>
<div class='separator'></div>
<div class='postlistfoot'>
</div>
<div id='below_postlist' class='noinlinemod below_postlist'>
    <a href='#quickReply' onclick='showhide("quickReply");' class='newcontent_textcontrol' id='newreplylink_bottom'>

        <span>+</span> Reply to Thread</a>
	<div id='pagination_bottom' class='pagination_bottom'>
            <div class='clear'></div>
	</div>
</div>
<!-- quick reply-->
<div id='quickReply' name='quickReply'  style='display:none;'>
<form  action='insertPost.do' name='quick_reply' id='quick_reply' method='post' >
	<div class='fullwidth'>
            <h3 id='quickreply_title' class='blockhead'>
                <img src='imagenes/reply_40b.png' alt='Quick Reply' style='float:left;padding-right:10px'/> Quick Reply<a name='quickreply'></a> <img style='display:none' id='progress_newreplylink_bottom' src=''  alt='' /></h3></div>
	<div class='wysiwyg_block'>

            <div class='blockbody formcontrols' style='height:200px;'>
		<div class='blockrow'>
            <div id='vB_Editor_QR' class='texteditor'>
                <div class='editor quickreply'>
		<div class='editor_controls floatcontainer' id='vB_Editor_QR_controls'>

			<ul class='right editor_control_group'>
</ul>
                        <ul class='editor_control_group'>
				<li class='editor_control_group_item'>
					<img src='imagenes/quote.png' class='imagebutton' id='vB_Editor_QR_cmd_wrap0_quote' width='20' height='20' alt='Wrap [QUOTE] tags around selected text' />
				</li>
			</ul>
		</div>
    		<div class='clear'></div>
	<div class='navpopupmenu popupmenu nohovermenu' id='showthread_navpopup' style ='float:left;margin-top:0px;'>

		<span class='shade'>To:
                 <select name="users" onchange="selectUser(this.value)">

                 <option value="armando">Users</option>
                 </select>

                </span>
               <span class='shad'>
                    <input type='text' class='textbox default-value' name='user_target' id='user_target' size='10' accesskey='u' tabindex='0' value='?' />
                    subject:
                <input type='text' class='textbox default-value' name='subject' id='subject' size='30' accesskey='u' tabindex='1' value='suject?' />
                </span>

            <ul class="navpopupbody popupbody popuphover">
		<li class="optionlabel">Available Users</li>
                <li><a href='#quickReply' onclick="selectUser('martika');" > martika</a></li>
                <li><a href='#quickReply' onclick="selectUser('julian');" > julian</a></li>
		<li><a href='#quickReply' onclick="selectUser('armando');"> armando</a></li>
 </ul>
</div>
                        <div class='clear'></div>

		<div class='editor_textbox quickreply' style ='margin-top:0px;'>
                    <textarea name='message' id='vB_Editor_QR_textarea' rows='10' tabindex='1' cols='80' dir='ltr' class='forum'> </textarea>
		</div>
	</div>
</div>
		</div>
	</div>
	<div class='' >
		<div class=''>

			<input type='submit' class='button' value='Post Quick Reply' accesskey='s' title='send' name='sbutton' tabindex='1' id='qr_submit' onclick='clickedelm = this.value' />
			<input type='submit' class='button' value='Go Advanced' accesskey='x' title='(Alt + X)' name='preview' tabindex='1' id='qr_preview' onclick='clickedelm = this.value' />
			<input type='reset' id='qr_cancelbutton' class='button' style='display:none;' value='Cancel' accesskey='c' title='(Alt + C)' name='cancel' tabindex='4' onclick='qr_reset();' />
		</div>
	</div>
	<div id='qr_posting_msg' class='hidden'>
		<img src='imagenes/progress.gif' alt='Posting Quick Reply - Please Wait' />&nbsp;<strong>Posting Quick Reply - Please Wait</strong>
	</div>

	</div>
</form>
</div>
</div> <!--wrapper-->
<div class='below_body'>
<div id='footer_time' class='shade footer_time'>Copy Right 2010-2011<span class='time'>Forum My Chum</span>.</div>
</div>
</body>
</html>
