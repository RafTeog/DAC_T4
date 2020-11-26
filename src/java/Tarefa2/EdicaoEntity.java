//INICIO DA CLASSE ENTIDADE EDICAO
package Tarefa2;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RAF
 */
@Entity
@Table
@XmlRootElement
public class EdicaoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_edicao;
    @Column
    @Basic(optional=false,fetch=FetchType.LAZY)
    private Long id_evento;
    @Column
    @Basic(optional=false,fetch=FetchType.LAZY)
    private int ano_edicao;
    @Column
    @Basic(optional=false,fetch=FetchType.LAZY)
    private int numero_edicao;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @Basic(optional=false,fetch=FetchType.LAZY)
    private String dataInicio_edicao;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Basic(optional=false,fetch=FetchType.LAZY)
    private String datafim_edicao;
    @Column
    @Basic(optional=false,fetch=FetchType.LAZY)
    private String cidade_edicao;
    @Column
    @Basic(optional=false,fetch=FetchType.LAZY)
    private String pais_edicao;

    public String getPais_edicao() {
        return pais_edicao;
    }

    public void setPais_edicao(String pais_edicao) {
        this.pais_edicao = pais_edicao;
    }
    
    

    public String getCidade_edicao() {
        return cidade_edicao;
    }

    public void setCidade_edicao(String cidade_edicao) {
        this.cidade_edicao = cidade_edicao;
    }
    
    

    public String getDatafim_edicao() {
        return datafim_edicao;
    }

    
    public void setDatafim_edicao(String datafim_edicao) {
        this.datafim_edicao = datafim_edicao;
    }
    
    
    public String getDataInicio_edicao() {
        return dataInicio_edicao;
    }

    public void setDataInicio_edicao(String dataInicio_edicao) {
        this.dataInicio_edicao = dataInicio_edicao;
    }
    
    
    
    public int getNumero_edicao() {
        return numero_edicao;
    }

    public void setNumero_edicao(int numero_edicao) {
        this.numero_edicao = numero_edicao;
    }
    
    
    public int getAno_edicao() {
        return ano_edicao;
    }

    public void setAno_edicao(int ano_edicao) {
        this.ano_edicao = ano_edicao;
    }
    
    
    
    
    public Long getId_evento() {
        return id_evento;
    }

    public void setId_evento(Long id_evento) {
        this.id_evento = id_evento;
    }
    
    
    

    public Long getId() {
        return id_edicao;
    }

    public void setId(Long id) {
        this.id_edicao = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_edicao != null ? id_edicao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdicaoEntity)) {
            return false;
        }
        EdicaoEntity other = (EdicaoEntity) object;
        if ((this.id_edicao == null && other.id_edicao != null) || (this.id_edicao != null && !this.id_edicao.equals(other.id_edicao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tarefa2.EdicaoEntity[ id=" + id_edicao + " ]";
    }
    
}
//FIM DA CLASSE ENTIDADE EDICAO
