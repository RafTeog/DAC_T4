//INICIO DA CLASSE ENTIDADE EVENTO
package Tarefa2;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class EventoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_evento;
    @Column
    @Basic(optional = false, fetch = FetchType.LAZY)
    private String nome_evento;
    @Column
    @Basic(optional = false, fetch = FetchType.LAZY)
    private String sigla_evento;
    @Column
    @Basic(optional = false, fetch = FetchType.LAZY)
    private String area_concent_evento;
    @Column
    @Basic(optional = false, fetch = FetchType.LAZY)
    private String inst_organizadora;
    @Column
    @Basic(optional = false, fetch = FetchType.LAZY)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<EdicaoEntity> ListaEds;
    
    public List<EdicaoEntity> getListaEds() {
        return ListaEds;
    }

    public void setListaEds(List<EdicaoEntity> ListaEds) {
        this.ListaEds = ListaEds;
    }

    public String getInst_organizadora() {
        return inst_organizadora;
    }

    public void setInst_organizadora(String inst_organizadora) {
        this.inst_organizadora = inst_organizadora;
    }

    public String getArea_concent_evento() {
        return area_concent_evento;
    }

    public void setArea_concent_evento(String area_concent_evento) {
        this.area_concent_evento = area_concent_evento;
    }

    public String getSigla_evento() {
        return sigla_evento;
    }

    public void setSigla_evento(String sigla_evento) {
        this.sigla_evento = sigla_evento;
    }

    public String getNome_evento() {
        return nome_evento;
    }

    public void setNome_evento(String nome_evento) {
        this.nome_evento = nome_evento;
    }

    public Long getId() {
        return id_evento;
    }

    public void setId(Long id) {
        this.id_evento = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_evento != null ? id_evento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventoEntity)) {
            return false;
        }
        EventoEntity other = (EventoEntity) object;
        if ((this.id_evento == null && other.id_evento != null) || (this.id_evento != null && !this.id_evento.equals(other.id_evento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tarefa2.EventoEntity[ id=" + id_evento + " ]";
    }
    public List<EdicaoEntity> getEventos(){
        return this.ListaEds;
    }
    public void  addEvento(EdicaoEntity ee){
        this.ListaEds.add(ee);
    }
    public void  removeEdicao(Long id){
        int removeIndex=0;
        for (Iterator<EdicaoEntity> iterator = ListaEds.iterator(); iterator.hasNext();) {
            EdicaoEntity next = iterator.next();
            if(next.getId()==id){
                ListaEds.remove(removeIndex);
            }            
            removeIndex++;
        }        
    }
    public void  updateEvento(EdicaoEntity ee){
        this.ListaEds.remove(ee.getId());
        this.ListaEds.add(ee);        
    }
}
//FIM DA CLASSE ENTIDADE EVENTO
