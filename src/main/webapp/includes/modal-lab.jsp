<%@page import="model.Pessoa"%>
<%@page import="java.util.ArrayList"%>
<script src="${pageContext.request.contextPath}/js/laboratorio.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>

<!-- ========== JANELA MODAL ========== -->
<form action="${pageContext.request.contextPath}/AlmightyController" method="post">
    <div class="modal fade" id="modalLab" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="modal-title soli-tit" id="solicitacao-modal-titulo">Laboratório</h5>
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
                                    <input style="width: 80%;" readonly type="text" class="form-control pull-right" name="modal_id_lab" id="modal_id_lab" placeholder="ID do Laboratorio" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Laboratório #</label>                                            
                                </div>
                            </td>
                            <td id="td-prof-txt" style="padding-left: 17.2%;">
                                <input style="width: 100%;" readonly type='text' class='form-control pull-right' name="modal_num_lab" id="modal_num_lab" placeholder="Número do Laboratório" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Capacidade de Pessoas</label>                                            
                                </div>
                            </td>
                            <td>
                                <input style="width: 80%;" required type='number' class='form-control pull-right' name="modal_cap_lab" id="modal_cap_lab" placeholder="Capacidade de Alunos" />
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-top: 5%;">
                                <div class='form-group'>
                                    <label># de Computadores</label>
                                </div>
                            </td>
                            <td>
                                <input type="number" required style="width: 80%;" class='form-control pull-right' name="modal_comps_lab" id="modal_comps_lab" placeholder="Quantidade de Computadores"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Softwares</label>
                                </div>
                            </td>
                            <td style="padding-left: 17.2%;" id="td_res_mod">
                                <select multiple required style="width: 100%;" class='form-control pull-right select2' name="modal_sw_lab" id="modal_sw_lab" data-placeholder="Softwares"></select>
                            </td>
                        </tr>
                    </table>
                </div>                        
                <div id="modal-footer" class="modal-footer">
                    <button name="acao" value="LaboratorioAtualizacao" id="btnModalAtualizar" type="submit" class="btn btn-success">Atualizar</button>
                </div>
            </div>
        </div>
    </div>
</form>

<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
<script>$(".select2").select2();</script>