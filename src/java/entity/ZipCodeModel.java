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
@Table(name = "zipcode")
@NamedQueries({
    @NamedQuery(name = "ZipCode.findByCode", query = "select z from ZipCodeModel z where z.zipcode = ?1"),
})
public class ZipCodeModel implements Serializable{
    //*** Field ***//
    @Id
    private String id;          
    private String jiscode;
    private String zipcode_old;
    private String zipcode;
    private String pref_kana;
    private String city_kana;
    private String street_kana;
    private String pref;
    private String city;
    private String street;
    private int flag1;
    private int flag2;
    private int flag3;
    private int flag4;
    private int flag5;
    private int flag6;

    public ZipCodeModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJiscode() {
        return jiscode;
    }

    public void setJiscode(String jiscode) {
        this.jiscode = jiscode;
    }

    public String getZipcode_old() {
        return zipcode_old;
    }

    public void setZipcode_old(String zipcode_old) {
        this.zipcode_old = zipcode_old;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPref_kana() {
        return pref_kana;
    }

    public void setPref_kana(String pref_kana) {
        this.pref_kana = pref_kana;
    }

    public String getCity_kana() {
        return city_kana;
    }

    public void setCity_kana(String city_kana) {
        this.city_kana = city_kana;
    }

    public String getStreet_kana() {
        return street_kana;
    }

    public void setStreet_kana(String street_kana) {
        this.street_kana = street_kana;
    }

    public String getPref() {
        return pref;
    }

    public void setPref(String pref) {
        this.pref = pref;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getFlag1() {
        return flag1;
    }

    public void setFlag1(int flag1) {
        this.flag1 = flag1;
    }

    public int getFlag2() {
        return flag2;
    }

    public void setFlag2(int flag2) {
        this.flag2 = flag2;
    }

    public int getFlag3() {
        return flag3;
    }

    public void setFlag3(int flag3) {
        this.flag3 = flag3;
    }

    public int getFlag4() {
        return flag4;
    }

    public void setFlag4(int flag4) {
        this.flag4 = flag4;
    }

    public int getFlag5() {
        return flag5;
    }

    public void setFlag5(int flag5) {
        this.flag5 = flag5;
    }

    public int getFlag6() {
        return flag6;
    }

    public void setFlag6(int flag6) {
        this.flag6 = flag6;
    }
}
