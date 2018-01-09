/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.RankingModel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author 5151021
 */
@Stateless
public class RankingDb {
    
    @PersistenceContext(unitName = "HIROZONPU")
    private EntityManager em;
    
    //*** ランキングビューの全件データ取得メソッド ***//
    public List<RankingModel> getAll(){
        return em.createNamedQuery("Ranking.All", RankingModel.class).setMaxResults(5).getResultList();
    }
    
}
