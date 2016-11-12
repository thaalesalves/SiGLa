package controller.actions;

import activedirectory.ActiveDirectory;
import dao.EquipamentoDAO;
import dao.LaboratorioDAO;
import dao.ReservaDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Equipamento;
import model.Grupo;
import model.Laboratorio;
import model.Pessoa;
import model.Reserva;

public class LoginAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        try {
            // <editor-fold defaultstate="collapsed" desc="Atributos do método.">
            ActiveDirectory ad = new ActiveDirectory();
            Pessoa p = new Pessoa();
            Grupo groups = new Grupo();
            Reserva r = new Reserva();
            Equipamento e = new Equipamento();
            Laboratorio l = new Laboratorio();

            p.setUsername(request.getParameter("username")); // passa o atributo de usuário
            p.setSenha(request.getParameter("password")); // passa o atributo de senha

            String path = "//admlab001/c$/img/users/" + p.getUsername() + "_pic.jpg";
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
                    return "pagina/login"; // chama de volta a página de login
                }
            } // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Login sem sucesso.">
            else { // caso o usuário não exista
                request.setAttribute("login", "false");
                return "pagina/login"; // chama de volta a página de login
            } // </editor-fold>
        } catch (AuthenticationException e) {
            request.setAttribute("login", "false");
            return "pagina/login"; // chama de volta a página de login
        } catch (FileNotFoundException e) {
            session.setAttribute("exception", e);
            return "error/error";
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
