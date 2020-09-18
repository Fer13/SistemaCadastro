package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeansUsuarios;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public List<BeansUsuarios> listar(String descricaoconsulta) throws SQLException {
		String sql = "select * from usuario where login <> 'login' and nome like '%" + descricaoconsulta + "%'";
		return consultarUsuarios(sql);
	}

	public List<BeansUsuarios> listar() throws Exception {
		String sql = "select * from usuario where login <> 'login'";
		return consultarUsuarios(sql);
	}

	private List<BeansUsuarios> consultarUsuarios(String sql) throws SQLException {
		List<BeansUsuarios> listar = new ArrayList<BeansUsuarios>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeansUsuarios usuarios = new BeansUsuarios();
			usuarios.setId(resultSet.getLong("id"));
			usuarios.setLogin(resultSet.getString("login"));
			usuarios.setSenha(resultSet.getString("senha"));
			usuarios.setNome(resultSet.getString("nome"));
			usuarios.setFone(resultSet.getString("fone"));
			usuarios.setCep(resultSet.getString("cep"));
			usuarios.setRua(resultSet.getString("rua"));
			usuarios.setBairro(resultSet.getString("bairro"));
			usuarios.setCidade(resultSet.getString("cidade"));
			usuarios.setEstado(resultSet.getString("estado"));
			usuarios.setIbge(resultSet.getString("ibge"));
			// usuarios.setFotoBase64(resultSet.getString("fotoBase64"));
			usuarios.setFotoBase64Miniatura(resultSet.getString("fotoBase64Miniatura"));
			usuarios.setContentType(resultSet.getString("contentType"));
			usuarios.setContentTypeCurriculo(resultSet.getString("contentTypeCurriculo"));
			usuarios.setCurriculoBase64(resultSet.getString("curriculoBase64"));
			usuarios.setAtivo(resultSet.getBoolean("ativo"));
			usuarios.setSexo(resultSet.getString("sexo"));
			usuarios.setPerfil(resultSet.getString("perfil"));
			listar.add(usuarios);
		}

		return listar;
	}

	public void salvar(BeansUsuarios usuarios) {
		try {
			String sql = "insert into usuario (login, senha, nome, fone, cep, rua, bairro, cidade, estado, ibge, "
					+ "fotoBase64, contentType, curriculoBase64, contentTypeCurriculo, fotoBase64Miniatura, ativo, sexo, perfil) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuarios.getLogin());
			statement.setString(2, usuarios.getSenha());
			statement.setString(3, usuarios.getNome());
			statement.setString(4, usuarios.getFone());
			statement.setString(5, usuarios.getCep());
			statement.setString(6, usuarios.getRua());
			statement.setString(7, usuarios.getBairro());
			statement.setString(8, usuarios.getCidade());
			statement.setString(9, usuarios.getEstado());
			statement.setString(10, usuarios.getIbge());
			statement.setString(11, usuarios.getFotoBase64());
			statement.setString(12, usuarios.getContentType());
			statement.setString(13, usuarios.getCurriculoBase64());
			statement.setString(14, usuarios.getContentTypeCurriculo());
			statement.setString(15, usuarios.getFotoBase64Miniatura());
			statement.setBoolean(16, usuarios.isAtivo());
			statement.setString(17, usuarios.getSexo());
			statement.setString(18, usuarios.getPerfil());
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

	public boolean validar(String login) throws Exception {
		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}

	public boolean validarLoginUpdate(String login, String id) throws Exception {
		String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id <> " + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}

	public boolean validarSenhaUpdate(String senha, String id) throws Exception {
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "' and id <> " + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}

	public boolean validarSenha(String senha) throws Exception {
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}

	public void deletar(String id) {
		try {
			String sql = "delete from usuario where id = '" + id + "' and login <>  'admin'";
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

	public BeansUsuarios consultar(String id) throws Exception {
		String sql = "select * from usuario where id = '" + id + "' and login <> 'admin'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			BeansUsuarios usuarios = new BeansUsuarios();
			usuarios.setId(resultSet.getLong("id"));
			usuarios.setLogin(resultSet.getString("login"));
			usuarios.setSenha(resultSet.getString("senha"));
			usuarios.setNome(resultSet.getString("nome"));
			usuarios.setFone(resultSet.getString("fone"));
			usuarios.setCep(resultSet.getString("cep"));
			usuarios.setRua(resultSet.getString("rua"));
			usuarios.setBairro(resultSet.getString("bairro"));
			usuarios.setCidade(resultSet.getString("cidade"));
			usuarios.setEstado(resultSet.getString("estado"));
			usuarios.setIbge(resultSet.getString("ibge"));
			usuarios.setFotoBase64(resultSet.getString("fotoBase64"));
			usuarios.setFotoBase64Miniatura(resultSet.getString("fotoBase64Miniatura"));
			usuarios.setContentType(resultSet.getString("contentType"));
			usuarios.setCurriculoBase64(resultSet.getString("curriculoBase64"));
			usuarios.setContentTypeCurriculo(resultSet.getString("contentTypeCurriculo"));
			usuarios.setAtivo(resultSet.getBoolean("ativo"));
			usuarios.setSexo(resultSet.getString("sexo"));
			usuarios.setPerfil(resultSet.getString("perfil"));
			return usuarios;
		}
		return null;
	}

	public void atualizar(BeansUsuarios usuarios) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update usuario set login = ?, senha = ?, nome = ?, ");
			sql.append(
					"cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ibge = ?, ativo = ?, sexo = ?, perfil = ?");

			if (usuarios.isAtualizarImagem()) {
				sql.append(",fotoBase64 = ?, contentType = ?");
			}
			if (usuarios.isAtualizarPdf()) {
				sql.append(",curriculoBase64 = ?, contentTypeCurriculo = ?");
			}
			if (usuarios.isAtualizarImagem()) {
				sql.append(",fotoBase64Miniatura = ?");
			}
			sql.append("where id = " + usuarios.getId());

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, usuarios.getLogin());
			statement.setString(2, usuarios.getSenha());
			statement.setString(3, usuarios.getNome());
			statement.setString(4, usuarios.getFone());
			statement.setString(5, usuarios.getCep());
			statement.setString(6, usuarios.getRua());
			statement.setString(7, usuarios.getBairro());
			statement.setString(8, usuarios.getCidade());
			statement.setString(9, usuarios.getEstado());
			statement.setString(10, usuarios.getIbge());
			statement.setBoolean(11, usuarios.isAtivo());
			statement.setString(12, usuarios.getSexo());
			statement.setString(13, usuarios.getPerfil());

			if (usuarios.isAtualizarImagem()) {
				statement.setString(14, usuarios.getFotoBase64());
				statement.setString(15, usuarios.getContentType());
			}
			if (usuarios.isAtualizarPdf()) {
				if (usuarios.isAtualizarPdf() && !usuarios.isAtualizarImagem()) {
					statement.setString(14, usuarios.getCurriculoBase64());
					statement.setString(15, usuarios.getContentTypeCurriculo());
				} else {
					statement.setString(16, usuarios.getCurriculoBase64());
					statement.setString(17, usuarios.getContentTypeCurriculo());
				}
			} else {
				if (usuarios.isAtualizarImagem()) {
					statement.setString(16, usuarios.getFotoBase64Miniatura());
				}
			}
			
			if(usuarios.isAtualizarImagem() && usuarios.isAtualizarPdf()) {
				statement.setString(18, usuarios.getFotoBase64Miniatura());
			}
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
	
	public void gravarImagem(String imagem) throws SQLException {
		String sql = "insert into usuario (imagem) values (?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, imagem);
		statement.execute();
	}
	
	public List<BeansUsuarios> getUsuarios() throws SQLException {
		List<BeansUsuarios> lista = new ArrayList<BeansUsuarios>();
		String sql = "select * from usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			BeansUsuarios usuarios = new BeansUsuarios();
			usuarios.setId(resultSet.getLong("id"));
			usuarios.setLogin(resultSet.getString("login"));
			usuarios.setSenha(resultSet.getString("senha"));
			usuarios.setImagem(resultSet.getString("imagem"));
			lista.add(usuarios);
		}
		return lista;
	}
}
