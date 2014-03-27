/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diabetes.model;

import com.diabetes.database.EM;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

/**
 *
 * @author Leonardo
 */
@Entity
@SequenceGenerator(sequenceName = "SEQ_REGISTRO", name = "SEQ_REGISTRO", initialValue = 1, allocationSize = 1)
public class Registro {

    @Id
    @GeneratedValue(generator = "SEQ_REGISTRO", strategy = GenerationType.SEQUENCE)
    private Integer Id;
    private String tipo;
    private String categoria;
    private Float valor;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date datahora;
    private String codPaciente;
    private Integer idCelular;
    private String unidade;
    private String codMedico;
    private String sincronizado;
    private String valorpressao;
    private Integer idMedicamento;

    public Registro(Integer Id, String tipo, String categoria, Float valor,
            Date datahora, String codMedico, String codPaciente, String unidade, Integer idCelular, String sincronizado, String valorpressao, Integer idMedicamento) {
        this.Id = Id;
        this.tipo = tipo;
        this.categoria = categoria;
        this.valor = valor;
        this.datahora = datahora;
        this.codMedico = codMedico;
        this.codPaciente = codPaciente;
        this.unidade = unidade;
        this.idCelular = idCelular;
        this.sincronizado = sincronizado;
        this.idMedicamento = idMedicamento;
        this.valorpressao = valorpressao;

    }

    public Registro salvar() {
        EntityManager em = EM.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Registro r = em.merge(this);
        transaction.commit();
        em.close();
        return r;
    }

    public Registro update(Registro registro) {
        EntityManager em = EM.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        registro = em.merge(registro);
        transaction.commit();
        em.close();
        return registro;
    }

    public Registro() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Timestamp datahora) {
        this.datahora = datahora;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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
     * @return the codMedico
     */
    public String getCodMedico() {
        return codMedico;
    }

    /**
     * @param codMedico the codMedico to set
     */
    public void setCodMedico(String codMedico) {
        this.codMedico = codMedico;
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

    /**
     * @return the unidade
     */
    public String getUnidade() {
        return unidade;
    }

    /**
     * @param unidade the unidade to set
     */
    public void setUnidade(String unidade) {
        this.unidade = unidade;
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

    public List<Registro> getRegistros(String codPac) {
        EntityManager em = EM.getEntityManager();
        List<Registro> listaRegistros = new ArrayList<Registro>();
        try {
            listaRegistros = em.createQuery("select r from Registro r where r.codPaciente = '" + codPac + "' and r.sincronizado = 'N'", Registro.class).getResultList();
        } catch (NoResultException e) {
            listaRegistros = null;
        } finally {
            em.close();
        }
        return listaRegistros;
    }

    /**
     * @return the valorpressao
     */
    public String getValorpressao() {
        return valorpressao;
    }

    /**
     * @param valorpressao the valorpressao to set
     */
    public void setValorpressao(String valorpressao) {
        this.valorpressao = valorpressao;
    }

    /**
     * @return the idMedicamento
     */
    public Integer getIdMedicamento() {
        return idMedicamento;
    }

    /**
     * @param idMedicamento the idMedicamento to set
     */
    public void setIdMedicamento(Integer idMedicamento) {
        this.idMedicamento = idMedicamento;
    }
}
