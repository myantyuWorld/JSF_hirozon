/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.ProductModel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author 5151021
 * 商品テーブル操作用クラス
 */
@Stateless
public class ProductDb {
    
    @PersistenceContext(unitName = "HIROZONPU")
    private EntityManager em;
    
    public ProductModel find(String pId){
        return em.find(ProductModel.class, pId);
    }
    
    //***  ***//
    public List<ProductModel> getAll(){
        return em.createNamedQuery("Product.All", ProductModel.class)
                .setMaxResults(10)
                .getResultList();
    }
    
    //***  ***//
    public List<ProductModel> getProductByCategory(String cId){
        return em.createNamedQuery("Product.byCategory", ProductModel.class)
                .setParameter(1, cId)
                .setMaxResults(8)
                .getResultList();
    }
    
    //***  ***//
    public List<ProductModel> searchProductByWord(String cId, String word){
        return em.createNamedQuery("Product.bySearchWord", ProductModel.class)
                .setParameter("c_id", cId)
                .setParameter("word", "%" + word + "%")
                .getResultList();
    }
    
    //***  ***//
    public String getProductName(String pId){
        return em.find(ProductModel.class, pId).getP_name();
    }
}
