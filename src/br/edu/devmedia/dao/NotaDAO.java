package br.edu.devmedia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.devmedia.config.BDConfig;
import br.edu.devmedia.entidade.Nota;

public class NotaDAO {
	
	public List< Nota > listarNotas() throws Exception {
		List< Nota > lista = new ArrayList<>();
		
		Connection con = BDConfig.getConnection();
		
		String sql = "SELECT * FROM TB_NOTA";
		
		PreparedStatement ps = con.prepareStatement( sql );
		ResultSet rs = ps.executeQuery();
		
		while( rs.next() ){
			Nota nota = new Nota();
			nota.setId( rs.getInt( "ID_NOTE") );
			nota.setTitulo( rs.getString( "TITULO" ) );
			nota.setDescricao( rs.getString( "DESCRICAO") );
			
			lista.add(nota);
		}
		
		return lista;
	}
	
	public Nota buscaNotaPorId( int idNota ) throws Exception {
		Nota nota = null;
		
		Connection con = BDConfig.getConnection();
		
		String sql = "SELECT * FROM TB_NOTA WHERE ID_NOTE = ?";
		
		PreparedStatement ps = con.prepareStatement( sql );
		ps.setInt( 1, idNota );
		ResultSet rs = ps.executeQuery();
		
		while( rs.next() ){
			nota = new Nota();
			nota.setId( rs.getInt( "ID_NOTE") );
			nota.setTitulo( rs.getString( "TITULO" ) );
			nota.setDescricao( rs.getString( "DESCRICAO") );
		}
		
		return nota;
	}
	
	public void addNota( Nota nota ) throws Exception {
		Connection con = BDConfig.getConnection();
		
		String sql = "INSERT INT TB_NOTA(TITULO, DESCRICAO) VALUES(?,?)";
		
		PreparedStatement ps = con.prepareStatement( sql );
	
		ps.setString( 1, nota.getTitulo() );
		ps.setString( 2, nota.getDescricao() );
	
		ps.execute();
	}
	
	public void editarNota( Nota nota, int idNota ) throws Exception {
		Connection con = BDConfig.getConnection();
		
		String sql = "UPDATE TB_NOTA SET TITULO = ?, DESCRICAO = ? WHERE ID_NOTE = ?";
		
		PreparedStatement ps = con.prepareStatement( sql );
	
		ps.setString( 1, nota.getTitulo() );
		ps.setString( 2, nota.getDescricao() );
		ps.setInt( 3, idNota );
		ps.execute();
	}
	
	public void removeNota( int idNota ) throws Exception {
		Connection con = BDConfig.getConnection();
		
		String sql = "DELETE FROM TB_NOTA WHERE ID_NOTE = ?";
		
		PreparedStatement ps = con.prepareStatement( sql );
	
		ps.setInt( 1, idNota );
	
		ps.execute();
	}
	
}
