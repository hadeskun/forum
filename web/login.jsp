<%-- 
    Document   : login
    Created on : Jun 23, 2011, 1:18:49 AM
    Author     : naomi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
            <!--link rel="openid.server" href="http://localhost:8088/openidserver/endpoint"-->
            <!--link rel="openid2.provider openid.server" href="http://openid.evil-genius-network.com/server" /-->
            <!--link rel="openid2.local_id openid.delegate" href="http://openid.evil-genius-network.com/openid" /-->

            <script type='text/javascript' language='javascript'>


       function showhide(typeAuth) {
           document.register.chboxAuthOP.checked = false;
           document.register.chboxAuthT.checked = false;
                if (typeAuth == 'divTraditionalAuth') {

                     document.register.action='login.do';
                  document.getElementById(typeAuth).style.display = 'block';
                  document.getElementById('divOpenID').style.display = 'none';

                }
                else {
                   document.register.action='login.openid';
                  document.getElementById(typeAuth).style.display = 'block';
                  document.getElementById('divTraditionalAuth').style.display = 'none';


                }


      }
             </script>

        <link rel="stylesheet" href="styles/styles.css" type="text/css">

        <!--script language="javascript" type='text/javascript' src='scripts/alerta.js'></script-->




        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login My Chum</title>
    </head>
    <body onload="document.register.username.focus();"style=" margin:auto; padding: 0 0px; ">

        <!-- ENCABEZADO -->
        <TABLE width="100%" height="57" BORDER="0" cellSpacing=0 cellPadding=0 style="background-color:#edeff4;border-bottom:1px solid #d8dfea">

                         <TBODY>

                           <TR>
                               <TD align=center width="15%" hight=65 >
                                       <IMG height=57 src="imagenes/upc.png" width=70 border=0/>
                               </TD>

                               <TD width="100">&nbsp;</TD>

                               <TD align=CENTER class="forumlink" >

                                       <!--IMG height=57 src="imagenes/logoingi.jpg"  width=250 border=0/-->
					FORUM MY CHUM
                               </TD>

                               <TD align=CENTER width=20%>
                                   <IMG height=48 alt="" src="imagenes/foros.gif" width=48  border=0/>
                               </TD>
                           </TR>
            </TABLE>

        <br>

<table width="860" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="CENTER" class="forumlink"  >  <br>
		<!--WEB APPLICTION-->
        <br>
         <br>

         <br>
        <br>

                <br><br>
     <table width="860" border="0" align="center" cellpadding="0" cellspacing="0">
       <tr>
          <td height="35" ></td>
       </tr>
       <tr>

    <td >
      <table width="50%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td>
                <form action="login.do" method="post" name="register" target="_top">
                     <table width="100%" cellpadding="4" cellspacing="1" border="0" class="forumline" align="center">
                       <tr>
                            <th height="25" class="thHead" nowrap="nowrap">AUTHENTICATION</th>

                      </tr>

                      <tr>
                        <td class="row1">

                            <%--
                            [************************************]
                               Traditional authentication prompt
                            [************************************]
                            --%>
                        <div id='divTraditionalAuth' name='divTraditionalAuth' >
                            <table border="0" cellpadding="3" cellspacing="1" width="100%">
                              <tr>
                                    <td colspan="2" align="center">
                                        <input type="checkbox"  name="chboxAuthOP" value="Support Authetication Open ID" onclick='showhide("divOpenID");'>
                                       Support Authentication Open ID
                                        <br/>
                                    </td>
                                   
                              </tr>
                              
                              <tr>

                                    <td width="45%" align="right"><span class="gen">User:</span></td>
                                    <td align="left">

                                      <input type="text" name="username"  size="25" maxlength="40" value=""  />
                                    </td>
                              </tr>
                              <tr>
                                    <td align="right"><span class="gen">Password:</span></td>

                                    <td align="left">
                                      <input type="password"  name="password" size="25" maxlength="32"  />
                                    </td>

                              </tr>
                              <tr align="center">
                                  <!--%if (error.equals("")){%-->
                                        <td colspan="2">
						<table border="0" cellspacing="0" cellpadding="0">

					         <tr>
                                                <td><span class="gen">
						<img src="imagenes/Key.png"/></span></td><td><span class="cbstyled"></span></td></tr>
                                                </table>
					</td>
                                        <%--%}else{%>

                                        <td colspan="2"><table border="0" cellspacing="0" cellpadding="0"><tr><td><span class="gensmall">
                                             Usuario o Password Incorrecto
                                            </span></td></tr></table>

                                            </td>
                                        <!--%}--%>
                              </tr>
                              <tr align="center">
                                  <td colspan="2"><input type="submit"  name="login" class="mainoption" value="Lon in"   /></td>

                              </tr>
                              <tr align="center">

                                    <td colspan="2"><br></td>
                              </tr>
                          </table>
                        </div>
                              
                              
                              <%-- 
                            [************************************]
                            [       OpenID identification        ]
                            [************************************]
                            --%>

                    <div id='divOpenID' name='divOpenID'  style='display:none;'>
                            <table border="0" cellpadding="3" cellspacing="1" width="100%">
                              <tr>
                                    <td colspan="2" align="center">
                                       <input type="checkbox" name="chboxAuthT" value="Traditional Authentication" onclick='showhide("divTraditionalAuth");'>
                                       Traditional Authentication
                                    </td>
                              </tr>
                              <tr>
                                    <td width="45%" align="right"><span class="gen">Type Open ID:</span></td>
                                    <td align="left">

                                      <input type="text" name="OpenID"  size="25" maxlength="40" value=""  />
                                    </td>
                              </tr>                            
                              <tr align="center">                                 
                                        <td colspan="2">
						<table border="0" cellspacing="0" cellpadding="0">
					         <tr>
                                                <td><span class="gen">
						<img src="imagenes/Key.png"/></span></td><td><span class="cbstyled"></span></td></tr>
                                                </table>
					</td>                                       
                              </tr>                              
                              <tr align="center">
                                  <td colspan="2"><input type="submit"  name="login" class="mainoption" value="Confirm OpenID"   /></td>

                              </tr>
                              <tr align="center">

                                    <td colspan="2"><br></td>
                              </tr>
                          </table>
                    </div>





                         </td>
                      </tr>
                  </table>

              </form>
           </td>
        </tr>
      </table>
    </td>
   </tr>
   <tr>
     <td>&nbsp;</td>
   </tr>

 </table>


    </td>
</tr>

<tr>
    <td height="100%"><BR>&nbsp;
    <div align="center"></div>
    <br><br><br><br>

    <br><div align=center ><span class="genmed">Powered by CHUM FORUM © 2011, 2011 UPC</span></div>

    </td>
</tr>

<tr>
    <td height="100%">&nbsp;</td>
</tr>
</table>

<%--script>
if (history.forward(1)){
location.replace(history.forward(1))
}
</script--%>


</body>
</html>

