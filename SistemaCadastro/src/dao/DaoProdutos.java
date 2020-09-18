package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.BeansCategorias;
import beans.BeansProdutos;
import connection.SingleConnection;

public class DaoProdutos {

	private Connection connection;

	public DaoProdutos() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeansProdutos produto) {
		try {
			String sql = "insert into produto (nome, quantidade, valor, categoria_id) values (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setDouble(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());
			statement.setLong(4, produto.getCategoria_id());
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void deletar(String id) {
		try {
			String sql = "delete from produto where id = '" + id + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public BeansProdutos consultar(String id) throws Exception {
		String sql = "select * from produto where id = '" + id + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			BeansProdutos produto = new BeansProdutos();
			produto.setId(resultSet.getLong("id"));
			produto.setNome(resultSet.getString("nome"));
			produto.setQuantidade(resultSet.getDouble("quantidade"));
			produto.setValor(resultSet.getDouble("valor"));
			produto.setCategoria_id(resultSet.getLong("categoria_id"));
			return produto;
		}

		return null;
	}

	public void atualizar(BeansProdutos produto) {
		try {
			String sql = "update produto set nome = ?, quantidade = ?, valor = ?, categoria_id = ? where = "
					+ produto.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setDouble(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());
			statement.setLong(4, produto.getCategoria_id());
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public boolean validarNome(String nome) throws Exception {
		String sql = "select count(1) as qtd from produto where nome = '" + nome + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}

	public List<BeansProdutos> listarProdutos() throws Exception {
		List<BeansProdutos> lista = new ArrayList<BeansProdutos>();
		String sql = "select * from produto";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeansProdutos produto = new BeansProdutos();
			produto.setId(resultSet.getLong("id"));
			produto.setNome(resultSet.getString("nome"));
			produto.setQuantidade(resultSet.getDouble("quantidade"));
			produto.setValor(resultSet.getDouble("valor"));
			produto.setCategoria_id(resultSet.getLong("categoria_id"));
			lista.add(produto);
		}

		return lista;
	}

	public List<BeansCategorias> listarCategorias() throws Exception {
		List<BeansCategorias> lista = new ArrayList<BeansCategorias>();
		String sql = "select * from categoria";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeansCategorias categoria = new BeansCategorias();
			categoria.setId(resultSet.getLong("id"));
			categoria.setNome(resultSet.getString("nome"));
			lista.add(categoria);
		}
		return lista;
	}
}
