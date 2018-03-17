/*
 * Copyright (C) 2017 Thales Alves Pereira
 *
 * This file is part of SiGLa.

 * SiGLa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * SiGLa  is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
 */
package mailsender;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Modulo;
import model.Software;
import util.Logger;
import util.SiGLa;

public class SolicitacaoMail extends Mail {

    @Override
    public void sendMail(Mail mail) throws MessagingException, UnsupportedEncodingException, IOException, NullPointerException {
        try {
            final Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(SiGLa.getMailName() + "<"+ SiGLa.getMailSystem() + ">"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getPessoa().getEmail()));
            message.setSubject("SiGLa | Solicitação de Reserva");
            message.setContent(getMessage(mail), "text/html; charset=UTF-8");
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (MessagingException e) {
            Logger.logWarning(e, this.getClass());
        } catch (Exception e) {
            Logger.logWarning(e, this.getClass());
        }
    }

    @Override
    public String getMessage(Mail mail) {
        Calendar cal = Calendar.getInstance();
        String softwares = "";
        String modulos = "";

        for (Software s : mail.getSolicitacao().getSoftwares()) {
            softwares += s.getFabricante() + " " + s.getNome() + "; ";
        }

        for (Modulo m : mail.getSolicitacao().getModulos()) {
            modulos += m.getId() + "º módulo; ";
        }

        return "<!doctype html>\n"
            + "<html xmlns=\'http://www.w3.org/1999/xhtml\' xmlns:v=\'urn:schemas-microsoft-com:vml\' xmlns:o=\'urn:schemas-microsoft-com:office:office\'>\n"
            + "	<head>\n"
            + "		<!-- NAME: 1 COLUMN -->\n"
            + "		<!--[if gte mso 15]>\n"
            + "		<xml>\n"
            + "			<o:OfficeDocumentSettings>\n"
            + "			<o:AllowPNG/>\n"
            + "			<o:PixelsPerInch>96</o:PixelsPerInch>\n"
            + "			</o:OfficeDocumentSettings>\n"
            + "		</xml>\n"
            + "		<![endif]-->\n"
            + "		<meta charset=\'UTF-8\'>\n"
            + "        <meta http-equiv=\'X-UA-Compatible\' content=\'IE=edge\'>\n"
            + "        <meta name=\'viewport\' content=\'width=device-width, initial-scale=1\'>\n"
            + "		<title>*|MC:SUBJECT|*</title>\n"
            + "        \n"
            + "    <style type=\'text/css\'>\n"
            + "		p{\n"
            + "			margin:10px 0;\n"
            + "			padding:0;\n"
            + "		}\n"
            + "		table{\n"
            + "			border-collapse:collapse;\n"
            + "		}\n"
            + "		h1,h2,h3,h4,h5,h6{\n"
            + "			display:block;\n"
            + "			margin:0;\n"
            + "			padding:0;\n"
            + "		}\n"
            + "		img,a img{\n"
            + "			border:0;\n"
            + "			height:auto;\n"
            + "			outline:none;\n"
            + "			text-decoration:none;\n"
            + "		}\n"
            + "		body,#bodyTable,#bodyCell{\n"
            + "			height:100%;\n"
            + "			margin:0;\n"
            + "			padding:0;\n"
            + "			width:100%;\n"
            + "		}\n"
            + "		.mcnPreviewText{\n"
            + "			display:none !important;\n"
            + "		}\n"
            + "		#outlook a{\n"
            + "			padding:0;\n"
            + "		}\n"
            + "		img{\n"
            + "			-ms-interpolation-mode:bicubic;\n"
            + "		}\n"
            + "		table{\n"
            + "			mso-table-lspace:0pt;\n"
            + "			mso-table-rspace:0pt;\n"
            + "		}\n"
            + "		.ReadMsgBody{\n"
            + "			width:100%;\n"
            + "		}\n"
            + "		.ExternalClass{\n"
            + "			width:100%;\n"
            + "		}\n"
            + "		p,a,li,td,blockquote{\n"
            + "			mso-line-height-rule:exactly;\n"
            + "		}\n"
            + "		a[href^=tel],a[href^=sms]{\n"
            + "			color:inherit;\n"
            + "			cursor:default;\n"
            + "			text-decoration:none;\n"
            + "		}\n"
            + "		p,a,li,td,body,table,blockquote{\n"
            + "			-ms-text-size-adjust:100%;\n"
            + "			-webkit-text-size-adjust:100%;\n"
            + "		}\n"
            + "		.ExternalClass,.ExternalClass p,.ExternalClass td,.ExternalClass div,.ExternalClass span,.ExternalClass font{\n"
            + "			line-height:100%;\n"
            + "		}\n"
            + "		a[x-apple-data-detectors]{\n"
            + "			color:inherit !important;\n"
            + "			text-decoration:none !important;\n"
            + "			font-size:inherit !important;\n"
            + "			font-family:inherit !important;\n"
            + "			font-weight:inherit !important;\n"
            + "			line-height:inherit !important;\n"
            + "		}\n"
            + "		#bodyCell{\n"
            + "			padding:10px;\n"
            + "		}\n"
            + "		.templateContainer{\n"
            + "			max-width:600px !important;\n"
            + "		}\n"
            + "		a.mcnButton{\n"
            + "			display:block;\n"
            + "		}\n"
            + "		.mcnImage{\n"
            + "			vertical-align:bottom;\n"
            + "		}\n"
            + "		.mcnTextContent{\n"
            + "			word-break:break-word;\n"
            + "		}\n"
            + "		.mcnTextContent img{\n"
            + "			height:auto !important;\n"
            + "		}\n"
            + "		.mcnDividerBlock{\n"
            + "			table-layout:fixed !important;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Page\n"
            + "	@section Background Style\n"
            + "	@tip Set the background color and top border for your email. You may want to choose colors that match your company\'s branding.\n"
            + "	*/\n"
            + "		body,#bodyTable{\n"
            + "			/*@editable*/background-color:#ecf0f5;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Page\n"
            + "	@section Background Style\n"
            + "	@tip Set the background color and top border for your email. You may want to choose colors that match your company\'s branding.\n"
            + "	*/\n"
            + "		#bodyCell{\n"
            + "			/*@editable*/border-top:0;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Page\n"
            + "	@section Email Border\n"
            + "	@tip Set the border for your email.\n"
            + "	*/\n"
            + "		.templateContainer{\n"
            + "			/*@editable*/border:0;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Page\n"
            + "	@section Heading 1\n"
            + "	@tip Set the styling for all first-level headings in your emails. These should be the largest of your headings.\n"
            + "	@style heading 1\n"
            + "	*/\n"
            + "		h1{\n"
            + "			/*@editable*/color:#202020;\n"
            + "			/*@editable*/font-family:Helvetica;\n"
            + "			/*@editable*/font-size:26px;\n"
            + "			/*@editable*/font-style:normal;\n"
            + "			/*@editable*/font-weight:bold;\n"
            + "			/*@editable*/line-height:125%;\n"
            + "			/*@editable*/letter-spacing:normal;\n"
            + "			/*@editable*/text-align:left;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Page\n"
            + "	@section Heading 2\n"
            + "	@tip Set the styling for all second-level headings in your emails.\n"
            + "	@style heading 2\n"
            + "	*/\n"
            + "		h2{\n"
            + "			/*@editable*/color:#202020;\n"
            + "			/*@editable*/font-family:Helvetica;\n"
            + "			/*@editable*/font-size:22px;\n"
            + "			/*@editable*/font-style:normal;\n"
            + "			/*@editable*/font-weight:bold;\n"
            + "			/*@editable*/line-height:125%;\n"
            + "			/*@editable*/letter-spacing:normal;\n"
            + "			/*@editable*/text-align:left;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Page\n"
            + "	@section Heading 3\n"
            + "	@tip Set the styling for all third-level headings in your emails.\n"
            + "	@style heading 3\n"
            + "	*/\n"
            + "		h3{\n"
            + "			/*@editable*/color:#202020;\n"
            + "			/*@editable*/font-family:Helvetica;\n"
            + "			/*@editable*/font-size:20px;\n"
            + "			/*@editable*/font-style:normal;\n"
            + "			/*@editable*/font-weight:bold;\n"
            + "			/*@editable*/line-height:125%;\n"
            + "			/*@editable*/letter-spacing:normal;\n"
            + "			/*@editable*/text-align:left;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Page\n"
            + "	@section Heading 4\n"
            + "	@tip Set the styling for all fourth-level headings in your emails. These should be the smallest of your headings.\n"
            + "	@style heading 4\n"
            + "	*/\n"
            + "		h4{\n"
            + "			/*@editable*/color:#202020;\n"
            + "			/*@editable*/font-family:Helvetica;\n"
            + "			/*@editable*/font-size:18px;\n"
            + "			/*@editable*/font-style:normal;\n"
            + "			/*@editable*/font-weight:bold;\n"
            + "			/*@editable*/line-height:125%;\n"
            + "			/*@editable*/letter-spacing:normal;\n"
            + "			/*@editable*/text-align:left;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Preheader\n"
            + "	@section Preheader Style\n"
            + "	@tip Set the background color and borders for your email\'s preheader area.\n"
            + "	*/\n"
            + "		#templatePreheader{\n"
            + "			/*@editable*/background-color:#3c8dbc;\n"
            + "			/*@editable*/background-image:none;\n"
            + "			/*@editable*/background-repeat:no-repeat;\n"
            + "			/*@editable*/background-position:center;\n"
            + "			/*@editable*/background-size:cover;\n"
            + "			/*@editable*/border-top:0;\n"
            + "			/*@editable*/border-bottom:0;\n"
            + "			/*@editable*/padding-top:9px;\n"
            + "			/*@editable*/padding-bottom:9px;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Preheader\n"
            + "	@section Preheader Text\n"
            + "	@tip Set the styling for your email\'s preheader text. Choose a size and color that is easy to read.\n"
            + "	*/\n"
            + "		#templatePreheader .mcnTextContent,#templatePreheader .mcnTextContent p{\n"
            + "			/*@editable*/color:#656565;\n"
            + "			/*@editable*/font-family:Helvetica;\n"
            + "			/*@editable*/font-size:12px;\n"
            + "			/*@editable*/line-height:150%;\n"
            + "			/*@editable*/text-align:left;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Preheader\n"
            + "	@section Preheader Link\n"
            + "	@tip Set the styling for your email\'s preheader links. Choose a color that helps them stand out from your text.\n"
            + "	*/\n"
            + "		#templatePreheader .mcnTextContent a,#templatePreheader .mcnTextContent p a{\n"
            + "			/*@editable*/color:#656565;\n"
            + "			/*@editable*/font-weight:normal;\n"
            + "			/*@editable*/text-decoration:underline;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Header\n"
            + "	@section Header Style\n"
            + "	@tip Set the background color and borders for your email\'s header area.\n"
            + "	*/\n"
            + "		#templateHeader{\n"
            + "			/*@editable*/background-color:#FFFFFF;\n"
            + "			/*@editable*/background-image:none;\n"
            + "			/*@editable*/background-repeat:no-repeat;\n"
            + "			/*@editable*/background-position:center;\n"
            + "			/*@editable*/background-size:cover;\n"
            + "			/*@editable*/border-top:0;\n"
            + "			/*@editable*/border-bottom:0;\n"
            + "			/*@editable*/padding-top:9px;\n"
            + "			/*@editable*/padding-bottom:0;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Header\n"
            + "	@section Header Text\n"
            + "	@tip Set the styling for your email\'s header text. Choose a size and color that is easy to read.\n"
            + "	*/\n"
            + "		#templateHeader .mcnTextContent,#templateHeader .mcnTextContent p{\n"
            + "			/*@editable*/color:#202020;\n"
            + "			/*@editable*/font-family:Helvetica;\n"
            + "			/*@editable*/font-size:16px;\n"
            + "			/*@editable*/line-height:150%;\n"
            + "			/*@editable*/text-align:left;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Header\n"
            + "	@section Header Link\n"
            + "	@tip Set the styling for your email\'s header links. Choose a color that helps them stand out from your text.\n"
            + "	*/\n"
            + "		#templateHeader .mcnTextContent a,#templateHeader .mcnTextContent p a{\n"
            + "			/*@editable*/color:#2BAADF;\n"
            + "			/*@editable*/font-weight:normal;\n"
            + "			/*@editable*/text-decoration:underline;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Body\n"
            + "	@section Body Style\n"
            + "	@tip Set the background color and borders for your email\'s body area.\n"
            + "	*/\n"
            + "		#templateBody{\n"
            + "			/*@editable*/background-color:#ffffff;\n"
            + "			/*@editable*/background-image:none;\n"
            + "			/*@editable*/background-repeat:no-repeat;\n"
            + "			/*@editable*/background-position:center;\n"
            + "			/*@editable*/background-size:cover;\n"
            + "			/*@editable*/border-top:0;\n"
            + "			/*@editable*/border-bottom:2px solid #EAEAEA;\n"
            + "			/*@editable*/padding-top:0;\n"
            + "			/*@editable*/padding-bottom:9px;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Body\n"
            + "	@section Body Text\n"
            + "	@tip Set the styling for your email\'s body text. Choose a size and color that is easy to read.\n"
            + "	*/\n"
            + "		#templateBody .mcnTextContent,#templateBody .mcnTextContent p{\n"
            + "			/*@editable*/color:#202020;\n"
            + "			/*@editable*/font-family:\'Roboto\', \'Helvetica Neue\', Helvetica, Arial, sans-serif;\n"
            + "			/*@editable*/font-size:16px;\n"
            + "			/*@editable*/line-height:150%;\n"
            + "			/*@editable*/text-align:left;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Body\n"
            + "	@section Body Link\n"
            + "	@tip Set the styling for your email\'s body links. Choose a color that helps them stand out from your text.\n"
            + "	*/\n"
            + "		#templateBody .mcnTextContent a,#templateBody .mcnTextContent p a{\n"
            + "			/*@editable*/color:#2BAADF;\n"
            + "			/*@editable*/font-weight:normal;\n"
            + "			/*@editable*/text-decoration:underline;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Footer\n"
            + "	@section Footer Style\n"
            + "	@tip Set the background color and borders for your email\'s footer area.\n"
            + "	*/\n"
            + "		#templateFooter{\n"
            + "			/*@editable*/background-color:#3c8dbc;\n"
            + "			/*@editable*/background-image:none;\n"
            + "			/*@editable*/background-repeat:no-repeat;\n"
            + "			/*@editable*/background-position:center;\n"
            + "			/*@editable*/background-size:cover;\n"
            + "			/*@editable*/border-top:0;\n"
            + "			/*@editable*/border-bottom:0;\n"
            + "			/*@editable*/padding-top:9px;\n"
            + "			/*@editable*/padding-bottom:9px;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Footer\n"
            + "	@section Footer Text\n"
            + "	@tip Set the styling for your email\'s footer text. Choose a size and color that is easy to read.\n"
            + "	*/\n"
            + "		#templateFooter .mcnTextContent,#templateFooter .mcnTextContent p{\n"
            + "			/*@editable*/color:#ffffff;\n"
            + "			/*@editable*/font-family:\'Roboto\', \'Helvetica Neue\', Helvetica, Arial, sans-serif;\n"
            + "			/*@editable*/font-size:12px;\n"
            + "			/*@editable*/line-height:150%;\n"
            + "			/*@editable*/text-align:center;\n"
            + "		}\n"
            + "	/*\n"
            + "	@tab Footer\n"
            + "	@section Footer Link\n"
            + "	@tip Set the styling for your email\'s footer links. Choose a color that helps them stand out from your text.\n"
            + "	*/\n"
            + "		#templateFooter .mcnTextContent a,#templateFooter .mcnTextContent p a{\n"
            + "			/*@editable*/color:#656565;\n"
            + "			/*@editable*/font-weight:normal;\n"
            + "			/*@editable*/text-decoration:underline;\n"
            + "		}\n"
            + "	@media only screen and (min-width:768px){\n"
            + "		.templateContainer{\n"
            + "			width:600px !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		body,table,td,p,a,li,blockquote{\n"
            + "			-webkit-text-size-adjust:none !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		body{\n"
            + "			width:100% !important;\n"
            + "			min-width:100% !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		#bodyCell{\n"
            + "			padding-top:10px !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		.mcnImage{\n"
            + "			width:100% !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		.mcnCartContainer,.mcnCaptionTopContent,.mcnRecContentContainer,.mcnCaptionBottomContent,.mcnTextContentContainer,.mcnBoxedTextContentContainer,.mcnImageGroupContentContainer,.mcnCaptionLeftTextContentContainer,.mcnCaptionRightTextContentContainer,.mcnCaptionLeftImageContentContainer,.mcnCaptionRightImageContentContainer,.mcnImageCardLeftTextContentContainer,.mcnImageCardRightTextContentContainer{\n"
            + "			max-width:100% !important;\n"
            + "			width:100% !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		.mcnBoxedTextContentContainer{\n"
            + "			min-width:100% !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		.mcnImageGroupContent{\n"
            + "			padding:9px !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		.mcnCaptionLeftContentOuter .mcnTextContent,.mcnCaptionRightContentOuter .mcnTextContent{\n"
            + "			padding-top:9px !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		.mcnImageCardTopImageContent,.mcnCaptionBlockInner .mcnCaptionTopContent:last-child .mcnTextContent{\n"
            + "			padding-top:18px !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		.mcnImageCardBottomImageContent{\n"
            + "			padding-bottom:9px !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		.mcnImageGroupBlockInner{\n"
            + "			padding-top:0 !important;\n"
            + "			padding-bottom:0 !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		.mcnImageGroupBlockOuter{\n"
            + "			padding-top:9px !important;\n"
            + "			padding-bottom:9px !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		.mcnTextContent,.mcnBoxedTextContentColumn{\n"
            + "			padding-right:18px !important;\n"
            + "			padding-left:18px !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		.mcnImageCardLeftImageContent,.mcnImageCardRightImageContent{\n"
            + "			padding-right:18px !important;\n"
            + "			padding-bottom:0 !important;\n"
            + "			padding-left:18px !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "		.mcpreview-image-uploader{\n"
            + "			display:none !important;\n"
            + "			width:100% !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "	/*\n"
            + "	@tab Mobile Styles\n"
            + "	@section Heading 1\n"
            + "	@tip Make the first-level headings larger in size for better readability on small screens.\n"
            + "	*/\n"
            + "		h1{\n"
            + "			/*@editable*/font-size:22px !important;\n"
            + "			/*@editable*/line-height:125% !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "	/*\n"
            + "	@tab Mobile Styles\n"
            + "	@section Heading 2\n"
            + "	@tip Make the second-level headings larger in size for better readability on small screens.\n"
            + "	*/\n"
            + "		h2{\n"
            + "			/*@editable*/font-size:20px !important;\n"
            + "			/*@editable*/line-height:125% !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "	/*\n"
            + "	@tab Mobile Styles\n"
            + "	@section Heading 3\n"
            + "	@tip Make the third-level headings larger in size for better readability on small screens.\n"
            + "	*/\n"
            + "		h3{\n"
            + "			/*@editable*/font-size:18px !important;\n"
            + "			/*@editable*/line-height:125% !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "	/*\n"
            + "	@tab Mobile Styles\n"
            + "	@section Heading 4\n"
            + "	@tip Make the fourth-level headings larger in size for better readability on small screens.\n"
            + "	*/\n"
            + "		h4{\n"
            + "			/*@editable*/font-size:16px !important;\n"
            + "			/*@editable*/line-height:150% !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "	/*\n"
            + "	@tab Mobile Styles\n"
            + "	@section Boxed Text\n"
            + "	@tip Make the boxed text larger in size for better readability on small screens. We recommend a font size of at least 16px.\n"
            + "	*/\n"
            + "		.mcnBoxedTextContentContainer .mcnTextContent,.mcnBoxedTextContentContainer .mcnTextContent p{\n"
            + "			/*@editable*/font-size:14px !important;\n"
            + "			/*@editable*/line-height:150% !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "	/*\n"
            + "	@tab Mobile Styles\n"
            + "	@section Preheader Visibility\n"
            + "	@tip Set the visibility of the email\'s preheader on small screens. You can hide it to save space.\n"
            + "	*/\n"
            + "		#templatePreheader{\n"
            + "			/*@editable*/display:block !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "	/*\n"
            + "	@tab Mobile Styles\n"
            + "	@section Preheader Text\n"
            + "	@tip Make the preheader text larger in size for better readability on small screens.\n"
            + "	*/\n"
            + "		#templatePreheader .mcnTextContent,#templatePreheader .mcnTextContent p{\n"
            + "			/*@editable*/font-size:14px !important;\n"
            + "			/*@editable*/line-height:150% !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "	/*\n"
            + "	@tab Mobile Styles\n"
            + "	@section Header Text\n"
            + "	@tip Make the header text larger in size for better readability on small screens.\n"
            + "	*/\n"
            + "		#templateHeader .mcnTextContent,#templateHeader .mcnTextContent p{\n"
            + "			/*@editable*/font-size:16px !important;\n"
            + "			/*@editable*/line-height:150% !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "	/*\n"
            + "	@tab Mobile Styles\n"
            + "	@section Body Text\n"
            + "	@tip Make the body text larger in size for better readability on small screens. We recommend a font size of at least 16px.\n"
            + "	*/\n"
            + "		#templateBody .mcnTextContent,#templateBody .mcnTextContent p{\n"
            + "			/*@editable*/font-size:16px !important;\n"
            + "			/*@editable*/line-height:150% !important;\n"
            + "		}\n"
            + "\n"
            + "}	@media only screen and (max-width: 480px){\n"
            + "	/*\n"
            + "	@tab Mobile Styles\n"
            + "	@section Footer Text\n"
            + "	@tip Make the footer content text larger in size for better readability on small screens.\n"
            + "	*/\n"
            + "		#templateFooter .mcnTextContent,#templateFooter .mcnTextContent p{\n"
            + "			/*@editable*/font-size:14px !important;\n"
            + "			/*@editable*/line-height:150% !important;\n"
            + "		}\n"
            + "\n"
            + "}</style></head>\n"
            + "    <body>\n"
            + "        <center>\n"
            + "            <table align=\'center\' border=\'0\' cellpadding=\'0\' cellspacing=\'0\' height=\'100%\' width=\'100%\' id=\'bodyTable\'>\n"
            + "                <tr>\n"
            + "                    <td align=\'center\' valign=\'top\' id=\'bodyCell\'>\n"
            + "                        <!-- BEGIN TEMPLATE // -->\n"
            + "						<!--[if (gte mso 9)|(IE)]>\n"
            + "						<table align=\'center\' border=\'0\' cellspacing=\'0\' cellpadding=\'0\' width=\'600\' style=\'width:600px;\'>\n"
            + "						<tr>\n"
            + "						<td align=\'center\' valign=\'top\' width=\'600\' style=\'width:600px;\'>\n"
            + "						<![endif]-->\n"
            + "                        <table border=\'0\' cellpadding=\'0\' cellspacing=\'0\' width=\'100%\' class=\'templateContainer\'>\n"
            + "                            <tr>\n"
            + "                                <td valign=\'top\' id=\'templatePreheader\'><table border=\'0\' cellpadding=\'0\' cellspacing=\'0\' width=\'100%\' class=\'mcnTextBlock\' style=\'min-width:100%;\'>\n"
            + "    <tbody class=\'mcnTextBlockOuter\'>\n"
            + "        <tr>\n"
            + "            <td valign=\'top\' class=\'mcnTextBlockInner\' style=\'padding-top:9px;\'>\n"
            + "              	<!--[if mso]>\n"
            + "				<table align=\'left\' border=\'0\' cellspacing=\'0\' cellpadding=\'0\' width=\'100%\' style=\'width:100%;\'>\n"
            + "				<tr>\n"
            + "				<![endif]-->\n"
            + "			    \n"
            + "				<!--[if mso]>\n"
            + "				<td valign=\'top\' width=\'600\' style=\'width:600px;\'>\n"
            + "				<![endif]-->\n"
            + "                <table align=\'left\' border=\'0\' cellpadding=\'0\' cellspacing=\'0\' style=\'max-width:100%; min-width:100%;\' width=\'100%\' class=\'mcnTextContentContainer\'>\n"
            + "                    <tbody><tr>\n"
            + "                        \n"
            + "                        <td valign=\'top\' class=\'mcnTextContent\' style=\'padding: 0px 18px 9px;color: #FFFFFF;text-align: center;\'>\n"
            + "                        \n"
            + "                            \n"
            + "                        </td>\n"
            + "                    </tr>\n"
            + "                </tbody></table>\n"
            + "				<!--[if mso]>\n"
            + "				</td>\n"
            + "				<![endif]-->\n"
            + "                \n"
            + "				<!--[if mso]>\n"
            + "				</tr>\n"
            + "				</table>\n"
            + "				<![endif]-->\n"
            + "            </td>\n"
            + "        </tr>\n"
            + "    </tbody>\n"
            + "</table></td>\n"
            + "                            </tr>\n"
            + "                            <tr>\n"
            + "                                <td valign=\'top\' id=\'templateHeader\'><table border=\'0\' cellpadding=\'0\' cellspacing=\'0\' width=\'100%\' class=\'mcnImageBlock\' style=\'min-width:100%;\'>\n"
            + "    <tbody class=\'mcnImageBlockOuter\'>\n"
            + "            <tr>\n"
            + "                <td valign=\'top\' style=\'padding:9px\' class=\'mcnImageBlockInner\'>\n"
            + "                    <table align=\'left\' width=\'100%\' border=\'0\' cellpadding=\'0\' cellspacing=\'0\' class=\'mcnImageContentContainer\' style=\'min-width:100%;\'>\n"
            + "                        <tbody><tr>\n"
            + "                            <td class=\'mcnImageContent\' valign=\'top\' style=\'padding-right: 9px; padding-left: 9px; padding-top: 0; padding-bottom: 0; text-align:center;\'>\n"
            + "                                \n"
            + "                                    \n"
            + "                                        <img align=\'center\' alt=\'\' src=\'https://gallery.mailchimp.com/c30555f51cd214c84e41274d9/images/a915647a-e7ed-4fa7-a138-74c69aa49c70.png\' width=\'564\' style=\'max-width:2540px; padding-bottom: 0; display: inline !important; vertical-align: bottom;\' class=\'mcnImage\'>\n"
            + "                                    \n"
            + "                                \n"
            + "                            </td>\n"
            + "                        </tr>\n"
            + "                    </tbody></table>\n"
            + "                </td>\n"
            + "            </tr>\n"
            + "    </tbody>\n"
            + "</table></td>\n"
            + "                            </tr>\n"
            + "                            <tr>\n"
            + "                                <td valign=\'top\' id=\'templateBody\'><table border=\'0\' cellpadding=\'0\' cellspacing=\'0\' width=\'100%\' class=\'mcnTextBlock\' style=\'min-width:100%;\'>\n"
            + "    <tbody class=\'mcnTextBlockOuter\'>\n"
            + "        <tr>\n"
            + "            <td valign=\'top\' class=\'mcnTextBlockInner\' style=\'padding-top:9px;\'>\n"
            + "              	<!--[if mso]>\n"
            + "				<table align=\'left\' border=\'0\' cellspacing=\'0\' cellpadding=\'0\' width=\'100%\' style=\'width:100%;\'>\n"
            + "				<tr>\n"
            + "				<![endif]-->\n"
            + "			    \n"
            + "				<!--[if mso]>\n"
            + "				<td valign=\'top\' width=\'600\' style=\'width:600px;\'>\n"
            + "				<![endif]-->\n"
            + "                <table align=\'left\' border=\'0\' cellpadding=\'0\' cellspacing=\'0\' style=\'max-width:100%; min-width:100%;\' width=\'100%\' class=\'mcnTextContentContainer\'>\n"
            + "                    <tbody><tr>\n"
            + "                        \n"
            + "                        <td valign=\'top\' class=\'mcnTextContent\' style=\'padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;\'>\n"
            + "                        \n"
            + "                            <h1 style=\'text-align: center;\'>Solicitação recebida!</h1>\n"
            + "\n"
            + "<p style=\'text-align: center;\'>Recebemos sua solicitação, <strong>" + mail.getSolicitacao().getPessoa().getNome() + "</strong>. Pode ficar tranquilo que vamos avaliá-la, e você receberá o retorno assim que possível.<br>\n"
            + "<br>\n"
            + "Você fez uma solicitação para o <strong>" + mail.getSolicitacao().getTurma() + "</strong> de <strong>" + mail.getSolicitacao().getCurso().getModalidade() + " em " + mail.getSolicitacao().getCurso().getNome() + "</strong>, que tem <strong>" + mail.getSolicitacao().getQtdAlunos() + " alunos</strong>, de <strong>" + mail.getSolicitacao().getDiaSemana() + "</strong>, e foi registrada como solicitação <strong>#" + mail.getSolicitacao().getId() + "</strong>. Organizamos os dados abaixo para que você possa verificá-los.&nbsp;Caso haja algum dado incorreto, basta entrar em contato com a gente que nós damos um jeito.</p>\n"
            + "\n"
            + "<p style=\'text-align: left;\'><strong>Módulos:</strong> " + modulos + "<br>\n"
            + "<strong>Softwares:</strong> " + softwares + "<br>\n"
            + "<strong>Observação:</strong> " + mail.getSolicitacao().getObservacao() + "</p>\n"
            + "\n"
            + "                        </td>\n"
            + "                    </tr>\n"
            + "                </tbody></table>\n"
            + "				<!--[if mso]>\n"
            + "				</td>\n"
            + "				<![endif]-->\n"
            + "                \n"
            + "				<!--[if mso]>\n"
            + "				</tr>\n"
            + "				</table>\n"
            + "				<![endif]-->\n"
            + "            </td>\n"
            + "        </tr>\n"
            + "    </tbody>\n"
            + "</table></td>\n"
            + "                            </tr>\n"
            + "                            <tr>\n"
            + "                                <td valign=\'top\' id=\'templateFooter\'><table border=\'0\' cellpadding=\'0\' cellspacing=\'0\' width=\'100%\' class=\'mcnTextBlock\' style=\'min-width:100%;\'>\n"
            + "    <tbody class=\'mcnTextBlockOuter\'>\n"
            + "        <tr>\n"
            + "            <td valign=\'top\' class=\'mcnTextBlockInner\' style=\'padding-top:9px;\'>\n"
            + "              	<!--[if mso]>\n"
            + "				<table align=\'left\' border=\'0\' cellspacing=\'0\' cellpadding=\'0\' width=\'100%\' style=\'width:100%;\'>\n"
            + "				<tr>\n"
            + "				<![endif]-->\n"
            + "			    \n"
            + "				<!--[if mso]>\n"
            + "				<td valign=\'top\' width=\'600\' style=\'width:600px;\'>\n"
            + "				<![endif]-->\n"
            + "                <table align=\'left\' border=\'0\' cellpadding=\'0\' cellspacing=\'0\' style=\'max-width:100%; min-width:100%;\' width=\'100%\' class=\'mcnTextContentContainer\'>\n"
            + "                    <tbody><tr>\n"
            + "                        \n"
            + "                        <td valign=\'top\' class=\'mcnTextContent\' style=\'padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;\'>\n"
            + "                        \n"
            + "                            <em>Copyright © " + cal.get(Calendar.YEAR) + " Instituto Brasileiro de Engenharia e Tecnologia, All rights reserved.</em><br>\n"
            + "                        </td>\n"
            + "                    </tr>\n"
            + "                </tbody></table>\n"
            + "				<!--[if mso]>\n"
            + "				</td>\n"
            + "				<![endif]-->\n"
            + "              Ï  \n"
            + "				<!--[if mso]>\n"
            + "				</tr>\n"
            + "				</table>\n"
            + "				<![endif]-->\n"
            + "            </td>\n"
            + "        </tr>\n"
            + "    </tbody>\n"
            + "</table></td>\n"
            + "                            </tr>\n"
            + "                        </table>\n"
            + "						<!--[if (gte mso 9)|(IE)]>\n"
            + "						</td>\n"
            + "						</tr>\n"
            + "						</table>\n"
            + "						<![endif]-->\n"
            + "                        <!-- // END TEMPLATE -->\n"
            + "                    </td>\n"
            + "                </tr>\n"
            + "            </table>\n"
            + "        </center>\n"
            + "    </body>\n"
            + "</html>";
    }
}
