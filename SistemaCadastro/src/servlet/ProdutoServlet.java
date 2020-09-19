package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeansProdutos;
import dao.DaoProdutos;

@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoProdutos daoProdutos = new DaoProdutos();
	
	public ProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listartodos";
			String produto = request.getParameter("produto");
			
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			
			if(acao.equalsIgnoreCase("delete")) {
				daoProdutos.deletar(produto);
				request.setAttribute("produtos", daoProdutos.listarProdutos());
			}else if(acao.equalsIgnoreCase("editar")) {
				BeansProdutos produto2 = daoProdutos.consultar(produto);
				request.setAttribute("produto", produto2);
			}else if(acao.equalsIgnoreCase("listartodos")) {
				request.setAttribute("produtos", daoProdutos.listarProdutos());
			}
			request.setAttribute("categorias", daoProdutos.listarCategorias());
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if(acao != null && acao.equalsIgnoreCase("reset")) {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			try {
				request.setAttribute("produtos", daoProdutos.listarProdutos());
				view.forward(request, response);				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");
			String categoria_id = request.getParameter("categoria_id");
			
			boolean podeInserir = true;
			
			try {
				if(valor == null || valor.isEmpty()) {
					request.setAttribute("msg", "Valor R$ deve ser informado");
					podeInserir = false;
				}
				else if (quantidade == null || quantidade.isEmpty()) {
					request.setAttribute("msg", "Quantidade deve ser informado");
					podeInserir = false;
				}
				else if(nome == null || nome.isEmpty()) {
					request.setAttribute("msg", "O nome deve ser informado");
					podeInserir = false;
				}
				if(id == null || id.isEmpty() && !daoProdutos.validarNome(nome) && podeInserir) {
					request.setAttribute("msg", "Produto já existe com o mesmo nome");
					podeInserir = false;
				}
				BeansProdutos produto = new BeansProdutos();
				produto.setNome(nome);
				produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
				produto.setCategoria_id(Long.parseLong(categoria_id));
				
				if(quantidade != null && !quantidade.isEmpty()) {
					produto.setQuantidade(Double.parseDouble(quantidade));
				}
				if(valor != null && !valor.isEmpty()) {
					String valorParse = valor.replaceAll("\\", ""); //10500,00
					valorParse = valorParse.replaceAll("\\", "."); //10500.200
					produto.setValor(Double.parseDouble(valorParse));
				}
				if(id == null || id.isEmpty() && daoProdutos.validarNome(nome) && podeInserir) {
					daoProdutos.salvar(produto);
				}else if(id != null && !id.isEmpty() && podeInserir) {
					daoProdutos.atualizar(produto);
				}else if(podeInserir) {
					request.setAttribute("produto", produto);
				}
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProdutos.listarProdutos());
				request.setAttribute("categorias", daoProdutos.listarCategorias());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
