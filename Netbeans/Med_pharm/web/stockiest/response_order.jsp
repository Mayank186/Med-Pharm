<%-- 
    Document   : response_order
    Created on : Mar 14, 2017, 4:58:38 PM
    Author     : DHRUV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Response_Order</title>
    </head>
    <body>

        <jsp:include page="../stockiest/include/header.jsp"></jsp:include>
        <jsp:include page="../stockiest/include/css.jsp"></jsp:include>
        
        <%
            String o_id=null;
            
            if(session.getAttribute("a_id")==null)
            {
                response.sendRedirect("../login.jsp");
            }
            
           o_id=request.getParameter("id");
           
            if(o_id!=null)
            {
                session.setAttribute("uo_id", o_id);
            }

           
        %>
        
        
        <%
                         
                ResultSet rs=null;
                Connection con=null;
                Statement st=null;
                         
                String action=null,ustatus=null;
                         
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    con=DriverManager.getConnection("jdbc:mysql://localhost/med_db","root","");
                    st=con.createStatement();
                    rs=st.executeQuery("select * from order_detail where o_id='"+session.getAttribute("uo_id").toString()+"' ");
                }
                        
                catch(Exception e)
                {
                    out.println("Driver Ex is:-"+e);
                }
            
        %>
                     

        
        <div id="body" style="min-height:100px; width: 335px">
             
                 <div class="content" style="width: 300px" >
                   
			
                     <center><h2>Order Status</h2></center>
                     
                     
                     <form action="response_order.jsp" method="post" >
                         
                         <label><span>Select Status*</span>
                            <select name="status" id="status">
    
                                <option value="pending">Pending</option>
                                <option value="ready">Ready</option>
                                <option value="dispatch">Dispatch</option>
                                  
                            </select>
                         </label>
                            <br/>
                            <br/>
        
                         <center> <input type="submit"  name="b1"  value="Update"  style="width: 90px; height: 30px"></center>
                </form>
                     
        <%
        
            action=request.getParameter("b1");
                            
            if(action!=null)
            {
                ustatus=request.getParameter("status");
                               
                    if(action.equalsIgnoreCase("update"))
                    {
                        int i=st.executeUpdate("update order_detail set status='"+ustatus+"' where o_id='"+session.getAttribute("uo_id").toString()+"'");
  
                        if(i>0)
                        {
                            session.removeAttribute("uo_id");
                            response.sendRedirect("order.jsp");
                        }
                                        
                        else
                        {
                            session.removeAttribute("uo_id"); 
                            response.sendRedirect("order.jsp");
                        }
                                        
                    }
            }
        %>
                         
    
        </div>
        </div>
        <br/>
        <br/>
        <jsp:include page="../stockiest/include/footer.jsp"></jsp:include>
    </body>
</html>
