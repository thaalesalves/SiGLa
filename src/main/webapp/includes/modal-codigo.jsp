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
    <div class="modal fade" id="modal-codigo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="modal-title soli-tit" id="solicitacao-modal-titulo">Adicionar c�digo</h5>
                </div>     
                <form action="${pageContext.request.contextPath}/AlmightyController" method="post" name="form-codigo">
                    <div class="modal-body">
                        <table id="lista-codigos" cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td>
                                    <div class="form-group">
                                        <label class="soli-tit">Licen�a #</label>
                                    </div>
                                </td>
                                <td style="width:100%;">
                                    <div class="form-group">
                                        <input readonly style="width: 80%;" type="text" class="form-control pull-right" name="codigo-licenca" id="codigo-licenca" placeholder="ID" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-group">
                                        <label class="soli-tit">Tipo</label>
                                    </div>
                                </td>
                                <td style="width:100%;">
                                    <div class="form-group">
                                        <input style="width: 80%;" type="text" class="form-control pull-right" name="tipo-chave" id="tipo-chave" placeholder="Tipo de chave" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-group">
                                        <label class="soli-tit">C�digo</label>
                                    </div>
                                </td>
                                <td style="width:100%;">
                                    <div class="form-group">
                                        <input style="width: 80%;" type="text" class="form-control pull-right" name="chave" id="chave" placeholder="C�digo" />
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>                        
                    <div id="modal-footer" class="modal-footer">
                        <button id="adiciona-codigo" name="acao" value="LicencaCodigoAdicao" type="submit" class="btn btn-success">Adicionar</button><br/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</form>

<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>