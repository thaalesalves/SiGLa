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
                                <div class="box box-info">
                                    <div class="box-header">
                                        <i class="fa fa-envelope"></i>
                                        <h3 class="box-title" id="modal-email-titulo"></h3>
                                    </div>
                                    <form action="${pageContext.request.contextPath}/AlmightyController" method="post">
                                        <div class="box-body">
                                            <div class="form-group" style="display:none;">
                                                <input autocomplete="off" required type="text" class="form-control" name="modal-email-pag" id="modal-email-pag" placeholder="URL">
                                            </div>
                                            <div class="form-group">
                                                <input readonly autocomplete="off" required type="email" class="form-control" name="modal-email-dest" id="modal-email-dest" placeholder="Destinatário">
                                            </div>
                                            <div class="form-group">
                                                <input autocomplete="off" required type="text" class="form-control" name="modal-email-ass" placeholder="Assunto">
                                            </div>
                                            <div>
                                                <textarea required name="modal-email-msg" class="textarea" placeholder="Mensagem" style="width: 100%; height: 125px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
                                            </div>
                                        </div>
                                        <div class="box-footer clearfix">
                                            <button value="EnviarEmailModal" name="acao" type="submit" class="pull-right btn btn-default" id="sendEmail">Enviar
                                                <i class="fa fa-arrow-circle-right"></i></button>
                                        </div>
                                    </form>
                                </div>
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