<%@include file="/includes/modal-soli.jsp"%> 
<%@include file="/includes/modal-perfil-self.jsp"%>
<%@include file="/includes/modal-perfil.jsp"%>
<%@include file="/includes/modal-incidente.jsp"%>
<%@include file="/includes/modal-licenca.jsp"%>
<%@include file="/includes/modal-codigo.jsp"%>
<footer class="main-footer">
    <strong>Copyright &copy; <% out.println(cal.get(Calendar.YEAR));%> All rights reserved. Versão <%=util.SiGLa.VERSION%></strong>
</footer>
<div class="control-sidebar-bg"></div>
<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
<script>
    $(function () {
        $("#equip-mac").keyup(function () {
            $("#equip-mac").val($(this).val().toUpperCase().split(".")[0]);
        });
    });
    $(".select2").select2();
</script>