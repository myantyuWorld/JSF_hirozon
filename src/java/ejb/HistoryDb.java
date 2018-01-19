/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.BuyHistoryModel;
import entity.CartModel;
import entity.HistoryModel;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 5151021
 */
@Stateless
public class HistoryDb {
    
    @PersistenceContext(unitName = "HIROZONPU")
    private EntityManager em;
    
    //*** 購入履歴全件取得 ***//
    public List<HistoryModel> getAll(String uId){
        return em.createNamedQuery("History.All", HistoryModel.class)
//            .setMaxResults(10)
            .setParameter(1, uId)           // userId
            .getResultList();
    }
    
    //*** 購入履歴 期間指定取得 ***//
    public List<HistoryModel> popHistoryPeriod(String uId, Date start, Date end){
        return em.createNamedQuery("History.HistoryPeriod", HistoryModel.class)
                .setParameter(1, uId)       // userId
                .setParameter(2, end)      // endDay
                .setParameter(3, start)     // startDay
                .getResultList();
    }
    
    //*** 新規登録 ***//
    public void persist(BuyHistoryModel buyHistoryModel){
        em.persist(buyHistoryModel);
    }
    
    
}
