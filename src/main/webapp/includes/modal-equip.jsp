<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Equipamento"%>
<%@page import="java.util.ArrayList"%>
<script src="${pageContext.request.contextPath}/js/laboratorio.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/equipamento.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>

<%
    String timeDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
%>

<!-- ========== JANELA MODAL ========== -->
<form action="${pageContext.request.contextPath}/AlmightyController" method="post">
    <div class="modal fade" id="myEquip" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="modal-title equip-tit" id="solicitacao-modal-titulo" >Equipamento</h5>
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
                                    <input style="width: 80%;" readonly type="text" class="form-control pull-right" name="equip-id" id="equip-id" placeholder="ID do Computador" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label class="soli-tit">NetBIOS</label>
                                </div>
                            </td>
                            <td style="width:100%;">
                                <div class="form-group">
                                    <input style="width: 80%;" readonly type="text" class="form-control pull-right" name="equip-netbios" id="equip-netbios" placeholder="Nome NetBIOS" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Endereço MAC</label>                                            
                                </div>
                            </td>
                            <td style="padding-left: 17.2%;">
                                <input style="width: 100%;" type='text' class='form-control pull-right equip-mac' name="equip-mac" id="equip-mac" placeholder="Endereço físico" data-mask="AA:AA:AA:AA:AA:AA" data-mask-selectonfocus="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Endereço IP</label>                                            
                                </div>
                            </td>
                            <td>
                                <input style="width: 80%;" readonly type='text' class='form-control pull-right' name="equip-ip" id="equip-ip" placeholder="Endereço lógico" />
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-top: 5%;">
                                <div class='form-group'>
                                    <label>Status</label>
                                </div>
                            </td>
                            <td>
                                <input type="text" style="width: 80%;" disabled class='form-control pull-right' name="equip-status" id="equip-status" placeholder="Status"></textarea>
                            </td>
                        </tr>
                        <tr  id="data-retirado-div">
                            <td style="padding-top: 5%;">
                                <div class='form-group'>
                                    <label>Data da Retirada</label>
                                </div>
                            </td>
                            <td style="padding-left: 17.2%;">
                                <input type="text" readonly id="data-retirado-campo" name="data-retirado-campo" class="form-control"></textarea>
                            </td>
                        </tr>
                        <tr  id="motivo-retirado">
                            <td style="padding-top: 5%;">
                                <div class='form-group'>
                                    <label>Motivo da Retirada</label>
                                </div>
                            </td>
                            <td style="padding-left: 17.2%;">
                                <textarea readonly id="motivo-retirado-campo" name="motivo-retirado-campo" class="form-control"></textarea>
                            </td>
                        </tr>
                    </table>
                </div>                        
                <div id="modal-footer" class="modal-footer"></div>
            </div>
        </div>
    </div>
</form>

<!-- ========== JANELA MODALDE RETIRADA  ========== -->
<form action="${pageContext.request.contextPath}/AlmightyController" method="post">
    <div class="modal fade" id="modalRetirar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="modal-title equip-tit" id="solicitacao-modal-titulo" >Retirar Equipamento</h5>
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
                                    <input style="width: 80%;" readonly type="text" class="form-control pull-right" name="equip-id-retirar" id="equip-id-retirar" placeholder="ID" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label class="soli-tit">NetBIOS</label>
                                </div>
                            </td>
                            <td style="width:100%;">
                                <div class="form-group">
                                    <input style="width: 80%;" readonly type="text" class="form-control pull-right" name="equip-netbios-retirar" id="equip-netbios-retirar" placeholder="Nome NetBIOS" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Data da Retirada</label>                                            
                                </div>
                            </td>
                            <td style="padding-left: 17.2%;">
                                <input style="width: 100%;" autocomplete="off" readonly type='text' class='form-control pull-right' name="equip-data-retirada" id="equip-data-retirada" placeholder="Endereço físico" value="<%=timeDate%>"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Motivo</label>                                            
                                </div>
                            </td>
                            <td id="motivo-retirar" style="padding-left: 17.2%;display:none;">
                                <textarea id="motivo" name="motivo" class="form-control"></textarea>
                            </td>
                        </tr>
                    </table>
                </div>                        
                <div id="modal-footer" class="modal-footer">
                    <button name="acao" value="EquipamentoRetirada" id="btnModalRetirar" type="submit" class="btn btn-danger">Retirar</button>
                </div>
            </div>
        </div>
    </div>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.13.4/jquery.mask.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
