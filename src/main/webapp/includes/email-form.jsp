<div class="box box-info">
    <div class="box-header">
        <i class="fa fa-envelope"></i>
        <h3 class="box-title">Email Rápido</h3>
    </div>
    <form action="${pageContext.request.contextPath}/AlmightyController" method="post">
        <div class="box-body">                            
            <div class="form-group">
                <input autocomplete="off" required type="email" class="form-control" name="emailto" placeholder="Destinatário">
            </div>
            <div class="form-group">
                <input autocomplete="off" required type="text" class="form-control" name="subject" placeholder="Assunto">
            </div>
            <div>
                <textarea required name="message" class="textarea" placeholder="Mensagem" style="width: 100%; height: 125px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
            </div>

        </div>
        <div class="box-footer clearfix">
            <button value="EnviarEmail" name="acao" type="submit" class="pull-right btn btn-default" id="sendEmail">Enviar
                <i class="fa fa-arrow-circle-right"></i></button>
        </div>
    </form>
</div>