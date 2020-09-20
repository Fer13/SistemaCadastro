package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeansUsuarios;
import dao.DaoUsuario;

@WebServlet("/pesquisaServlet")
public class PesquisaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String descricaoPesquisa = request.getParameter("descricaoconsulta");
		
		if(descricaoPesquisa != null && !descricaoPesquisa.trim().isEmpty()) {
			try {
				List<BeansUsuarios> listaPesquisa = daoUsuario.listar(descricaoPesquisa);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", listaPesquisa);
				view.forward(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			try {
				request.setAttribute("usuarios", daoUsuario.listar());
			} catch (Exception e) {
				e.printStackTrace();
			}
			view.forward(request, response);
		}
	}

}
