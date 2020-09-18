package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.BeansTelefones;
import connection.SingleConnection;

public class DaoTelefones {

	private Connection connection;

	public DaoTelefones() {
		connection = SingleConnection.getConnection();
	}

	public List<BeansTelefones> listarTelefones(Long user) throws Exception {
		List<BeansTelefones> lista = new ArrayList<BeansTelefones>();
		String sql = "select * from telefone where usuario = " + user;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeansTelefones telefone = new BeansTelefones();
			telefone.setId(resultSet.getLong("id"));
			telefone.setNumero(resultSet.getString("numero"));
			telefone.setTipo(resultSet.getString("tipo"));
			telefone.setUsuario(resultSet.getLong("usuario"));
			lista.add(telefone);
		}

		return lista;
	}

	public void deletar(String id) {
		try {
			String sql = "delete from telefone where = '" + id + "'";
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
	
	public void salvar(BeansTelefones telefone) {
		try {
			String sql = "insert into telefone (numero, tipo, usuario) values (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
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
}
