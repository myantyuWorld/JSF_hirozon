/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author yuichi_develop
 */
@Entity
@Table(name = "t_buy_history")
public class BuyHistoryModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String buy_id;
    private String u_id;
    private Date datetime;
    private Integer count;
    private Integer price;
    private String p_id;

    public String getBuy_id() {
        return buy_id;
    }
    public void setBuy_id(String buy_id) {
        this.buy_id = buy_id;
    }
    public String getU_id() {
        return u_id;
    }
    public void setU_id(String u_id) {
        this.u_id = u_id;
    }
    public Date getDatetime() {
        return datetime;
    }
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }
    
    
    
    
}
