
import java.io.IOException;
import java.util.Date;

import spark.Request;
import spark.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class RelatoService {

	private RelatoDAO relatoDAO;

	public RelatoService() {
		
	}

	public Object add(Request request, Response response) throws ParseException {
		String id = request.queryParams("id");
		String nome = request.queryParams("nome");
	                    String quantidade = new SimpleDateFormat("dd/MM/yyyy").parse(request.queryParams("quantidade"));

		Relato relato = new Relato(id, nome, quantidade);

		relatoDAO.add(relato);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		String id = request.queryParams(":id");
		
		Relato relato = (Relato) relatoDAO.get(id);
		
		if (relato != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<relato>\n" + 
            		"\t<id>" + relato.getId() + "</id>\n" +
            		"\t<nome>" + relato.getNome() + "</nome>\n" +
					"\t<quantidade>" + relato.getQuantidade() + "</quantidade>\n"  +
                          "</relato>\n";
        } else {
            response.status(404); // 404 Not found
            return "Relato não encontrado.";
        }

	}

	public Object update(Request request, Response response) throws ParseException {
        String id = request.queryParams(":id");
        
		Relato relato = (Relato) relatoDAO.get(id);

        if (relato != null) {
        	relato.setId(request.queryParams("id"));
        	relato.setNome(request.queryParams("nome"));
        	relato.setQuantidade(new SimpleDateFormat("dd/MM/yyyy").parse(request.queryParams("quantidade")));
        	
        	relatoDAO.update(relato);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Relato não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        String id = request.queryParams(":id");

        Relato relato = (Relato) relatoDAO.get(id);

        if (relato != null) {

            relatoDAO.remove(id);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Relato não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<relato type=\"array\">");
		for (Relato relato : relatoDAO.getAll()) {
			returnValue.append("\n<relato>\n" + 
					"\t<id>" + relato.getId() + "</id>\n" +
		    		"\t<nome>" + relato.getNome() + "</nome>\n" +
					"\t<quantidade>" + relato.getQuantidade() + "</quantidade>\n" +
			    		"</relato>\n");
		}
		returnValue.append("</relato>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}