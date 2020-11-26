//EventoEntityFacadeREST
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.StringReader;
import java.util.List;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import Tarefa2.EventoEntity;
import Tarefa2.JPAEvento;

/**
 *
 * @author viter
 */
@Path("eventos")
public class EventoEntityFacadeREST {
    
    private final JsonBuilderFactory factory;

    public EventoEntityFacadeREST() {
        factory = Json.createBuilderFactory(null);
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void create(@FormParam("nome") String nome, @FormParam("sigla") String snome, @FormParam("area") String area, @FormParam("instorg") String instorg) {
        JPAEvento dao = new JPAEvento();
        EventoEntity e = new EventoEntity();
        e.setNome_evento(nome);
        e.setSigla_evento(snome);
        e.setArea_concent_evento(area);
        e.setInst_organizadora(instorg);
        dao.salva(e);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void createJson(String ent) {
        JPAEvento dao = new JPAEvento();
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader jsonReader = factory.createReader(new StringReader(ent));
        JsonObject json = jsonReader.readObject();
        EventoEntity e = new EventoEntity();
        e.setNome_evento(json.getString("nome"));
        e.setSigla_evento(json.getString("sigla"));
        e.setArea_concent_evento(json.getString("area"));
        e.setInst_organizadora(json.getString("instorg"));
        dao.salva(e);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, EventoEntity entity) {
        JPAEvento dao = new JPAEvento();
        dao.salva(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        JPAEvento dao = new JPAEvento();
        dao.exclui(id);
    }

    @GET
    @Path("evento/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public EventoEntity find(@PathParam("id") Long id) {
        JPAEvento dao = new JPAEvento();
        return dao.recupera(id);
    }

    @GET
    @Path("evento/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public JsonObject findJson(@PathParam("id") Long id) {
        JPAEvento dao = new JPAEvento();
        EventoEntity e = dao.recupera(id);
        if (e == null)
            throw new RuntimeException("Entrada não encontrada...");
        JsonObjectBuilder builder = factory.createObjectBuilder();
        JsonObject obj = builder.add("nome", e.getNome_evento())
                .add("sigla", e.getSigla_evento())
                .add("area", e.getArea_concent_evento())
                .add("instorg", e.getInst_organizadora())
                .build();
        return obj;
    }

    @GET
    @Path("evento/{id}")
    @Produces({MediaType.TEXT_PLAIN})
    public String findplain(@PathParam("id") Long id) {
        JPAEvento dao = new JPAEvento();
        return dao.recupera(id).toString();
    }

    @GET
    @Path("todos")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<EventoEntity> findAll() {
        JPAEvento dao = new JPAEvento();
        return dao.ListarTodosEventos();
    }

    @GET
    @Path("siglas/{sn}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<EventoEntity> findAll(@PathParam("sn") String sn) {
        JPAEvento dao = new JPAEvento();
        return dao.buscaSiglaEvento(sn);
    }

}
