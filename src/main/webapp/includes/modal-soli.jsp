<script src="${pageContext.request.contextPath}/js/laboratorio.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/reserva.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>

<!-- ========== JANELA MODAL ========== -->
<form action="${pageContext.request.contextPath}/AlmightyController" method="post">
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="modal-title" id="solicitacao-modal-titulo">Solicitação</h5>                                                        
                </div>     
                <div class="modal-body">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label>Solicitação #</label>
                                </div>
                            </td>
                            <td style="width:100%;">
                                <div class="form-group">
                                    <input style="width: 80%;" disabled type="text" class="form-control pull-right" name="modalIdSolicitacao" id="modalIdSolicitacao" placeholder="Número da Solicitação" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Professor</label>                                            
                                </div>
                            </td>
                            <td>
                                <input style="width: 80%;" disabled type='text' class='form-control pull-right' name="modalProfessor" id="modalProfessor" placeholder="Nome do Professor" /
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Turma</label>                                            
                                </div>
                            </td>
                            <td>
                                <input style="width: 80%;" disabled type='text' class='form-control pull-right' name="modalCurso" id="modalCurso" placeholder="Nome do Curso" />
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-top: 5%;">
                                <div class='form-group'>
                                    <label>Software</label>
                                </div>
                            </td>
                            <td>
                                <textarea style="width: 80%;" disabled class='form-control pull-right' name="modalSoftware" id="modalSoftware" placeholder="Nome do Software"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Módulo</label>
                                </div>
                            </td>
                            <td>
                                <textarea style="width: 80%;" disabled class='form-control pull-right' name="modalModulo" id="modalModulo" placeholder="Módulo"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Dia da Semana</label>
                                </div>
                            </td>
                            <td>
                                <input style="width: 80%;" disabled type='text' class='form-control pull-right' id="modalDiaSemana" name="modalDiaSemana" id="modalDiaSemana" placeholder="Dia da Semana" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Qtd. de Alunos</label>
                                </div>   
                            </td>
                            <td>
                                <input style="width: 80%;" disabled type='text' class='form-control pull-right' nome="modalQtdAlunos" id="modalQtdAlunos" placeholder="Quantidade de Alunos" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Observações</label>
                                </div>   
                            </td>
                            <td>
                                <input style="width: 80%;" disabled type='textarea' class='form-control pull-right' nome="modalObservacao" id="modalObservacao" placeholder="Observações" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Laboratório</label>
                                </div> 
                            </td>
                            <td>
                                <select style="width: 80%;" class='form-control pull-right' name="modalLabCombo" id="modalLabCombo">
                                    <option default>Selecionar</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>                        
                <div id="modal-footer" class="modal-footer">
                    <!--button data-toggle="modal" data-target="#labModal" type="button" class="btn btn-success" onclick="modalLabs()">Aprovar</button-->
                    <button id="btnModalAprovar" type="button" class="btn btn-success" onclick="aprovarReserva()">Aprovar</button>
                    <button id="btnModalReprovar" type="button" class="btn btn-danger" onclick="reprovarReserva()">Reprovar</button><br/>
                </div>
            </div>
        </div>
    </div>
</form>
<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
<script>$(".select2").select2();</script>