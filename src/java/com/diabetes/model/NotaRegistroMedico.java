/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diabetes.model;

import com.diabetes.database.EM;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Leonardo
 */
@Entity
@SequenceGenerator(sequenceName = "SEQ_NOTA", name = "SEQ_NOTA", initialValue = 1, allocationSize = 1)
public class NotaRegistroMedico {

    @Id
    @GeneratedValue(generator = "SEQ_NOTA", strategy = GenerationType.SEQUENCE)
    private Integer Id;
    private Integer idCelular;
    private String descricao;
    private String sincronizado;
    private String infoRegistro;
    private String codPaciente;

    public NotaRegistroMedico() {
    }

    public NotaRegistroMedico(Integer Id, String descricao, String sincronizado, String infoRegistro, String codPaciente, Integer idCelular) {
        this.Id = Id;
        this.descricao = descricao;
        this.sincronizado = sincronizado;
        this.infoRegistro = infoRegistro;
        this.codPaciente = codPaciente;
        this.idCelular = idCelular;
    }

    public NotaRegistroMedico salvar() {
        EntityManager em = EM.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        NotaRegistroMedico nrm = em.merge(this);
        transaction.commit();
        em.close();
        return nrm;
    }
    
    public NotaRegistroMedico update(NotaRegistroMedico nrm) {
        EntityManager em = EM.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        nrm = em.merge(nrm);
        transaction.commit();
        em.close();
        return nrm;
    }
    
    public List<NotaRegistroMedico> getNotas(String codPac) {
        EntityManager em = EM.getEntityManager();
        List<NotaRegistroMedico> listaNotas = new ArrayList<NotaRegistroMedico>();
        try {
            listaNotas = em.createQuery("select n from NotaRegistroMedico n where n.codPaciente = '" + codPac + "' and n.sincronizado = 'N'", NotaRegistroMedico.class).getResultList();
        } catch (NoResultException e) {
            listaNotas = null;
        } finally {
            em.close();
        }
        return listaNotas;
    }

    /**
     * @return the Id
     */
    public Integer getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the sincronizado
     */
    public String getSincronizado() {
        return sincronizado;
    }

    /**
     * @param sincronizado the sincronizado to set
     */
    public void setSincronizado(String sincronizado) {
        this.sincronizado = sincronizado;
    }

    /**
     * @return the infoRegistro
     */
    public String getInfoRegistro() {
        return infoRegistro;
    }

    /**
     * @param infoRegistro the infoRegistro to set
     */
    public void setInfoRegistro(String infoRegistro) {
        this.infoRegistro = infoRegistro;
    }

    /**
     * @return the codPaciente
     */
    public String getCodPaciente() {
        return codPaciente;
    }

    /**
     * @param codPaciente the codPaciente to set
     */
    public void setCodPaciente(String codPaciente) {
        this.codPaciente = codPaciente;
    }

    /**
     * @return the idCelular
     */
    public Integer getIdCelular() {
        return idCelular;
    }

    /**
     * @param idCelular the idCelular to set
     */
    public void setIdCelular(Integer idCelular) {
        this.idCelular = idCelular;
    }
}