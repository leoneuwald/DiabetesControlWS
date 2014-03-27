/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diabetes.database;

import javax.persistence.EntityManager;

/**
 *
 * @author Leonardo
 */
public class EM {

    public static EntityManager getEntityManager() {
        return EMF.get().createEntityManager();
    }
}
