/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diabetes.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Leonardo
 */
public class EMF {

    private static EntityManagerFactory emfInstance;

    private EMF() {
    }

    public static EntityManagerFactory get() {
        if (emfInstance == null) {
            emfInstance = Persistence.createEntityManagerFactory("diabetes");
        }
        return emfInstance;
    }
}
