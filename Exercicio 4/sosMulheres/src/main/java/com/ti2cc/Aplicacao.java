package com.ti2cc;

import java.sql.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
	
public class Aplicacao {
	
	private static RelatoService relatoService = new RelatoService();
	
    public static void main(String[] args) throws Exception{
        port(5432);
        
        RelatoDAO Azure = new RelatoDAO();
        Azure.conectar();
        
        post("/relato", (request, response) -> relatoService.add(request, response));

        get("/relato/:id", (request, response) -> relatoService.get(request, response));

        get("/relato/update/:id", (request, response) -> relatoService.update(request, response));

        get("/relato/delete/:id", (request, response) -> relatoService.remove(request, response));

        get("/relato", (request, response) -> relatoService.getAll(request, response));
                	    
    }
   
}

