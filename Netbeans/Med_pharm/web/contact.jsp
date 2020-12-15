<%-- 
    Document   : contact
    Created on : Feb 8, 2017, 11:00:24 AM
    Author     : MANAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <jsp:include page="include/header.jsp"></jsp:include>
            <jsp:include page="include/css.jsp"></jsp:include>
            <div id="body"style="min-height:150px">
                <div class="content">
			<img src="images/telephone.jpg" alt="">
			<h2>send us a message</h2>
			<form action="index.html">
				<label for="firstName"> <span>first name*</span>
					<input type="text" name="first" id="firstName">
				</label>
				<label for="lastName"> <span>last name*</span>
					<input type="text" name="last" id="lastName">
				</label>
				<label for="email"> <span>email*</span>
					<input type="text" name="email" id="email">
				</label>
				<label for="phoneNumber"> <span>Phone Number</span>
					<input type="text" name="phone" id="phoneNumber">
				</label>
				<label for="subject"> <span>subject*</span>
					<input type="text" name="subject" id="subject">
				</label>
				<label for="message"> <span>message</span>
					<textarea name="" id="message" cols="30" rows="10"></textarea>
				</label>
				<center> 
                             <input type="submit"  name="b1"  value="Send"  style="width: 90px; height: 30px">
                         </center>
			</form>
		</div>
		<div class="sidebar">
			<h3>contact</h3>
			<ul>
				<li>
					<span class="address">address</span>
					<ul>
						<li>
							008, Med_Pharm Corporation
						</li>
                                                <li>
							Pharmacy Plaza
						</li>
						<li>
							Express HighWay Road
						</li>
						<li>
							Ahmedabad, GJ 27
						</li>
					</ul>
				</li>
				<li>
					<span class="phone">Mobile</span>
					<ul>
						<li>
							8866101873
						</li>
					</ul>
				</li>
				<li>
					<span class="email">email</span>
					<ul>
						<li>
							dsp4456@gmail.com
						</li>
					</ul>
				</li>
				<li>
					<span class="twitter">twitter</span>
					<ul>
						<li>
							@dhruv_devil8
						</li>
					</ul>
				</li>
				<li>
					<span class="facebook">facebook</span>
					<ul>
						<li>
							<a href="https://www.facebook.com/">www.facebook/med_pharm</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
            </div>
            <br/>
        <br/>
        
            <jsp:include page="include/footer.jsp"></jsp:include>

    </body>
</html>
