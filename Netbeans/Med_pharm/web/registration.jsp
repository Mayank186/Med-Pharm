<%-- 
    Document   : registration
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
       <jsp:include page="include/header.jsp"></jsp:include>
        <jsp:include page="include/css.jsp"></jsp:include>
        <div id="body" style="min-height:300px; width: 600px">
             
                 <div class="content" style="width: 600px" >
                   
			
                     <center><h2>Registration</h2></center>
                     <form action="registration.jsp" method="post">
				<label for="firstName"> <span>Name*</span>
                                    <input type="text" name="name"  id="firstName">
				</label>
                                <label for="Address"> <span>Address*</span>
                                    <textarea name="addr" id="Address"  style="width: 308px;height: 100px"></textarea>
                                </label>
				<label for="email"> <span>email*</span>
                                    <input type="text" name="email"  id="email">
				</label>
                         
                         
				<label for="password"> <span>password*</span>
                                    <input type="password" name="pass"  id="passsword">
				</label>
                         
                                <label for="phoneNumber"> <span>Phone Number*</span>
                                    <input type="number" name="number"  id="phoneNumber">
				</label>
                         
                                <label for="Description"> <span>Description*</span>
                                    <textarea name="des" id="Description"  style="width: 308px;height: 100px"></textarea>
				</label>
				
				
                         <center><input type="submit" name="b1" value="Submit" style="width: 90px; height: 30px"></center>
			</form>
                 <%
                     Connection con=null;
                     Statement st=null;
                     String action=null,name=null,address=null,email=null,ds=null,pass=null,phone=null;
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
                            address=request.getParameter("addr");
                            email=request.getParameter("email");
                            pass=request.getParameter("pass");
                            phone=request.getParameter("number");
                            ds=request.getParameter("des");
                            try
                            {
                                int i=st.executeUpdate("insert into agancy_detail(name,address,email,password,number,des)values('"+name+"','"+address+"','"+email+"','"+pass+"','"+phone+"','"+ds+"')");
                                if(i>0)
                                    response.sendRedirect("login.jsp");
                                else
                                    out.println("Not Submit");
                            }
                            catch(Exception e)
                            {
                                out.println("Insert Ex is:-"+e);
                            }
                        }
                 %>
                </div>
        </div>
        <br/>
        <br/>
       <jsp:include page="include/footer.jsp"></jsp:include>
    </body>
</html>
