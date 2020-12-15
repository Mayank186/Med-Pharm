<%-- 
    Document   : login
    Created on : 1 Sep, 2016, 6:25:50 PM
    Author     : DHRUV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <jsp:include page="include/header.jsp"></jsp:include>
        <jsp:include page="include/css.jsp"></jsp:include>
        <div id="body" style="min-height:100px; width: 600px">
             
                 <div class="content" style="width: 600px" >
                   
			
                     <center><h2>Login Detail</h2></center>
                     <form action="login.jsp" method="post" >
                            <label for="firstName" > <span>Email*</span>
					<input type="text" name="uname" id="firstName"><br/><br/><br/>
                                </label>
                                        <label for="lastName"> <span>Password*</span>
                                            <input type="password" name="pass" id="lastName"><br/>
				</label><br/>
                                <center> 
                                    <input type="submit"  name="b1" value="Submit" style="width: 90px; height: 30px" >
                                </center>
                </form>
                     
                     <br/><center><a href="registration.jsp"> New User ?</a> </center>
             <%
                     ResultSet rs=null;
                     Connection con=null;
                     Statement st=null;
                     String action=null,dpass=null,duname=null,upass=null,uuname=null,a_id=null;
                     int flag=0;
                        try
                        {
                          Class.forName("com.mysql.jdbc.Driver");
                          con=DriverManager.getConnection("jdbc:mysql://localhost/med_db","root","");
                          st=con.createStatement();
                        }
                        catch(Exception e)
                        {
                            out.println("Driver Ex is:-"+e);
                        }
                        action=request.getParameter("b1");
                        if(action!=null)
                        {
                            uuname=request.getParameter("uname");
                            upass=request.getParameter("pass");
                            try
                            {
                                rs=st.executeQuery("select a_id,email,password from agancy_detail");
                                while(rs.next())
                                {
                                    a_id=rs.getString(1);
                                    duname=rs.getString(2);
                                    dpass=rs.getString(3);
                                    if((uuname.equals(duname)) && (upass.equals(dpass)))
                                    {
                                        flag=1;
                                        break;
                                    }
                                }
                                if(flag==1)
                                {
                                    session.setAttribute("a_id", a_id);
                                    response.sendRedirect("stockiest/profile.jsp");
                                }
                                else
                                {
                                    out.println("Invalid USer Name and Password");
                                }
                            }
                            catch(Exception e)
                            {
                                out.println("Insert Ex is:-"+e);
                            }
                        }
                 %>
                </div>
        </div>
        <br/>
        <br/>
       <jsp:include page="include/footer.jsp"></jsp:include>
    </body>
</html>
