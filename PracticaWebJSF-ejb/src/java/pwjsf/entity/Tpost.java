/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwjsf.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "TPOST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tpost.findAll", query = "SELECT t FROM Tpost t"),
    @NamedQuery(name = "Tpost.findById", query = "SELECT t FROM Tpost t WHERE t.id = :id"),
    @NamedQuery(name = "Tpost.findByTexto", query = "SELECT t FROM Tpost t WHERE t.texto = :texto"),
    @NamedQuery(name = "Tpost.findByImagen", query = "SELECT t FROM Tpost t WHERE t.imagen = :imagen")})
public class Tpost implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "secuencia_post")
    @SequenceGenerator(name="secuencia_post", sequenceName = "SEQ_POST", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 50)
    @Column(name = "TEXTO")
    private String texto;
    @Size(max = 200)
    @Column(name = "IMAGEN")
    private String imagen;
    @JoinColumn(name = "TUSUARIO_ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Tusuario tusuarioIdUser;

    public Tpost() {
    }

    public Tpost(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Tusuario getTusuarioIdUser() {
        return tusuarioIdUser;
    }

    public void setTusuarioIdUser(Tusuario tusuarioIdUser) {
        this.tusuarioIdUser = tusuarioIdUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpost)) {
            return false;
        }
        Tpost other = (Tpost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entity.Tpost[ id=" + id + " ]";
    }
    
}
