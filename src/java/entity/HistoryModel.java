/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import util.Util;

/**
 *
 * @author yuichi_develop
 */
@Entity
@Table(name = "v_history")
@NamedQueries({
    @NamedQuery(name = "History.All", query = "select h from HistoryModel h where h.u_id = ?1  order by h.datetime desc"),
    @NamedQuery(name = "History.HistoryPeriod", query = "select h from HistoryModel h "
            + "where h.u_id = ?1 and h.datetime BETWEEN ?2 and ?3 order by h.datetime desc"),
    @NamedQuery(name = "History.HistoryWord", query = "select h from HistoryModel h "
            + "where h.u_id = ?1 and h.p_name like ?2 order by h.datetime desc"),
})
public class HistoryModel implements Serializable {
    
    @Id
    private String buy_id;
    private String u_id;
    private String u_name;
    private String u_mailaddr;
    private String u_post;
    private String u_address;
    private String u_tel;
    private Date datetime;
    private String count;
    private String price;
    private String p_id;
    private String p_name;
    private Integer p_price;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] p_img;  //*** 画像 ***//
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] p_img2;  //*** 画像2 ***//
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] p_img3;  //*** 画像3 ***//
    private String c_id;

    public String getBuy_id() {
        return String.format("%07d", Integer.valueOf(buy_id));
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
    public String getU_name() {
        return u_name;
    }
    public void setU_name(String u_name) {
        this.u_name = u_name;
    }
    public String getU_mailaddr() {
        return u_mailaddr;
    }
    public void setU_mailaddr(String u_mailaddr) {
        this.u_mailaddr = u_mailaddr;
    }
    public String getU_post() {
        return u_post;
    }
    public void setU_post(String u_post) {
        this.u_post = u_post;
    }
    public String getU_address() {
        return u_address;
    }
    public void setU_address(String u_address) {
        this.u_address = u_address;
    }
    public String getU_tel() {
        return u_tel;
    }
    public void setU_tel(String u_tel) {
        this.u_tel = u_tel;
    }
    public String getDatetime() {
        return Util.parseCalToStr(datetime).split(" ")[0];
    }
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
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
    public Integer getP_price() {
       return p_price;
    }
    public void setP_price(Integer p_price) {
        this.p_price = p_price;
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
    public String getC_id() {
        return c_id;
    }
    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
    
}
