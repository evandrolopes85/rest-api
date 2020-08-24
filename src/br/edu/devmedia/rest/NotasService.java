package br.edu.devmedia.rest;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import br.edu.devmedia.dao.NotaDAO;
import br.edu.devmedia.entidade.Nota;

@Path( "/notas" )
public class NotasService {
	private NotaDAO notaDAO;
	
	@PostConstruct
	private void init(){
		notaDAO = new NotaDAO();
	}
	
	@GET
	@Path( "/list" )
	@Produces(MediaType.APPLICATION_JSON)
	public List< Nota > listarNotas() {
		List< Nota > lista = null;
		try{
			lista = notaDAO.listarNotas();
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		
		return lista;
	}
	
	@POST
	@Path( "/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarNota( Nota nota, @PathParam( "id" ) int idNota ){
		String msg = "";
		
		System.out.println( nota.getTitulo() );
		
		try{
			notaDAO.editarNota( nota, idNota );
			
			msg = "Nota editada com sucesso!";
		}catch( Exception ex ){
			msg = "Erro ao editar nota!";
			ex.printStackTrace();
		}
		
		return msg;
	}
	
	@POST
	@Path( "/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerNota( @PathParam( "id" ) int idNota ){
		String msg = "";
		
		try{
			notaDAO.removeNota( idNota );
			
			msg = "Nota removida com sucesso!";
		}catch( Exception ex ){
			msg = "Erro ao remover nota!";
			ex.printStackTrace();
		}
		
		return msg;
	}
}
