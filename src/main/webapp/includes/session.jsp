<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.Properties"%>
<%
    Calendar cal = Calendar.getInstance();

    Pessoa p;
    if ((p = (Pessoa) session.getAttribute("pessoa")) == null) {
        response.sendRedirect(request.getContextPath() + "/error/401");
    }

    String msg = "null";
    String status = null;
    if ((msg = (String) session.getAttribute("msg")) != null) {
        msg = (String) session.getAttribute("msg");
        status = (String) session.getAttribute("status");
        session.removeAttribute("msg");
        session.removeAttribute("status");
    }

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    Properties props = new Properties();
    InputStream resourceStream = loader.getResourceAsStream("app.properties");
    props.load(resourceStream);
    String siglaVersion = (String) props.get("sigla.version");
    String siglaDir = (String) props.get("sigla.home");
    siglaDir = siglaDir.replace("\\", "/");
    String picPath = request.getContextPath() + "/img/user/thumbnail.png";

    if (new File(siglaDir + "/target/sigla-" + siglaVersion + "/img/user/" + p.getUsername() + "_pic.jpg").exists()) {
        picPath = request.getContextPath() + "/img/user/" + p.getUsername() + "_pic.jpg";
    }
%>