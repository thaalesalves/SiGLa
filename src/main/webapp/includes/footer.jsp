<%
    String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
%>

<%@include file="/includes/modal-soli.jsp"%> 
<%@include file="/includes/modal-perfil-self.jsp"%>
<%@include file="/includes/modal-perfil.jsp"%>
<%@include file="/includes/modal-incidente.jsp"%>
<footer class="main-footer">
    <strong>Copyright &copy; <% out.println(cal.get(Calendar.YEAR));%> All rights reserved. Versão <%=util.SiGLa.VERSION%></strong>
</footer>
<div class="control-sidebar-bg"></div>