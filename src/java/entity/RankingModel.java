/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author yuichi_develop
 */
@Entity
@Table(name = "v_ranking")
@NamedQueries({
    @NamedQuery(name = "Ranking.All", query = "select r from RankingModel r"),
})
public class RankingModel implements Serializable {
    //*** Filed ***//
    @Id
    private String p_id;
    private String p_name;
    private String p_price;
    private Integer cnt;
    private Integer price;
    private String c_id;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] p_img;  //*** 画像 ***//
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] p_img2;  //*** 画像2 ***//
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] p_img3;  //*** 画像3 ***//
    
    //*** Constractor ***//
    public RankingModel() {
    }
    public RankingModel(String p_id, String p_name, String p_price, Integer cnt, Integer price) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_price = p_price;
        this.cnt = cnt;
        this.price = price;
    }
    //*** GetterSetter ***//
    public String getP_id() {
        return p_id;
    }
    public void setP_id(String p_id) {
        this.p_id = p_id;
    }
    public String getP_name() {
        return p_name;
    }
    public void setP_name(String p_name) {
        this.p_name = p_name;
    }
    public String getP_price() {
        return p_price;
    }
    public void setP_price(String p_price) {
        this.p_price = p_price;
    }
    public String getC_id() {
        return c_id;
    }
    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
    public Integer getCnt() {
        return cnt;
    }
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public byte[] getP_img() {
        return p_img;
    }
    public void setP_img(byte[] p_img) {
        this.p_img = p_img;
    }
    public byte[] getP_img2() {
        return p_img2;
    }
    public void setP_img2(byte[] p_img2) {
        this.p_img2 = p_img2;
    }
    public byte[] getP_img3() {
        return p_img3;
    }
    public void setP_img3(byte[] p_img3) {
        this.p_img3 = p_img3;
    }
    
}
