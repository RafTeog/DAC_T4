//EdicaoEntityFacadeREST
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
import Tarefa2.EdicaoEntity;
import Tarefa2.EventoEntity;
import Tarefa2.JPAEdicao;
import Tarefa2.JPAEvento;


/**
 *
 * @author viter
 */
@Path("edicao")
public class EdicaoEntityFacadeREST {
    
    private final JsonBuilderFactory factory;

    public EdicaoEntityFacadeREST() {
        factory = Json.createBuilderFactory(null);
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void create(@FormParam("pais") String pais, @FormParam("cidade") String cidade, @FormParam("dtfim") String dtfim,
            @FormParam("dtinicio") String dtinicio, @FormParam("numero") String numero, @FormParam("ano") String ano) {
        JPAEdicao dao = new JPAEdicao();
        EdicaoEntity e = new EdicaoEntity();
        e.setPais_edicao(pais);
        e.setCidade_edicao(cidade);
        e.setDatafim_edicao(dtfim);
        e.setDataInicio_edicao(dtinicio);
        e.setNumero_edicao(Integer.parseInt(numero));
        e.setAno_edicao(Integer.parseInt(ano));

        dao.salva(e);
    }

    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void createJson(String ent) {
        JPAEdicao dao = new JPAEdicao();
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader jsonReader = factory.createReader(new StringReader(ent));
        JsonObject json = jsonReader.readObject();
        EdicaoEntity e = new EdicaoEntity();
        e.setPais_edicao(json.getString("pais"));
        e.setCidade_edicao(json.getString("cidade"));
        e.setDatafim_edicao(json.getString("dtfim"));
        e.setDataInicio_edicao(json.getString("dtinicio"));
        e.setNumero_edicao(Integer.parseInt(json.getString("numero")));
        e.setAno_edicao(Integer.parseInt(json.getString("ano")));
        Long idEv=Long.parseLong(json.getString("idev"));
        JPAEvento daoEv = new JPAEvento();
        EventoEntity Ev = daoEv.recupera(idEv);
        Ev.addEvento(e);
        daoEv.salva(Ev);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("ided") Long ided, EdicaoEntity entity) {
        JPAEdicao dao = new JPAEdicao();
        dao.exclui(ided);
        dao.salva(entity);        
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        JPAEdicao dao = new JPAEdicao();
        dao.exclui(id);
    }

    @GET
    @Path("edicao/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public EdicaoEntity find(@PathParam("id") Long id) {
        JPAEdicao dao = new JPAEdicao();
        return dao.recupera(id);
    }

    @GET
    @Path("edicao/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public JsonObject findJson(@PathParam("id") Long id) {
        JPAEdicao dao = new JPAEdicao();
        EdicaoEntity e = dao.recupera(id);
        if (e == null)
            throw new RuntimeException("Entrada não encontrada...");
        JsonObjectBuilder builder = factory.createObjectBuilder();
        JsonObject obj = builder.add("pais", e.getPais_edicao())
                .add("cidade", e.getCidade_edicao())
                .add("dtfim", e.getDatafim_edicao())
                .add("dtfim", e.getDataInicio_edicao())
                .add("numero", e.getNumero_edicao())
                .add("ano", e.getAno_edicao())
                .build();
        return obj;
    }
    


    @GET
    @Path("edicao/{id}")
    @Produces({MediaType.TEXT_PLAIN})
    public String findplain(@PathParam("id") Long id) {
        JPAEdicao dao = new JPAEdicao();
        return dao.recupera(id).toString();
    }

    @GET
    @Path("todos")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<EdicaoEntity> findAll() {
        JPAEdicao dao = new JPAEdicao();
        return dao.ListarTodasEdicoes();
    }
    
     @GET
    @Path("todos/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<EdicaoEntity> findAllEdsEv(@PathParam("id") Long idev) {
        JPAEdicao dao = new JPAEdicao();
        return dao.ListarTodasEdicoes(idev);
    }

    @GET
    @Path("anoeds/{sn}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<EdicaoEntity> findAll(@PathParam("sn") String sn) {
        JPAEdicao dao = new JPAEdicao();
        return dao.buscaAnoEdicao(Integer.parseInt(sn));
    }
    @GET
    @Path("timestamp/{inidate};{enddate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Object> findAllBetweenDates(@PathParam("inidate") String inidate,@PathParam("enddate") String enddate) {
        JPAEdicao dao = new JPAEdicao();        
        return dao.buscaEdicoesTimeStamp(inidate,enddate);
    }
    
    @GET
    @Path("cidade/{cidade}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Object> findAllinCities(@PathParam("cidade") String cidade) {
        JPAEdicao dao = new JPAEdicao();        
        return dao.buscaEdicoesCidade(cidade);
    }
    
    @GET
    @Path("cidadetimestampe/{cidade}/{inidate};{enddate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Object> findAllinCitiesTimestamp(@PathParam("cidade") String cidade,@PathParam("inidate") String inidate,@PathParam("enddate") String enddate) {
        JPAEdicao dao = new JPAEdicao();        
        return dao.buscaEdicoesCidadeTimestamp(cidade,inidate,enddate);
    }

}
