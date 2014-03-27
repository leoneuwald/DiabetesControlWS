/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diabetes.model;

import com.diabetes.database.EM;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;

/**
 *
 * @author Leonardo
 */
@Entity
public class Medico {
    @Id
    private String codMedico;
    private String nomeMed;
    private String emailMed;
    private String regMed;
    
    
    public Medico(String nomeMed, String emailMed, String regMed, String codMedico){
        this.nomeMed = nomeMed;
        this.emailMed = emailMed;
        this.regMed = regMed;
        this.codMedico = codMedico;
    }
    
    public Medico(){
        
    }
    
    public Medico salvar() {
        EntityManager em = EM.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Medico med = em.merge(this);
        transaction.commit();
        em.close();
        return med;
    }

    /**
     * @return the nomeMed
     */
    public String getNomeMed() {
        return nomeMed;
    }

    /**
     * @param nomeMed the nomeMed to set
     */
    public void setNomeMed(String nomeMed) {
        this.nomeMed = nomeMed;
    }

    /**
     * @return the emailMed
     */
    public String getEmailMed() {
        return emailMed;
    }

    /**
     * @param emailMed the emailMed to set
     */
    public void setEmailMed(String emailMed) {
        this.emailMed = emailMed;
    }

    /**
     * @return the regMed
     */
    public String getRegMed() {
        return regMed;
    }

    /**
     * @param regMed the regMed to set
     */
    public void setRegMed(String regMed) {
        this.regMed = regMed;
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
 
}
