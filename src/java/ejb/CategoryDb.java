/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.CategoryModel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yuichi_develop
 */
@Stateless
public class CategoryDb {

    @PersistenceContext(unitName = "HIROZONPU")
    private EntityManager em;

    public String getCnameById(String cId) {
        return em.createNamedQuery("Category.getNameById", CategoryModel.class).
                    setParameter(1, cId)
                    .getSingleResult()
                    .getC_name();
    }

}
