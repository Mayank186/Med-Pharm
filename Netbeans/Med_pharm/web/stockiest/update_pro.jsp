<%-- 
    Document   : update_pro
    Created on : Sep 20, 2016, 9:53:26 AM
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
        <jsp:include page="../stockiest/include/header.jsp"></jsp:include>
        <jsp:include page="../stockiest/include/css.jsp"></jsp:include>
        
        <%
            String pid=null;
            
            if(session.getAttribute("a_id")==null)
            {
                response.sendRedirect("../login.jsp");
            }
            
           pid=request.getParameter("id");
           
            if(pid!=null)
            {
                session.setAttribute("upid", pid);
            }

           
        %>
        
        
        
        <%
            ResultSet rs=null;
            Connection con=null;
            Statement st=null;
            
            String name=null,des=null;

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/med_db","root","");
                st=con.createStatement();
                rs=st.executeQuery("select * from product_detail where pid='"+session.getAttribute("upid").toString()+"' ");
                
                while(rs.next())
                {
                    name=rs.getString(2);
                    des=rs.getString(3);
                }
                
            }
            
            catch(Exception e)
            {
                out.println("Driver Ex is:-"+e);
            }
        %>
        
              
        
        <div id="body" style="min-height:100px; width: 600px">
             
                <div class="content" style="width: 600px" >
                   
			
                    <center><h2>Product Detail</h2></center>
                
                        <form action="update_pro.jsp" method="post" >
                            
                            <label for="Pro_Nmae"> <span>Pro_Name*</span>
                                <input type="text" value="<%= name%>"  name="name" id="product">
			    </label>
                                
                         
                            <label for="Description"> <span>Description*</span>
                                <textarea name="des" id="Description"  style="width: 308px;height: 100px"><%= des%></textarea>
                            </label>

                            <center> 
                                 <input type="submit"  name="b1"  value="Update"  style="width: 90px; height: 30px">
                            </center>
                        </form>          
                            
                        <%
                            String action=null,uname=null,udes=null;
                            action=request.getParameter("b1");
                            
                            if(action!=null)
                            {
                                uname=request.getParameter("name");
                                udes=request.getParameter("des");
                                
                                if(action.equalsIgnoreCase("update"))
                                {
                                    int i=st.executeUpdate("update product_detail set pname='"+uname+"',pdes='"+udes+"' where pid='"+session.getAttribute("upid").toString()+"'");
                                    if(i>0)
                                    {
                                        
                                        
                                        response.sendRedirect("product.jsp");
                                        session.removeAttribute("upid");
                                    }
                                        
                                    else
                                    {
                                        
                                        
                                        response.sendRedirect("product.jsp");
                                        session.removeAttribute("upid");
                                    }
                                        
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
