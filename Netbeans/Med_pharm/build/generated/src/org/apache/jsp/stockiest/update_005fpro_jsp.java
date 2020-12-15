package org.apache.jsp.stockiest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class update_005fpro_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../stockiest/include/header.jsp", out, false);
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../stockiest/include/css.jsp", out, false);
      out.write("\n");
      out.write("        \n");
      out.write("        ");

            String pid=null;
            if(session.getAttribute("a_id")==null)
            {
                response.sendRedirect("../login.jsp");
            }
            pid=request.getParameter("id");
           
        
      out.write("\n");
      out.write("\n");
      out.write("        \n");
      out.write("        <div id=\"body\" style=\"min-height:100px; width: 600px\">\n");
      out.write("             \n");
      out.write("                 <div class=\"content\" style=\"width: 600px\" >\n");
      out.write("                   \n");
      out.write("\t\t\t\n");
      out.write("                     <center><h2>Sub_Product Detail</h2></center>\n");
      out.write("                     <form action=\"sub_product.jsp\" method=\"post\" >\n");
      out.write("                            \n");
      out.write("                            <label for=\"Sub_Pro_name\"> <span>Product_Name*</span>\n");
      out.write("                                <input type=\"text\"  name=\"name\" id=\"product\">\n");
      out.write("\t\t\t    </label>\n");
      out.write("                                \n");
      out.write("                         \n");
      out.write("                            <label for=\"Description\"> <span>Content*</span>\n");
      out.write("                                <textarea name=\"con\"  id=\"Content\" style=\"width: 308px;height: 100px\"></textarea>\n");
      out.write("                            </label>\n");
      out.write("                         \n");
      out.write("                            <label for=\"rate\"> <span>rate*</span>\n");
      out.write("                                <input type=\"number\" name=\"rate\"  id=\"rate\">\n");
      out.write("\t\t\t    </label>\n");
      out.write("                         \n");
      out.write("                            <label for=\"company\" > <span>company*</span>\n");
      out.write("                                <select name=\"company\" >\n");
      out.write("                                    <option value=\"Company\" ></option>\n");
      out.write("                                    <option value=\"Company-1\">Company-1</option>\n");
      out.write("                                    <option value=\"Company-2\">Company-2</option>\n");
      out.write("                                    <option value=\"Company-3\">Company-3</option>\n");
      out.write("                                    <option value=\"Company-4\">Company-4</option>\n");
      out.write("                                    <option value=\"Company-5\">Company-5</option>\n");
      out.write("                                </select>\n");
      out.write("\t\t\t    </label>\n");
      out.write("                         \n");
      out.write("                            <label for=\"Mfg_date\"> <span>Mfg_Date*</span>\n");
      out.write("                                <input type=\"date\" name=\"Mfg_date\" id=\"Mfg_date\">\n");
      out.write("\t\t\t    </label>\n");
      out.write("                         \n");
      out.write("                            \n");
      out.write("                            <label for=\"Exp_date\"> <span>Exp_Date*</span>\n");
      out.write("                                <input type=\"date\" name=\"Exp_date\" id=\"Mfg_date\">\n");
      out.write("\t\t\t    </label>\n");
      out.write("                         \n");
      out.write("                         \n");
      out.write("\n");
      out.write("                         <center> <input type=\"submit\"  name=\"b1\"  value=\"Save\"  style=\"width: 90px; height: 30px\"></center>\n");
      out.write("                </form>\n");
      out.write("                ");

                     Connection con=null;
                     Statement st=null;
                     String action=null,name=null,des=null,rate=null,company=null,mfg=null,exp=null;
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
                            try
                            {
                                int i=st.executeUpdate("insert into sub_pro(name,content,rate,stock,mfg_date,exp_date)values('"+name+"','"+des+"','"+rate+"','"+company+"','"+mfg+"','"+exp+"')");
                                if(i>0)
                                    response.sendRedirect("product.jsp");
                                else
                                    out.println("Not Submit");
                            }
                            catch(Exception e)
                            {
                                out.println("Insert Ex is:-"+e);
                            }
                        }
                 
      out.write("\n");
      out.write("                     <br/>\n");
      out.write("                </div>\n");
      out.write("        </div>\n");
      out.write("        <br/>\n");
      out.write("        <br/>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../stockiest/include/footer.jsp", out, false);
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
