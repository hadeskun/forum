<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
    Document   : index
    Created on : Mar 31, 2011, 11:26:31 AM
    Author     : Marta Lusarreta ,Julian, Armando
--%>

<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>


<% if(null == session.getAttribute("usernameSession")){%>
   <jsp:forward page="loginstart.do"/>
<% }else{
   // User IS logged in.
 }  %>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />


<script type="text/javascript" language="javascript">


var state = 'none';

function copyvalues(myfield)
{
if (myfield.inchange)return;
myfield.inchange=true;
myfield.value=myfield.value.toUpperCase();
myfield.inchange=false;
}


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
//-->
</script>

	<link rel='stylesheet' type='text/css' href='styles/main-rollup.css' />
	<meta  name='keywords' content='user, community, Forum my Chum 2011' />
	<meta  name='description' content='The stable release of forum my chum ' />

	<title> Forum My Chum @ have funny here</title>

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
<div id='my_chum_contact_menu' class='time shade footer_time'>
              <span class='time'> Today is Thursday June 23, 2011</span>
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
<li class="downpage.do" id="onotifications">
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
<div id='navbar' class='navbar' >
<form id='formlistpost' name ='formlistpost'  action='listReceivedPost.do' method='post'>
<ul id='navtabs' class='navtabs floatcontainer'>
<li class='selected'><a class='navtab' href='#'>Forum</a>
<ul class='floatcontainer'>

<li><a href="javascript: void(0)" onclick =" document.formlistpost.submit(); return false;">back to inBox</a></li>
<li class='popupmenu'>
<a href='javascript://' class='popupctrl' accesskey='6'>News</a>
<ul class='popupbody popuphover'>
<li><a href='maintenancePage.do'>option</a></li>
</ul>
</li>
</ul>
</li>
<li>
<a class='navtab' href='about the forum chum'  accesskey='2'>
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
<div id='pagetitle' class='pagetitle'>
	<!--un solve-->
</div>
<div id='postlist' class='postlist restrain'>

<!--c:forEach items="{sessionScope.messages}" var="row" -->
<%
                /*********************Get all product by category********/
               java.util.List posts = null;

                    /*int idpost ;
                    String sender = "";
                    String reeiver = "";
                    String subjectpost = "";
                    String textpost = "";
                    String replaydate = "";
                    int iduser;
                    double productPrice =0.0;*/
                    posts = (java.util.List<model.Row>)session.getAttribute("messages");//request.getAttribute("products");//products.getProducts(1);                   
                    for(java.util.Iterator<model.Row> iter =posts.iterator(); iter.hasNext();   ) {

                     model.Row splitpost = iter.next();
                    //while (iterator.hasNext()) {
                    //java.util.List<model.Row>  post = (java.util.Lisst<model.Row>) posts.;
                    

                    
%>

<ol id='posts' class='posts' start='1'>
<li class='postbit postbitim postcontainer' id='post'>
	<div class='postdetails_noavatar'>

		<div class='posthead'>
                        <span class='postdate old'>
                            <span class='date'> <%= splitpost.getReplaydate()%>&nbsp;</span>
				<span class='time'> </span>
                            </span>
                         
                         <span class='nodecontrols'>

                            <a name='post248409' href='#' class='postcounter'>#<%= splitpost.getIdPost()%></a>
                             <a id='postcount' name='1'></a>
                         </span>
		</div>
		<div class='userinfo_noavatar'>
			<div class='contact'>
				<div class='username_container'>
                                    <div class='popupmenu memberaction'>
<a class='username offline popupctrl' href='#' title='<%= splitpost.getSender()%> is offline'><strong><%= splitpost.getSender()%></strong>
                                      </a>

                                     </div>
				    <img class='inlineimg onlinestatus' src='imagenes/user-offline.png' alt='<%= splitpost.getReceiver()%>' border='0' />
					<span class='usertitle'>
						My chum Community
					</span>
					<div class='imlinks'>
					</div>
				</div>
			</div>

			<div class='userinfo_extra'>
				<dl class='userstats'>
				    <dt>Join Date</dt> <dd><%= splitpost.getReplaydate()%></dd>
                                    <dt>Posts</dt> <dd><%= splitpost.getIdPost()%></dd>
                                </dl>
                        </div>

		</div>
	</div>
	<div class='postbody'>
            <div class='postrow'>
		<h2 class='posttitle icon'>
			<img src='images/icons/icon1.png' alt='Post' />
                     <%= splitpost.getSubjectpost()%>
		</h2>
		<div class='content'>

			<div id='post_message_248409'>
				<blockquote class='postcontent restore'>
                                        <br />
                                        <br />
                                        <%= splitpost.getTextPost()%>
                                        
                                        <br />
                                        <br />
				</blockquote>

			</div>
		</div>
                  <!-- edit note -->
	       <blockquote class='postcontent lastedited'>
                    <!--img src='images/buttons_pentaho/edit_40b.png' alt='' /> -->
                        Last edited by <%= splitpost.getSender()%> at <span class='time'><%= splitpost.getReplaydate()%></span>
	      </blockquote>
			<!-- / edit note -->

         </div> <!--post row-->
</div><!--post body-->
      <!--module to reply-->
<div class='postfoot'>
	   <div class='textcontrols floatcontainer'>
	      <span class='postcontrols'>
                 <img style='display:none' id='progress_248409' src='imagenes/progress.gif'  alt='' />
                      <a id='qr_24499' class='quickreply' href='#quickReply' onclick='showhide("quickReply");' rel='nofollow'><img id='replyimg_254499' src='imagenes/clear.gif' alt='' /> Reply</a>

					<span class='seperator'>&nbsp;</span>
                      <a id='qrwq_248409' class='newreply' href='#quickReply' onclick='showhide("quickReply");' rel='nofollow' ><img id='quoteimg_248409' src='imagenes/clear.gif' alt='Reply With Quote' /> Reply</a>
	     </span>
             <span class='postlinking'>
             </span>
          </div>
</div>
	<hr />

</li>
</ol>


<%}%>



