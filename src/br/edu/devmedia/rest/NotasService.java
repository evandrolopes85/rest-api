package br.edu.devmedia.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.devmedia.dao.NotaDAO;
import br.edu.devmedia.entidade.Nota;

@Path( "/notas" )
public class NotasService {
	private static final String CHARSET_UTF8 =";charset=utf-8;";
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
	
	@GET
	@Path( "/list/{id}" )
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listarNotas(@PathParam( "id" ) int id) {
		Nota nota = null;
		try{
			Response.ok(notaDAO.buscaNotaPorId(id)).build();
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		
		return Response.status(404).build();
	}
	
	/*@GET
	@Path( "/list/{id}" )
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Nota listarNotas(@PathParam( "id" ) int id) {
		Nota nota = null;
		try{
			nota = notaDAO.buscaNotaPorId( id );
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		
		return nota;
	}*/
	
	@PUT
	@Path("/edit/{id}")
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
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String adicionarNota( Nota nota ){
		String msg = "";
		
		System.out.println( nota.getTitulo() );
		
		try{
			notaDAO.addNota(nota);;
			
			msg = "Nota adicionada com sucesso!";
		}catch( Exception ex ){
			msg = "Erro ao adicionar nota!";
			ex.printStackTrace();
		}
		
		return msg;
	}
	
	@DELETE
	@Path( "/delete/{id}" )
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
