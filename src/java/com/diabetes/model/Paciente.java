/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diabetes.model;

import com.diabetes.database.EM;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Temporal;

/**
 *
 * @author Leonardo
 */
@Entity
public class Paciente {

    @Id
    private String codPac;
    private String nomePac;
    private String emailPac;
    private String sexoPac;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date nascimentoPac;
    private String senhaPac;

    public Paciente(String codPac, String nomePac, String sexoPac, String senhaPac, String emailPac, Date nascimentoPac) {
        this.codPac = codPac;
        this.emailPac = emailPac;
        this.nomePac = nomePac;
        this.sexoPac = sexoPac;
        this.senhaPac = senhaPac;
        this.nascimentoPac = nascimentoPac;
    }

    public Paciente() {
    }

    public Paciente salvar() {
        EntityManager em = EM.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Paciente pac = em.merge(this);
        transaction.commit();
        em.close();
        return pac;
    }

    public Paciente getPacienteCodSenha(String codPac, String senhaPac) {
        EntityManager em = EM.getEntityManager();
        Paciente pac = null;
        try {
            pac = em.createQuery("select p from Paciente p where p.codPac = '" + codPac + "' and p.senhaPac = '" + senhaPac + "'", Paciente.class).getSingleResult();
        } catch (NoResultException e) {
            pac = null;
        } finally {
            em.close();
        }

        return pac;
    }

    public Paciente getPaciente(String codPac) {
        Paciente pac = new Paciente();
        try {
            EntityManager em = EM.getEntityManager();
            pac = em.createQuery("select p from Paciente p where p.codPac = '" + codPac + "'", Paciente.class).getSingleResult();
            em.close();
        } catch (NoResultException e) {
            return null;
        }
        return pac;
    }

    /**
     * @return the nomePac
     */
    public String getNomePac() {
        return nomePac;
    }

    /**
     * @param nomePac the nomePac to set
     */
    public void setNomePac(String nomePac) {
        this.nomePac = nomePac;
    }

    /**
     * @return the emailPac
     */
    public String getEmailPac() {
        return emailPac;
    }

    /**
     * @param emailPac the emailPac to set
     */
    public void setEmailPac(String emailPac) {
        this.emailPac = emailPac;
    }

    /**
     * @return the codPaciente
     */
    public String getCodPac() {
        return codPac;
    }

    /**
     * @param codPaciente the codPaciente to set
     */
    public void setCodPac(String codPac) {
        this.codPac = codPac;
    }

    /**
     * @return the sexoPac
     */
    public String getSexoPac() {
        return sexoPac;
    }

    /**
     * @param sexoPac the sexoPac to set
     */
    public void setSexoPac(String sexoPac) {
        this.sexoPac = sexoPac;
    }

    /**
     * @return the nascimentoPac
     */
    public Date getNascimentoPac() {
        return nascimentoPac;
    }

    /**
     * @param nascimentoPac the nascimentoPac to set
     */
    public void setNascimentoPac(Date nascimentoPac) {
        this.nascimentoPac = nascimentoPac;
    }

    /**
     * @return the senhaPac
     */
    public String getSenhaPac() {
        return senhaPac;
    }

    /**
     * @param senhaPac the senhaPac to set
     */
    public void setSenhaPac(String senhaPac) {
        this.senhaPac = senhaPac;
    }
}