<div class='separator'></div>
<div class='postlistfoot'>
</div>
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
<form class='vbform' name='quick_reply' id='quick_reply' method='post' action='insertPost.do'>
	<div class='fullwidth'>
            <h3 id='quickreply_title' class='blockhead'>
                <img src='imagenes/reply_40b.png' alt='Quick Reply' style='float:left;padding-right:10px'/> Quick Reply<a name='quickreply'></a> <img style='display:none' id='progress_newreplylink_bottom' src='images/misc/progress.gif'  alt='' /></h3></div>
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

	<div class="navpopupmenu popupmenu nohovermenu" id="showthread_navpopup">
		<span class="shade">To:
		<a href="#" class="popupctrl"><span class="ctrlcontainer">Users</span></a>
                </span>
                <span class="shad">
                <input type='text' class='textbox default-value' name='user_target' id='user_target' size='10' accesskey='u' tabindex='0' value='?' />
                    subject:
                <input type='text' class='textbox default-value' name='subject' id='navbar_username' size='30' accesskey='u' tabindex='1' value='suject?' />
                </span>



<ul class="navpopupbody popupbody popuphover">
		<li class="optionlabel">Available Users</li>
                <li><a href='#quickReply' onclick="selectUser('sandy');" > martika</a></li>
                <li><a href='#quickReply' onclick="selectUser('sandy');" > julian</a></li>
		<li><a href='#quickReply' onclick="selectUser('armando');"> armando</a></li>
 </ul>

</div>
    <div class="clear"></div>
    <br>
		<div class='editor_textbox quickreply'>
				<textarea name='message' id='vB_Editor_QR_textarea' rows='10' tabindex='1' cols='80' dir='ltr' class='forum' </textarea>
		</div>
	</div>
</div>
<script type='text/javascript'>
//<!--
//	vB_Editor['vB_Editor_QR'] = new vB_Text_Editor('vB_Editor_QR', 1, '73', '1', undefined, '', 'forum'
//	var QR_EditorID = 'vB_Editor_QR';
//-->
</script>
		</div>
	</div>
	<div class='' >
		<div class=''>
			<input type='submit' class='button' value='Post Quick Reply' accesskey='s' title='(Alt + S)' name='sbutton' tabindex='1' id='qr_submit' onclick='clickedelm = this.value' />
			<input type='submit' class='button' value='Go Advanced' accesskey='x' title='(Alt + X)' name='preview' tabindex='1' id='qr_preview' onclick='clickedelm = this.value' />
			<input type='reset' id='qr_cancelbutton' class='button' style='display:none;' value='Cancel' accesskey='c' title='(Alt + C)' name='cancel' tabindex='4' onclick='qr_reset();' />
		</div>
	</div>
	<div id='qr_posting_msg' class='hidden'>

		<img src='imagenes/progress.gif' alt='Posting Quick Reply - Please Wait' />&nbsp;<strong>Posting Quick Reply - Please Wait</strong>
	</div>
	</div>

	
	<!-- Mozilla work around for focusing on QR in WYSIWYG mode -->
	<div id='qr_scroll'></div>
</form>
</div>















<!-- next / previous links -->

<%--div class='navlinks'>

	<strong>&laquo;</strong>
	<a href='showthread.php?t=79574&amp;goto=nextoldest' rel='nofollow'>Previous Thread</a>
	|
	<a href='showthread.php?t=79574&amp;goto=nextnewest' rel='nofollow'>Next Thread</a>
	<strong>&raquo;</strong>

</div--%>
<!-- / next / previous links -->


</div> <!-- closing div for body_wrapper -->

<div class='below_body'>

<div id='footer_time' class='shade footer_time'>All times are GMT -4. The time now is <span class='time'>06:47 PM</span>.</div>

</div>

</body>

</html>