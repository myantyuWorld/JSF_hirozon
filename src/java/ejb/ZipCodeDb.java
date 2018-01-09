/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.ZipCodeModel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yuichi_develop
 */
@Stateless
public class ZipCodeDb {
    
    @PersistenceContext(unitName = "HIROZONPU")
    private EntityManager em;
    
    //*** 郵便番号から住所テーブルを検索して結果を返すメソッド ***//
    public List<ZipCodeModel> searchZipInfo(String zipCode){
        System.out.println("ejb.ZipCodeDb.searchZipInfo()");
        return em.createNamedQuery("ZipCode.findByCode", ZipCodeModel.class).setParameter(1, zipCode).getResultList();
    }
}
