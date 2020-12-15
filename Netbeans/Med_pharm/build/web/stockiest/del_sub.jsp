<%-- 
    Document   : del_sub
    Created on : Sep 20, 2016, 6:07:34 PM
    Author     : DHRUV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
                Connection con=null;
                Statement st=null;
  
                String pid=null;
                
                if(session.getAttribute("a_id")==null)
                {
                    response.sendRedirect("../login.jsp");
                }
            
                pid=request.getParameter("id");
                 
                if(pid!=null)
                 {
                    session.setAttribute("dlid", pid);
                 }
           
                
                        try
                        {
                          Class.forName("com.mysql.jdbc.Driver");
                          con=DriverManager.getConnection("jdbc:mysql://localhost/med_db","root","");
                          st=con.createStatement();
                          int i=st.executeUpdate("delete from sub_pro where sp_id='"+session.getAttribute("dlid")+"'");
                          if(i>0)
                          {
                              
                              response.sendRedirect("product.jsp");
                              session.removeAttribute("dlid");
                          }
                              
                          else
                          {
                               
                              response.sendRedirect("product.jsp");
                              session.removeAttribute("dlid");
                          }
                              
                        }
                        catch(Exception e)
                        {
                            out.println("Driver Ex is:-"+e);
                        }
         %>
    </body>
</html>
