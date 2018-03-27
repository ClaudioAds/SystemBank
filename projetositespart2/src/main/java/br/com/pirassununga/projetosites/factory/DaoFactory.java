/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.factory;

/**
 *
 * @author Joseph
 */
public class DaoFactory {
    
    public static final int DAO_BD = 0;

    public static DaoFactoryIf createFactory(int factoryType) {
        if (factoryType == DAO_BD){
            return new DaoFactoryBD();
        }
        return null;
    }
}