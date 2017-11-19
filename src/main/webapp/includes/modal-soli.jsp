<%@page import="model.Curso"%>
<%@page import="model.Software"%>
<%@page import="model.Reserva"%>
<%@page import="model.Pessoa"%>
<%@page import="java.util.ArrayList"%>
<script src="${pageContext.request.contextPath}/js/laboratorio.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/reserva.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
<%--    Reserva r;
    ArrayList<Software> asw;
    ArrayList<Curso> ac;

    r = (Reserva) request.getAttribute("reserva");

    if ((r = (Reserva) session.getAttribute("reserva")) == null) {
        request.getRequestDispatcher(request.getContextPath() + "/InfoController?acao=nova-reserva").forward(request, response);
    }

    asw = r.getSoftwares();
    ac = r.getCursos();
--%>
<!-- ========== JANELA MODAL ========== -->
<form action="${pageContext.request.contextPath}/AlmightyController" method="post">
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="modal-title soli-tit" id="solicitacao-modal-titulo"  style="display:none;">Solicita��o</h5>
                    <h5 class="modal-title res-tit" id="solicitacao-modal-titulo" style="display:none;">Reserva</h5>
                </div>     
                <div class="modal-body">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label style="display:none;" class="soli-tit">Solicita��o #</label>
                                    <label style="display:none;" class="res-tit">Reserva #</label>
                                </div>
                            </td>
                            <td style="width:100%;">
                                <div class="form-group">
                                    <input style="width: 80%;" disabled type="text" class="form-control pull-right" name="modalIdSolicitacao" id="modalIdSolicitacao" placeholder="N�mero da Solicita��o" />
                                    <input style="width: 80%;display:none;" type="text" class="form-control pull-right" name="reserva-id" id="reserva-id" placeholder="N�mero da Solicita��o" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Professor</label>                                            
                                </div>
                            </td>
                            <td id="td-prof-combo" style="padding-left: 17.2%;display:none;">
                                <select class="select2 form-control" data-placeholder="Selecione um professor" style="width: 100%;" id="modalProfessoresCombo" name="modalProfessoresCombo">                                   
                                    <% for (Pessoa pessoa : (ArrayList<Pessoa>) session.getAttribute("todos-usuarios")) {%>
                                    <option value="<%=pessoa.getUsername().trim()%>"><% out.println(pessoa.getNomeCompleto() + " (" + pessoa.getEmail() + ")"); %></option>
                                    <% }%>
                                </select>
                            </td>
                            <td id="td-prof-txt" style="padding-left: 17.2%;display:none;">
                                <input style="width: 100%;" disabled type='text' class='form-control pull-right' name="modalProfessor" id="modalProfessor" placeholder="Nome do Professor" />
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
                            <td style="padding-left: 17.2%;display:none;" id="td_soli_sw">
                                <textarea style="width: 100%;" disabled class='form-control pull-right' name="modalSoftware" id="modalSoftware" placeholder="Nome do Software"></textarea>
                            </td>
                            <td style="padding-left: 17.2%;display:none;" id="td_res_sw">
                                <select id="modalSoftwares" name="modalSoftwares" class="select2 form-control" data-placeholder="Selecione um software" style="width: 100%;" multiple required></select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>M�dulo</label>
                                </div>
                            </td>
                            <td style="padding-left: 17.2%;display:none;" id="td_res_mod">
                                <select multiple style="width: 100%;" class='form-control pull-right select2' name="modalModuloCombo" id="modalModuloCombo">
                                    <option value="1">1� M�dulo (8h �s 9h30)</option>
                                    <option value="2">2� M�dulo (9h40 �s 11h10)</option>
                                    <option value="3">3� M�dulo (11h10 �s 12h40)</option>
                                    <option value="4">4� M�dulo (13h �s 14h30)</option>
                                    <option value="5">5� M�dulo (14h30 �s 17h30)</option>
                                    <option value="6">6� M�dulo (17h30 �s 19h)</option>
                                    <option value="7">7� M�dulo (19h �s 20h30)</option>
                                    <option value="8">8� M�dulo (20h40 �s 22h)</option>
                                </select>
                            </td>
                            <td style="padding-left: 17.2%;display:none;" id="td_soli_mod">
                                <textarea style="width: 100%;" disabled class='form-control pull-right' name="modalModulo" id="modalModulo" placeholder="M�dulo"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div>
                                    <label>Dia da Semana</label>
                                </div>
                            </td>
                            <td style="padding-left: 17.2%;display:none;" id="td_soli_dia">
                                <input style="width: 100%;" disabled type='text' class='form-control pull-right' id="modalDiaSemana" name="modalDiaSemana" id="modalDiaSemana" placeholder="Dia da Semana" />
                            </td>
                            <td style="padding-left: 17.2%;display:none;" id="td_res_dia">
                                <select style="width: 100%;" class='form-control pull-right select2' name="modalDiaCombo" id="modalDiaCombo"></select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div>
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
                                    <label>Observa��es</label>
                                </div>   
                            </td>
                            <td>
                                <input style="width: 80%;" disabled type='textarea' class='form-control pull-right' nome="modalObservacao" id="modalObservacao" placeholder="Observa��es" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class='form-group'>
                                    <label>Laborat�rio</label>
                                </div> 
                            </td>
                            <td style="padding-left: 17.2%;">
                                <select style="width: 100%;" class='select2 form-control pull-right' name="modalLabCombo" id="modalLabCombo">
                                    <option default>Selecionar</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>                        
                <div id="modal-footer" class="modal-footer">
                    <!--button data-toggle="modal" data-target="#labModal" type="button" class="btn btn-success" onclick="modalLabs()">Aprovar</button-->
                    <button id="btnModalAprovar" style="display:none;" type="button" class="btn btn-success" onclick="aprovarReserva()">Aprovar</button>
                    <button name="acao" value="ReservaAtualizacao" id="btnModalAtualizar" style="display:none;" type="submit" class="btn btn-success">Atualizar</button>
                    <button id="btnModalReprovar" style="display:none;" type="button" class="btn btn-danger" onclick="reprovarReserva()">Reprovar</button>
                    <button id="btnModalExcluir" style="display:none;" type="button" class="btn btn-danger" onclick="removerReserva()">Excluir</button><br/>
                </div>
            </div>
        </div>
    </div>
</form>

<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
<script>$(".select2").select2();</script>