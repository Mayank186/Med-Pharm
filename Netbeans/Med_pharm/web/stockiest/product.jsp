<%-- 
    Document   : product
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

        
        <div id="body" style="min-height:100px; width: 600px">
             
                 <div class="content" style="width: 600px" >
                   
			
                     <center><h2>Product Detail</h2></center>
                     <form action="product.jsp" method="post" >
                            
                            <label for="Pro_Nmae"> <span>Pro_Name*</span>
                                <input type="text"  name="name" id="product">
			    </label>
                                
                         
                            <label for="Description"> <span>Description*</span>
                                <textarea name="des" id="Description"  style="width: 308px;height: 100px"></textarea>
                            </label>

                         <center> 
                             <input type="submit"  name="b1"  value="Save"  style="width: 90px; height: 30px">
                         </center>
                </form>
                     <% 
                            Connection con=null;
                            Statement st=null;
                            ResultSet rs=null;
                            String action=null,pname=null,des=null;
                            
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
                            pname=request.getParameter("name");
                            des=request.getParameter("des");
                                if(action.equals("Save"))
                                {
                                    int i=st.executeUpdate("insert into product_detail(pname,pdes)values('"+pname+"','"+des+"')");
                                    
                                }
                        }
                        rs=st.executeQuery("select *from product_detail");
                        
                            
                     %>
                     <br/>
                     <br/>
                     <center><table cellpadding="5" cellspacing="5" border="1">
                         <tr>
                             <th>Name</th>
                             <th>Description</th>
                             <th></th>
                             <th></th>
                             <th></th>
                         </tr>
                         <%
                             
                                while(rs.next())
                                {
                                    out.println("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td ><a href=sub_product.jsp?id="+rs.getString(1)+">Sub Product</a></td><td><a href=del_pro.jsp?id="+rs.getString(1)+">Delete</a><td><a href=update_pro.jsp?id="+rs.getString(1)+">Update</a></td></td></tr>");
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
