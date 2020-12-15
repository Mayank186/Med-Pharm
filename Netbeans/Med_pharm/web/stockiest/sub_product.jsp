<%-- 
    Document   : sub_product
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
            String pid=null;
            if(session.getAttribute("a_id")==null)
            {
                response.sendRedirect("../login.jsp");
            }
            pid=request.getParameter("id");
            
            
            
            if(pid!=null)
            {
                session.setAttribute("pid", pid);
            }
           
        %>

        
        <div id="body" style="min-height:100px; width: 635px">
             
                 <div class="content" style="width: 600px" >
                   
			
                     <center><h2>Sub_Product Detail</h2></center>
                     <form action="sub_product.jsp" method="post" >
                            
                            <label for="Sub_Pro_name"> <span>SubProduct_Name*</span>
                                <input type="text"  name="name" id="product">
			    </label>
                                
                         
                            <label for="Description"> <span>Content*</span>
                                <textarea name="con"  id="Content" style="width: 208px;height: 100px"></textarea>
                            </label>
                         
                            <label for="rate"> <span>rate*</span>
                                <input type="number" name="rate"  id="rate">
			    </label>
                         
                         <label><span>Company*</span>
                            <select name="company" id="company">  
                                <option value="">Select Company</option>  
                                <option value="torrent">Torrent</option>
                                <option value="elite">Elite</option>
                                <option value="himalaya">Himalaya</option>
                                <option value="shakar">Shakar</option>  
                                <option value="dishman">Dishman</option>  
                                <option value="cadila">Cadila</option>  
                                <option value="apollo">Apollo</option>  
                            </select>
                         </label>
                         <!--   
                         <label for="stock" > <span>stock*</span>
                                <input type="number" name="stock"  id="stock">
			    </label>
                         -->
                            <label for="Mfg_date"> <span>Mfg_Date*</span>
                                <input type="date" name="Mfg_date" id="Mfg_date" >
			    </label>
                         
                            
                            <label for="Exp_date"> <span>Exp_Date*</span>
                                <input type="date" name="Exp_date" id="Mfg_date">
			    </label>
                         
                             
                            <label for="stock"> <span>stock*</span>
                                <input type="number" name="stock"  id="stock">
			    </label>

                         <center> <input type="submit"  name="b1"  value="Save"  style="width: 90px; height: 30px"></center>
                </form>
                <%
                     Connection con=null;
                     Statement st=null;
                     ResultSet rs=null;
                     String action=null,name=null,des=null,rate=null,company=null,mfg=null,exp=null,stock=null;
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
                            name=request.getParameter("name");
                            des=request.getParameter("con");
                            rate=request.getParameter("rate");
                            company=request.getParameter("company");
                            mfg=request.getParameter("Mfg_date");
                            exp=request.getParameter("Exp_date");
                            stock=request.getParameter("stock");
                            try
                            {
                                int i=st.executeUpdate("insert into sub_pro(name,content,rate,company,mfg_date,exp_date,pid,stock)values('"+name+"','"+des+"','"+rate+"','"+company+"','"+mfg+"','"+exp+"','"+session.getAttribute("pid").toString()+"','"+stock+"')");
                                if(i>0)
                                {
                                   
                                    response.sendRedirect("product.jsp");
                                }
                                    
                                else
                                {
                                    
                                    out.println("Not Submit");
                                }
                                    
                            }
                            catch(Exception e)
                            {
                                out.println("Insert Ex is:-"+e);
                            }
                        }
                        rs=st.executeQuery("select *from sub_pro where pid='"+session.getAttribute("pid").toString()+"'");
                 %>
                     <br/>
                     
                     
                     <table  cellpadding="2" cellspacing="3" border="1">
                         <tr>
                             <th>Name</th>
                             <th>Content</th>
                             <th>Rate</th>
                             <th>Company</th>
                             <th>Mfg_date</th>
                             <th>Exp_date</th>
                             <th>Stock</th>
                             <th></th>
                             <th></th>
                         </tr>
                         
                         <%     
                                while(rs.next())
                                {
                                    out.println("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td >"+rs.getString(10)+"</td><td><a href=update_sub.jsp?id="+rs.getString(1)+">Update</a></td><td><a href=del_sub.jsp?id="+rs.getString(1)+">Delete</a></td></tr>");
                                }
                                
                                
                         %>
                        
                     </table>
                     
        
                     
                </div>
        </div>
        <br/>
        <br/>
        <jsp:include page="../stockiest/include/footer.jsp"></jsp:include>
    </body>
</html>
