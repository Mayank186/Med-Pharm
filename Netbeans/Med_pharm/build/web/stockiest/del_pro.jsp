<%-- 
    Document   : del_pro
    Created on : Sep 17, 2016, 5:28:50 PM
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
                    session.setAttribute("dlpid", pid);
                 }
                
                        try
                        {
                          Class.forName("com.mysql.jdbc.Driver");
                          con=DriverManager.getConnection("jdbc:mysql://localhost/med_db","root","");
                          st=con.createStatement();
                          int i=st.executeUpdate("delete from product_detail where pid='"+session.getAttribute("dlpid").toString()+"'");
                          if(i>0)
                          {
                            int j=st.executeUpdate("delete from sub_pro where pid='"+session.getAttribute("dlpid").toString()+"'");
                                if(j>0)
                                {
                                    response.sendRedirect("product.jsp");
                                    session.removeAttribute("dlpid"); 
                                    
                                }
                                else
                                {
                                    
                                    response.sendRedirect("product.jsp");
                                    session.removeAttribute("dlpid"); 
                                }
                          }
                          else
                          {
                              
                              response.sendRedirect("product.jsp");
                              session.removeAttribute("dlpid"); 
                          }
                        }
                        catch(Exception e)
                        {
                            out.println("Driver Ex is:-"+e);
                        }
         %>
    </body>
</html>
