<%@page import="java.util.Calendar"%>
<%@page import="model.Pessoa"%>
<%@page import="util.SiGLa"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.Properties"%>
<%
    Calendar cal = Calendar.getInstance();

    Pessoa p = (Pessoa) session.getAttribute("pessoa");

    String msg = "null";
    String status = null;

    if ((msg = (String) session.getAttribute("msg")) != null || (msg = (String) session.getAttribute("mensagem")) != null) {
        if ((status = (String) session.getAttribute("status")) != null || (status = (String) session.getAttribute("estado")) != null);
        session.removeAttribute("msg");
        session.removeAttribute("status");
        session.removeAttribute("mensagem");
        session.removeAttribute("estado");
    }

    String picPath;
    String picPathBlack;
    if (new File(request.getContextPath() + "/img/user/" + p.getUsername() + "_pic.jpg").exists()) {
        picPath = request.getContextPath() + "/img/user/" + p.getUsername() + "_pic.jpg";
        picPathBlack = request.getContextPath() + "/img/user/" + p.getUsername() + "_pic.jpg";
    } else {
        picPath = request.getContextPath() + "/img/user/thumbnail.png";
        picPathBlack = request.getContextPath() + "/img/user/thumbnail1.png";
    }
%>