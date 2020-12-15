<%-- 
    Document   : order
    Created on : 11 Mar, 2017, 11:39:53 AM
    Author     : DHRUV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body>
        <jsp:include page="../stockiest/include/header.jsp"></jsp:include>
        <jsp:include page="../stockiest/include/css.jsp"></jsp:include>
        
        <div id="body" style="min-height:100px; width: 650px ;margin-left: 300px">
              <div class="content" style="width: 600px" >
                  <center><h2>Order Detail</h2></center>
        
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
                 
            
                     try
                     {
                        Class.forName("com.mysql.jdbc.Driver");
                        con=DriverManager.getConnection("jdbc:mysql://localhost/med_db","root","");
                        st=con.createStatement();
                        
                        
                        rs=st.executeQuery("select *from order_detail  ");
                     
                     
                 %>
     
        
                 <center>
                     <table  style="border-style: solid;">
                         <tr>
                             <th style="width: 100px;height:30px;text-align:center;">Name</th>
                             <th style="width: 200px;height:30px;text-align:center;">Address</th>
                             <th style="width: 100px;height:30px;text-align:center;">Phone</th>
                             <th style="width: 250px;height:30px;text-align:center;">Product Name</th>
                             <th style="width: 30px;height:30px;text-align:center;">Rate</th>
                             <th style="width: 30px;height:30px;text-align:center;">QTY</th>
                             <th>Date</th>
                             <th style="width: 100px;height:30px;text-align:center;">Status</th>
                         </tr>
                         
                         <%     
                                while(rs.next())
                                {
                                     out.println("<tr><td style=width:100px;height:30px;text-align:center>"+rs.getString(2)+"</td><td style=width:100px;height:30px;text-align:center>"+rs.getString(3)+"</td><td style=width:100px;height:30px;text-align:center>"+rs.getString(4)+"</td><td style=width:100px;height:30px;text-align:center>"+rs.getString(5)+"</td><td  style=width:100px;height:30px;text-align:center>"+rs.getString(6)+"</td><td  style=width:100px;height:30px;text-align:center>"+rs.getString(7)+"</td><td  style=width:100px;height:30px;text-align:center>"+rs.getString(10)+"</td><td style=width:100px;height:30px;text-align:center><a href=response_order.jsp?id="+rs.getString(1)+">"+rs.getString(11)+"</a></td></tr>");
                                }
                                
                     }
                     
            
                    catch(Exception e)
                    {
                        out.println("Driver Ex is:-"+e);
                    }
                   
                         %>
                        
                     </table>
                 </center>    
                        </div>
                         </div>
                          <br/>
        <br/>

        <jsp:include page="../stockiest/include/footer.jsp"></jsp:include>
    </body>
</html>
