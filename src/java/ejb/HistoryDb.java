/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.BuyHistoryModel;
import entity.CartModel;
import entity.HistoryModel;
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
    
    public List<HistoryModel> getAll(){
        return em.createNamedQuery("History.All", HistoryModel.class)
            .setMaxResults(10)
            .getResultList();
    }
    
    public void persist(BuyHistoryModel buyHistoryModel){
        em.persist(buyHistoryModel);
    }
    
    
}
