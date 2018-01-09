/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author yuichi_develop
 */
@Entity
@Table(name = "m_category")
@NamedQueries({
    @NamedQuery(name = "Category.getNameById", query = "select c from CategoryModel c where c.c_id = ?1" ),
})
public class CategoryModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    private String c_id;
    private String c_name;
    
    public String getC_id() {
        return c_id;
    }
    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
    public String getC_name() {
        return c_name;
    }
    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
}
