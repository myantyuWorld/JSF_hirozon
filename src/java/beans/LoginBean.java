/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.UserDb;
import entity.UserModel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import util.Util;

/**
 *
 * @author yuichi_develop
 */
@Named(value = "lBean")
@RequestScoped
public class LoginBean extends SuperBean implements Serializable {

    private String id;
    private String pass;

    @EJB
    UserDb db;
    
    @Inject
    UserBean ub;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    //*** ログイン ***//
    public String loginCheck() throws NoSuchAlgorithmException {
        System.out.println("call public String loginCheck() throws NoSuchAlgorithmException");
        System.out.println(pass);
        
        UserModel um = db.loginCheck(id, Util.returnSHA256(pass));
        if (um != null) {
            // DBから取得したユーザ情報をセットする
            ub.setU_Id(um.getU_id());
            ub.setU_name(um.getU_name());
            ub.setU_address(um.getU_address());
            ub.setU_birth_day(um.getU_birth_day());
            ub.setU_mailaddr(um.getU_mailaddr());
            ub.setU_post(um.getU_post());
            ub.setU_sex(um.getU_sex());
            ub.setU_tel(um.getU_tel());
            
            System.out.println(ub.toString());
            return nextTopPage();   //  ログイン成功なので、トップページへ遷移する
        } else {
            return null;            //  失敗の場合は、ページそのまま
        }
    }

    //*** ログアウト ***//
    public String logout() {
        System.out.println("call public String logout()");
        // ID PASSをnullに設定してログアウトさせる
        this.id = null;     //  nullを代入
        this.pass = null;   //  nullを代入
        
        return nextIndexPage();
    }

}
