<%@page import="util.SiGLa"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.Properties"%>
<%
    Calendar cal = Calendar.getInstance();

    Pessoa p = (Pessoa) session.getAttribute("pessoa");

    String msg = "null";
    String status = null;
    if ((msg = (String) session.getAttribute("msg")) != null) {
        msg = (String) session.getAttribute("msg");
        status = (String) session.getAttribute("status");
        session.removeAttribute("msg");
        session.removeAttribute("status");
    }

    String picPath = request.getContextPath() + "/img/user/thumbnail.png";
    if (new File(request.getContextPath() + "/img/user/" + p.getUsername() + "_pic.jpg").exists()) {
        picPath = request.getContextPath() + "/img/user/" + p.getUsername() + "_pic.jpg";
    }
%>