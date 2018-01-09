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
@Table(name = "m_user")
@NamedQueries({
    //*** ログインチェック ***//
    @NamedQuery(name = "User.loginCheck", query = "select u from UserModel u where u.u_Id = ?1 and u.u_pass = ?2"),
    //***  ***//
    @NamedQuery(name = "User.findUser", query = "select u from UserModel u where u.u_Id = ?1"),
    //*** 退会用ユーザ削除メソッド ***//
    @NamedQuery(name = "User.unsubscribe", query = "select u from UserModel u where u.u_Id = ?1 and u.u_pass = ?2"),
})
public class UserModel implements Serializable {

    //*** Field ***//
    private static final long serialVersionUID = 1L;
    @Id
    private String u_Id;			//*** ユーザID ***//
    private String u_name;			//*** 氏名 ***//
    private String u_pass;			//*** パスワード（ハッシュ済み） ***//
    private String u_mailaddr;		//*** メールアドレス ***//
    private String u_post;			//*** 郵便番号 ***//
    private String u_address;		//*** 住所 ***//
    private String u_tel;			//*** 電話番号 ***//
    private String u_birth_day;		//*** 生年月日 ***//
    private String u_sex;			//*** 性別 ***//

    //*** Constractor ***//
    public UserModel() {
    }
    public UserModel(String id, String u_name, String pass, String u_mailaddr, String u_address, String u_birth_day, String u_post, String u_sex, String u_tel) {
        this.u_Id = id;
        this.u_name = u_name;
        this.u_pass = pass;
        this.u_mailaddr = u_mailaddr;
        this.u_post = u_post;
        this.u_address = u_address;
        this.u_tel = u_tel;
        this.u_birth_day = u_birth_day;
        this.u_sex = u_sex;
    }

    //*** GetterSetter ***//
    public String getU_id() {
        return u_Id;
    }
    public void setU_id(String u_id) {
        this.u_Id = u_id;
    }
    public String getU_pass() {
        return u_pass;
    }
    public void setU_pass(String u_pass) {
        this.u_pass = u_pass;
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
    public String getU_birth_day() {
        return u_birth_day;
    }
    public void setU_birth_day(String u_birth_day) {
        this.u_birth_day = u_birth_day;
    }
    public String getU_sex() {
        return u_sex;
    }
    public void setU_sex(String u_sex) {
        this.u_sex = u_sex;
    }

    @Override
    public String toString() {
        return String.format("ユーザID : %s ユーザ氏名 : %s", u_Id, u_name);
    }

}
