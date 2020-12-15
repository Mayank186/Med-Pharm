<%-- 
    Document   : change_password
    Created on : 14 Sep, 2016, 6:25:50 PM
    Author     : DHRUV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
    </head>
    <body>
        <jsp:include page="../stockiest/include/header.jsp"></jsp:include>
        <jsp:include page="../stockiest/include/css.jsp"></jsp:include>
        
        <%
            if(session.getAttribute("a_id")==null)
            {
                response.sendRedirect("../login.jsp");
            }
        %>

        
        <div id="body" style="min-height:100px; width: 600px">
             
                 <div class="content" style="width: 600px" >
                   
			
                     <center><h2>Change Password</h2></center>
                     <form action="change_password.jsp" method="post" >
                            
                            <label for="old_pwd"> <span>Old_pwd*</span>
                                <input type="password"  name="old"  id="Old_pwd">
			    </label>
                                
                            <label for="New_pwd"> <span>New_pwd*</span>
                                <input type="password"  name="new" id="New_pwd">
			    </label>
                            
                            <label for="Conf_pwd"> <span>Conf_pwd*</span>
                                <input type="password"  name="conf" id="Conf_pwd">
			    </label>
                            
                         
                            
                         <center> 
                             <input type="submit"  name="b1"  value="Update"  style="width: 90px; height: 30px">
                         </center>
                </form>
                    
        <%
            ResultSet rs=null;
            Connection con=null;
            Statement st=null;
            
            String action=null,dbpass=null,old=null,unew=null,uconf=null;

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
                old=request.getParameter("old");
                unew=request.getParameter("new");
                uconf=request.getParameter("conf");
                
                rs=st.executeQuery("select password from agancy_detail where a_id='"+session.getAttribute("a_id")+"'");
                
                while(rs.next())
                {
                    dbpass=rs.getString(1);
                }
                
                if(dbpass.equals(old))
                {
                    if(unew.equals(uconf))
                    {
                        int i=st.executeUpdate("update agancy_detail set password='"+unew+"' where a_id='"+session.getAttribute("a_id").toString()+"'");
                        
                        if(i>0)
                        {
                            session.removeAttribute("a_id");
                            response.sendRedirect("../login.jsp");
                        }
                        
                        else
                        {
                            out.println("Not Update");
                        }
                    }
                    
                    else
                    {
                        out.println("Conform password is not match");
                    }
                }
                
                else
                {
                    out.println("Old password is wrong");
                }
            }
        %>
         
                     
                     
                     
                     
                     
                     <br/>
                </div>
        </div>
        <br/>
        <br/>
        <jsp:include page="../stockiest/include/footer.jsp"></jsp:include>
    </body>
</html>
