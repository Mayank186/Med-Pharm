<%-- 
    Document   : update_sub
    Created on : Sep 20, 2016, 6:07:00 PM
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
                session.setAttribute("spid", pid);
            }
           
        %>

        
        <%
            ResultSet rs=null;
            Connection con=null;
            Statement st=null;
            
            String name=null,content=null,rate=null,stock=null,mfg=null,exp=null,company=null;

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/med_db","root","");
                st=con.createStatement();
                rs=st.executeQuery("select * from sub_pro where sp_id='"+session.getAttribute("spid").toString()+"' ");
                
                while(rs.next())
                {
                    name=rs.getString(2);
                    content=rs.getString(3);
                    rate=rs.getString(4);
                    company=rs.getString(5);
                    mfg=rs.getString(6);
                    exp=rs.getString(7);
                    stock=rs.getString(10);
                }
                
            }
            
            catch(Exception e)
            {
                out.println("Driver Ex is:-"+e);
            }
        %>

        
        
        
        <div id="body" style="min-height:100px; width: 600px">
             
                 <div class="content" style="width: 600px" >
                   
			
                     <center><h2>Sub_Product Detail</h2></center>
                     <form action="update_sub.jsp" method="post" >
                            
                            <label for="Sub_Pro_name"> <span>Product_Name*</span>
                                <input type="text" value="<%= name%>"  name="name" id="product">
			    </label>
                                
                         
                            <label for="Description"> <span>Content*</span>
                                <textarea name="con"  id="Content" style="width: 308px;height: 100px"><%= content%></textarea>
                            </label>
                         
                            <label for="rate"> <span>rate*</span>
                                <input type="number" name="rate" value="<%= rate%>"  id="rate">
			    </label>
                         
                            <label for="company" id="company"> <span>company*</span>
                                
                                <select name="company">
                                    <option value="Company" >Select Company</option>
                                    <option value="torrent">Torrent Pharma</option>
                                    <option value="elite">Elite Pharma</option>
                                    <option value="himalaya">Himalaya</option>
                                    <option value="shakar">Shakar Pharma</option>  
                                    <option value="dishman">Dishman Pharma</option>  
                                    <option value="cadila">Cadila Pharma</option>  
                                    <option value="apollo">Apollo Pharmacy</option>
                                </select>
			    </label>
                         
                            <label for="Mfg_date"> <span>Mfg_Date*</span>
                                <input type="date" name="mfg" value="<%= mfg%>" id="Mfg_date">
			    </label>
                         
                            
                            <label for="Exp_date"> <span>Exp_Date*</span>

                                <input type="date" name="exp" value="<%= exp%>" id="exp_date">
			    </label>
                         
                            <label for="stock"> <span>stock*</span>
                                <input type="number" name="stock"  id="stock">
			    </label>

                         <center> <input type="submit"  name="b1"  value="Update"  style="width: 90px; height: 30px"></center>
                </form>
                    <%
                            String action=null,ucompany=null,uname=null,ucontent=null,urate=null,ustock=null,umfg=null,uexp=null;
                            action=request.getParameter("b1");
                            
                            if(action!=null)
                            {
                                uname=request.getParameter("name");
                                ucontent=request.getParameter("con");
                                urate=request.getParameter("rate");
                                ucompany=request.getParameter("company");
                                umfg=request.getParameter("mfg");
                                uexp=request.getParameter("exp");
                                ustock=request.getParameter("stock");
                                
                                if(action.equalsIgnoreCase("update"))
                                {
                                    
                                   int i=st.executeUpdate("update sub_pro set name='"+uname+"',content='"+ucontent+"',rate='"+urate+"',company='"+ucompany+"',mfg_date='"+umfg+"',exp_date='"+uexp+"',stock='"+ustock+"' where sp_id='"+session.getAttribute("spid").toString()+"'");
                                    if(i>0)
                                    {
                                        
                                        response.sendRedirect("product.jsp");
                                        session.removeAttribute("spid");
                                        
                                    }
                                    else
                                    {   
                                        
                                        response.sendRedirect("product.jsp");
                                        session.removeAttribute("spid");
                                        
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
