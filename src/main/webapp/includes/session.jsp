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
%>