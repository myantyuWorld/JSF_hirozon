/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.CartModel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author 5151021
 */
@Stateless
public class CartDb {
    
    @PersistenceContext(unitName = "HIROZONPU")
    private EntityManager em;
    
    public List<CartModel> getAll(String uId){
        return em.createNamedQuery("Cart.All", CartModel.class)
            .setParameter(1, uId)
            .getResultList();
    }
    
    //***  ***//
    public CartModel find(String uId, String pId){
        System.out.println("ejb.CartDb.find()");
        System.out.println(uId);
        System.out.println(pId);
           TypedQuery<CartModel> query = em.createNamedQuery("Cart.findSameProduct", CartModel.class)
                .setParameter(1, uId)
                .setParameter(2, pId);
                
           try {
                return query.getSingleResult();
           } catch (NoResultException e){
               return null;
           }
    }
    
    public void merge(CartModel cartModel){
        em.merge(cartModel);
    }
    
    //*** カートテーブル新規登録用 ***//
    public void persist(CartModel data){
        em.persist(data);
    }
    
    //*** 当該ユーザのカート情報を削除するメソッド ***//
    public int deleteAll(String uId){
        return em.createNamedQuery("Cart.DeleteAll", CartModel.class)
                .setParameter(1, uId)
                .executeUpdate();
    }
    
    //*** 当該ユーザの特定一行の商品情報を削除するメソッド ***//
    public int deleteLine(String uId, String pId){
        return em.createNamedQuery("Cart.DeleteLine", CartModel.class)
                .setParameter(1, uId)
                .setParameter(2, pId)
                .executeUpdate();
    }
    
}
