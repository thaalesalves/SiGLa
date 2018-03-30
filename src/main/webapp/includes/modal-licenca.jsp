<%@page import="model.Curso"%>
<%@page import="model.Software"%>
<%@page import="model.Reserva"%>
<%@page import="model.Pessoa"%>
<%@page import="java.util.ArrayList"%>
<script src="${pageContext.request.contextPath}/js/laboratorio.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/reserva.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>

<!-- ========== JANELA MODAL ========== -->
<form action="${pageContext.request.contextPath}/AlmightyController" method="post">
    <div class="modal fade" id="modal-licenca" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="modal-title soli-tit" id="solicitacao-modal-titulo">Licença</h5>
                </div>     
                <div class="modal-body">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label class="soli-tit">#</label>
                                </div>
                            </td>
                            <td style="width:100%;">
                                <div class="form-group">
                                    <input readonly style="width: 80%;" type="text" class="form-control pull-right" name="licenca-id" id="licenca-id" placeholder="ID" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label class="soli-tit">Aquisição</label>
                                </div>
                            </td>
                            <td style="width:100%;">
                                <div class="form-group">
                                    <input readonly style="width: 80%;" type="text" class="form-control pull-right" name="data-aquisicao" id="data-aquisicao" placeholder="Data de aquisição" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label class="soli-tit">Vencimento</label>
                                </div>
                            </td>
                            <td style="width:100%;">
                                <div class="form-group">
                                    <input readonly style="width: 80%;" type="text" class="form-control pull-right" name="data-vencimento" id="data-vencimento" placeholder="Data de vencimento" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label class="soli-tit">Software</label>
                                </div>
                            </td>
                            <td style="width:100%;">
                                <div class="form-group">
                                    <input readonly style="width: 80%;" type="text" class="form-control pull-right" name="licenca-software" id="licenca-software" placeholder="Software" />
                                </div>
                            </td>
                        </tr>
                    </table>
                    <table id="lista-codigos" cellpadding="0" cellspacing="0" border="0"></table>
                </div>                        
                <div id="modal-footer" class="modal-footer">
                    <button style="display:none;" id="btn-desativar" type="button" class="btn btn-success" onclick="removerLicenca()">Desativar</button>
                    <button style="display:none;" id="btn-ativar" type="button" class="btn btn-success" onclick="removerLicenca()">Ativar</button>
                    <button id="btnModalExcluir2" type="button" class="btn btn-danger" onclick="removerLicenca()">Excluir</button>
                </div>
            </div>
        </div>
    </div>
</form>

<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>