<%@page import="model.Pessoa"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Equipamento"%>
<%@page import="java.util.ArrayList"%>
<script src="${pageContext.request.contextPath}/js/laboratorio.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/equipamento.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>

<!-- ========== JANELA MODAL DE INCIDENTE ========== -->
<form action="${pageContext.request.contextPath}/AlmightyController" method="post">
    <div class="modal fade" id="modalIncidente" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="modal-title equip-tit" id="solicitacao-modal-titulo" >Incidente</h5>
                </div>     
                <div class="modal-body">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label class="soli-tit">ID</label>
                                </div>
                            </td>
                            <td style="width:100%;">
                                <div class="form-group">
                                    <input style="width: 80%;" readonly type="text" class="form-control pull-right" name="incidente_id" id="incidente_id" placeholder="ID do Incidente" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label class="soli-tit">Data de Abertura</label>
                                </div>
                            </td>
                            <td style="width:100%;">
                                <div class="form-group">
                                    <input style="width: 80%;" readonly type="text" class="form-control pull-right" name="incidente_data" id="incidente_data" placeholder="Data de Abertura" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Computador Retirado</label>                                            
                                </div>
                            </td>
                            <td style="padding-left: 17.2%;">
                                <input style="width: 100%;" readonly type='text' class='form-control pull-right equip-mac' name="incidente_computador" id="incidente_computador" placeholder="Computador Retirado"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Aberto por</label>                                            
                                </div>
                            </td>
                            <td>
                                <input style="width: 80%;" value="<%=p.getNomeCompleto()%>" readonly type='text' class='form-control pull-right' placeholder="Nome de quem abriu o chamado" />
                            </td>
                            <td style="display:none;">
                                <input style="width: 80%;" value="<%=p.getUsername()%>" readonly type='text' class='form-control pull-right' name="incidente_abridor" id="incidente_abridor" placeholder="Nome de quem abriu o chamado" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Descrição</label>                                            
                                </div>
                            </td>
                            <td>
                                <input style="width: 80%;" readonly type='textarea' class='form-control pull-right' name="incidente_descricao" id="incidente_descricao" placeholder="Motivo da retirada" />
                            </td>
                        </tr>Ï
                    </table>
                </div>                        
                <div id="modal-footer" class="modal-footer"></div>
            </div>
        </div>
    </div>
</form>

<!-- ========== JANELA MODAL DE ABERTURA DE INCIDENTE ========== -->
<form action="${pageContext.request.contextPath}/AlmightyController" method="post">
    <div class="modal fade" id="modalAberturaIncidente" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="modal-title equip-tit" id="solicitacao-modal-titulo" >Abertura de Incidente</h5>
                </div>     
                <div class="modal-body">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label class="soli-tit">Data de Abertura</label>
                                </div>
                            </td>
                            <td style="width:100%;">
                                <div class="form-group">
                                    <input style="width: 80%;" value="<%=timeStamp%>" readonly type="text" class="form-control pull-right" name="incidente_data_abertura" id="incidente_data_abertura" placeholder="Data de Abertura" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Computador Retirado</label>                                            
                                </div>
                            </td>
                            <td style="padding-left: 17.2%;">
                                <input style="width: 100%;" readonly type='text' class='form-control pull-right equip-mac' name="incidente_computador_abertura" id="incidente_computador_abertura" placeholder="Computador Retirado"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Aberto por</label>                                            
                                </div>
                            </td>
                            <td>
                                <input style="width: 80%;" value="<%=p.getNomeCompleto()%>" readonly type='text' class='form-control pull-right' placeholder="Nome de quem abriu o chamado" />
                            </td>
                            <td style="display:none;">
                                <input style="width: 80%;" value="<%=p.getUsername()%>" readonly type='text' class='form-control pull-right' name="incidente_abridor_abertura" id="incidente_abridor_abertura" placeholder="Nome de quem abriu o chamado" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Descrição</label>                                            
                                </div>
                            </td>
                            <td>
                                <input style="width: 80%;" type='textarea' class='form-control pull-right' name="incidente_descricao_abertura" id="incidente_descricao_abertura" placeholder="Motivo da retirada" />
                            </td>
                        </tr>
                    </table>
                </div>                        
                <div id="modal-footer" class="modal-footer"></div>
            </div>
        </div>
    </div>
</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.13.4/jquery.mask.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
<script>
    $(function () {
        $("#equip-mac").keyup(function () {
            $("#equip-mac").val($(this).val().toUpperCase().split(".")[0]);
        });
    });
    $(".select2").select2();
</script>