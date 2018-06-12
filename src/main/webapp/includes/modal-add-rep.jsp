<%@page import="model.Curso"%>
<%@page import="model.Software"%>
<%@page import="model.Reserva"%>
<%@page import="model.Pessoa"%>
<%@page import="java.util.ArrayList"%>
<script src="${pageContext.request.contextPath}/js/laboratorio.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/reserva.new.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>

<!-- ========== JANELA MODAL ========== -->
<form action="${pageContext.request.contextPath}/AlmightyController" method="post">
    <div class="modal fade" id="modal-add-rep" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="modal-title soli-tit" id="solicitacao-modal-titulo">Adicionar representante</h5>
                </div>     
                <form action="${pageContext.request.contextPath}/AlmightyController" method="post" name="form-codigo">
                    <div class="modal-body">
                        <table id="lista-codigos" cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td>
                                    <div class="form-group">
                                        <label class="soli-tit">Fornecedor #</label>
                                    </div>
                                </td>
                                <td style="width:100%;">
                                    <div class="form-group">
                                        <input readonly style="width: 80%;" type="text" class="form-control pull-right" name="forn-id" id="forn-id" placeholder="ID" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-group">
                                        <label class="soli-tit">Nome</label>
                                    </div>
                                </td>
                                <td style="width:100%;">
                                    <div class="form-group">
                                        <input style="width: 80%;" type="text" class="form-control pull-right" name="rep-name" id="rep-name" placeholder="Nome" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-group">
                                        <label class="soli-tit">Telefone</label>
                                    </div>
                                </td>
                                <td style="width:100%;">
                                    <div class="form-group">
                                        <input style="width: 80%;" type="text" class="form-control pull-right" name="rep-tel" id="rep-tel" placeholder="Telefone" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-group">
                                        <label class="soli-tit">Email</label>
                                    </div>
                                </td>
                                <td style="width:100%;">
                                    <div class="form-group">
                                        <input style="width: 80%;" type="email" class="form-control pull-right" name="rep-email" id="rep-email" placeholder="Email" />
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>                        
                    <div id="modal-footer" class="modal-footer">
                        <button id="adiciona-codigo" name="acao" value="RepresentanteInsercao" type="submit" class="btn btn-success">Adicionar</button><br/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</form>

<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>