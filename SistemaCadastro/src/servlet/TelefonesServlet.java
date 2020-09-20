package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.BeansUsuarios;
import beans.BeansTelefones;
import dao.DaoLogin;
import dao.DaoTelefones;
import dao.DaoUsuario;

@WebServlet("/salvarTelefones")
public class TelefonesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoTelefones daoTelefones = new DaoTelefones();
	private DaoUsuario daoUsuario = new DaoUsuario();

	public TelefonesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");
			
			if(user != null) {
				BeansUsuarios usuario = daoUsuario.consultar(user);
				if(acao.equalsIgnoreCase("addFone")) {
					request.getSession().setAttribute("userEscolhido", usuario);
					request.setAttribute("userEscolhido", usuario);
					RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefones.jsp");
					request.setAttribute("telefones", daoTelefones.listarTelefones(usuario.getId()));
					view.forward(request, response);
				}else if(acao.equalsIgnoreCase("deleteFone")) {
					String foneId = request.getParameter("foneId");
					daoTelefones.deletar(foneId);
					RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefones.jsp");
					request.setAttribute("telefones", daoTelefones.listarTelefones(usuario.getId()));
					request.setAttribute("msg", "Removido com sucesso!");
					view.forward(request, response);
				}
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefones.jsp");
				request.setAttribute("msg", daoUsuario.listar());
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
		BeansUsuarios usuario = (BeansUsuarios) request.getSession().getAttribute("userEscolhido");
		
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");
		String acao = request.getParameter("acao");
		
	    if(acao == null || (acao != null && !acao.equalsIgnoreCase("voltar"))) {
	    	if(numero == null || (numero != null && numero.isEmpty())) {
	    		RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefones.jsp");
	    		request.setAttribute("telefones", daoTelefones.listarTelefones(usuario.getId()));
	    		request.setAttribute("msg", "Informe o numero de telefone!");
	    		view.forward(request, response);
	    	}else {
	    		BeansTelefones telefones = new BeansTelefones();
	    		
	    		telefones.setNumero(numero);
	    		telefones.setTipo(tipo);
	    		telefones.setUsuario(usuario.getId());
	    		
	    		daoTelefones.salvar(telefones);
	    		
	    		request.getSession().setAttribute("userEscolhido",usuario);
	    		request.setAttribute("userEscolhido", usuario);
	    		
	    		RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefones.jsp");
	    		request.setAttribute("telefones", daoTelefones.listarTelefones(usuario.getId()));
	    		request.setAttribute("msg", "Telefone salvo com sucesso!");
	    		view.forward(request, response);
	    	}
	    }else {
	    	RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
	    	request.setAttribute("usuarios", daoUsuario.listar());
	    	view.forward(request, response);
	    }
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
