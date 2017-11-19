<%@page import="model.Curso"%>
<%@page import="model.Software"%>
<%@page import="model.Reserva"%>
<%@page import="model.Pessoa"%>
<%@page import="java.util.ArrayList"%>
<script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/laboratorio.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/reserva.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
<!-- ========== JANELA MODAL ========== -->
<form action="${pageContext.request.contextPath}/AlmightyController" method="post">
    <div class="modal fade" id="modalPerfil" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>     
                <div class="modal-body">
                    <div class="box-body box-profile">
                        <img class="profile-user-img img-responsive img-circle" src="<%=picPathBlack%>" alt="User profile picture"><br/>
                        <h3 class="profile-username text-center"  id="modal-perfil-nome-completo"></h3>
                        <p class="text-muted text-center"  id="modal-perfil-cargo-depto"></p>
                        <ul class="list-group list-group-unbordered">
                            <li class="list-group-item">
                                <b>E-mail</b> <a class="pull-right" id="modal-perfil-email"></a>
                            </li>
                            <li class="list-group-item">
                                <b>Cargo</b> <a class="pull-right" id="modal-perfil-cargo"></a>
                            </li>
                            <li class="list-group-item">
                                <b>Departamento</b> <a class="pull-right" id="modal-perfil-depto"></a>
                            </li>
                            <li class="list-group-item">
                                <b>Empresa</b> <a class="pull-right" id="modal-perfil-empresa"></a>
                            </li>
                            <li class="list-group-item">
                                <%@include file="/includes/modal-email-form.jsp"%>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/app.min.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/demo.js"></script>