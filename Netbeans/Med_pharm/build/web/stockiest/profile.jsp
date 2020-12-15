<%-- 
    Document   : profile
    Created on : 5 Sep, 2016, 6:25:50 PM
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
            if(session.getAttribute("a_id")==null)
            {
                response.sendRedirect("../login.jsp");
            }
        %>
        
        <%
            ResultSet rs=null;
            Connection con=null;
            Statement st=null;
            
            String name=null,address=null,email=null,phone=null,description=null;

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/med_db","root","");
                st=con.createStatement();
                rs=st.executeQuery("select * from agancy_detail where a_id='"+session.getAttribute("a_id")+"' ");
                
                while(rs.next())
                {
                    name=rs.getString(2);
                    address=rs.getString(3);
                    email=rs.getString(4);
                    phone=rs.getString(6);
                    description=rs.getString(7);
                }
                
            }
            
            catch(Exception e)
            {
                out.println("Driver Ex is:-"+e);
            }
        %>
        
        <div id="body" style="min-height:100px; width: 600px">
             
                 <div class="content" style="width: 600px" >
                   
			
                     <center><h2>Profile Detail</h2></center>
                     <form action="profile.jsp" method="post" >
                            
                            <label for="firstName"> <span>Name*</span>
                                <input type="text" name="name" value="<%= name%>"  id="firstName">
			    </label>
                                
                            <label for="Address"> <span>Address*</span>
                                <textarea name="addr" id="Address"  style="width: 308px;height: 100px"><%= address%></textarea>
                            </label>
			
                            <label for="email"> <span>email*</span>
                                <input type="text" name="email" value="<%=email%>" id="email" >
                            </label>
                         
                            <label for="phoneNumber"> <span>Phone Number*</span>
                                <input type="text" name="number" value="<%=phone%> " id="phoneNumber">
                            </label>
                         
                            <label for="Description"> <span>Description*</span>
                                <textarea name="des" id="Description"  style="width: 308px;height: 100px"><%= description%></textarea>
                            </label>

                         <center> 
                             <input type="submit"  name="b1"  value="Update"  style="width: 90px; height: 30px">
                         </center>
                </form>
                        <%
                            String action=null,uname=null,uaddress=null,uemail=null,uphone=null,udes=null;
                            action=request.getParameter("b1");
                            
                            if(action!=null)
                            {
                                uname=request.getParameter("name");
                                uaddress=request.getParameter("addr");
                                uemail=request.getParameter("email");
                                uphone=request.getParameter("number");
                                udes=request.getParameter("des");
                                
                                if(action.equalsIgnoreCase("update"))
                                {
                                    int i=st.executeUpdate("update agancy_detail set name='"+uname+"',address='"+uaddress+"',email='"+uemail+"',number='"+uphone+"',des='"+udes+"' where a_id='"+session.getAttribute("a_id").toString()+"'");
                                    if(i>0)
                                    {
                                        
                                        response.sendRedirect("profile.jsp");
                                      // session.removeAttribute("a_id"); 
                                   
                                    }
                                     else
                                    {
                                       
                                        response.sendRedirect("profile.jsp");
                                         session.removeAttribute("a_id");
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
