package controller.actions;

import dao.*;
import model.*;
import activedirectory.ActiveDirectory;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ConnectException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws NamingException, ServletException {
        try {
            // <editor-fold defaultstate="collapsed" desc="Atributos do método.">
            ActiveDirectory ad = new ActiveDirectory();
            Pessoa p = new Pessoa();
            Grupo groups = new Grupo();
            Reserva r = new Reserva();
            Equipamento e = new Equipamento();
            Laboratorio l = new Laboratorio();
            HttpSession session = request.getSession();

            p.setUsername(request.getParameter("username")); // passa o atributo de usuário
            p.setSenha(request.getParameter("password")); // passa o atributo de senha

            String path = "C:/img/users/" + p.getUsername() + "_pic.jpg";
            FileOutputStream pic = new FileOutputStream(new File(path));
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Invocação do login.">
            if (ad.login(p)) { // faz o login
                LaboratorioDAO daolab = new LaboratorioDAO();
                ReservaDAO resdao = new ReservaDAO();
                EquipamentoDAO equipdao = new EquipamentoDAO();

                e.setQtd(equipdao.qtdEquip());
                r.setQtd(resdao.qtdReservas());
                l.setQtd(daolab.qtdLabs());
                p.setNome(ad.getGivenName(p)); // passa o atributo de nome
                p.setNomeCompleto(ad.getCN(p)); // passa o atributo de nome completo
                p.setCargo(ad.getTitle(p)); // passa o atributo de cargo
                p.setDepto(ad.getDepartment(p)); // passa o atributo de cargo
                p.setChapa(ad.getOffice(p)); // passa o atributo da chapa             
                p.setEmail(p.getUsername() + "@umc.br"); // passa o atributo de email   
                p.setPicture(ad.getPicture(p)); // passa o atributo da foto
                if (p.getPicture() != null) {
                    pic.write(ad.getPicture(p));
                    pic.flush();
                    pic.close();
                }

                boolean acesso = false;
                for (String g : groups.getOu()) {
                    groups.setGrupo(g + groups.getDc());
                    if (ad.isMember(p, groups)) {
                        acesso = true;
                    }
                }

                if (acesso) {
                    session.setAttribute("pessoa", p); // salva dados do login na sessão

                    session.setAttribute("laboratorio", l); // salva dados dos labs na sessão
                    session.setAttribute("reserva-qtd", r); // salva dados das reservas na sessão
                    session.setAttribute("equipamento", e); // salva dados das equipamentos na sessão
                    return "pagina/home";
                } else {
                    request.setAttribute("login", "acesso");
                    return request.getContextPath() + "pagina/login"; // chama de volta a página de login
                }
            } // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Login sem sucesso.">
            else { // caso o usuário não exista
                request.setAttribute("login", "false");
                return "/index.jsp"; // chama de volta a página de login
            } // </editor-fold>
        } catch (ConnectException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
