/*
 * Copyright (C) 2017 thaal
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thaal
 */
public class InstallationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath() + "/";
        boolean dominioNulo = util.SiGLa.getDomain().equals("null");
        
        System.out.println("Requisição atual: " + uri);
        
        if (dominioNulo && uri.equals(contextPath + "admin/install")) {
            System.out.println("Domínio está nulo e acessando admin > deixando passar");
            chain.doFilter(req, res);
        } else if (dominioNulo && (uri.equals(contextPath))) {
            System.out.println("Domínio está nulo e acessando raiz > redirecionando para admin");
            res.sendRedirect(contextPath + "admin/install");
        } else if (!dominioNulo && uri.equals(contextPath + "admin/install")) {
            System.out.println("Domínio está preenchido e acessando admin > error 404");
            res.sendRedirect(contextPath + "error/404");
        } else if (!dominioNulo && (uri.equals(contextPath))) {
            System.out.println("Domínio está preenchido e acessando raiz > deixando passar");
            chain.doFilter(req, res);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }

}
