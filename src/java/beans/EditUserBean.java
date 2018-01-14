/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.UserDb;
import entity.UserModel;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *  アカウント編集に使用するUserBeanの代わり・照合用クラス
 * @author yuichi_develop
 */
@Named(value = "editUserBean")
@SessionScoped
public class EditUserBean implements Serializable{

    private String nowName;
    @NotEmpty(message = "空欄です")
    private String newName;
    private String nowMailAddr;
    @Pattern(regexp = "^.*@.*\\..*$|^.*@.*\\..*\\..*$|^.*@.*\\..*\\..*\\..*$", message = "メールアドレスの形式が正しくありません")
    private String newMailAddr;
    private String nowTel;
    private String newTel;
    private String nowPassword;
    @NotEmpty(message = "パスワード 未入力です！")
    private String newPassword;
    private String newPassword2;
    private String loginId;
    private String loginPass;
    
    @Inject
    UserBean userBean;
    
    @EJB
    UserDb userDb;
    /**
     * Creates a new instance of EditUserBean
     */
    public EditUserBean() {
    }

    public String getNowName() {
        return nowName;
    }
    public void setNowName(String nowName) {
        this.nowName = nowName;
    }
    public String getNewName() {
        return newName;
    }
    public void setNewName(String newName) {
        this.newName = newName;
    }
    public String getNowMailAddr() {
        return nowMailAddr;
    }
    public void setNowMailAddr(String nowMailAddr) {
        this.nowMailAddr = nowMailAddr;
    }
    public String getNewMailAddr() {
        return newMailAddr;
    }
    public void setNewMailAddr(String newMailAddr) {
        this.newMailAddr = newMailAddr;
    }
    public String getNowTel() {
        return nowTel;
    }
    public void setNowTel(String nowTel) {
        this.nowTel = nowTel;
    }
    public String getNewTel() {
        return newTel;
    }
    public void setNewTel(String newTel) {
        this.newTel = newTel;
    }
    public String getNowPassword() {
        return nowPassword;
    }
    public void setNowPassword(String nowPassword) {
        this.nowPassword = nowPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getNewPassword2() {
        return newPassword2;
    }
    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }
    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getLoginPass() {
        return loginPass;
    }
    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }
    
    //*** 新しい名前で当該ユーザ情報を更新するメソッド ***//
    public void updateName(){
        System.out.println("beans.EditUserBean.updateName()");
        UserModel um = userDb.findUser(userBean.getU_Id());
        if (um != null){
            um.setU_name(newName);
            userDb.merge(um);
            
            addMessage("名前の変更が完了しました");
            userBean.setU_name(newName);
            newName = "";
            
            return;
        }
        addMessage("変更できませんでした");
    }
    
    //*** 新しいメールアドレスで当該ユーザ情報を更新するメソッド ***//
    public void updateMailAddr(){
        System.out.println("beans.EditUserBean.updateMailAddr()");
        UserModel um = userDb.findUser(userBean.getU_Id());
        if (um != null){
            um.setU_mailaddr(newMailAddr);
            userDb.merge(um);
            
            addMessage("メールアドレスの変更が完了しました");
            userBean.setU_mailaddr(newMailAddr);
            newMailAddr = "";
            
            return;
        }
        addMessage("変更できませんでした");
    }
    
    //*** 新しい電話番号で当該ユーザ情報を更新するメソッド ***//
    public void updateTel(){
        System.out.println("");
        UserModel um = userDb.findUser(userBean.getU_Id());
        if (um != null){
            um.setU_tel(newTel);
            userDb.merge(um);
        }
    }
    
    //*** 新しいパスワードで当該ユーザ情報を更新するメソッド ***//
    public void updatePassword() throws NoSuchAlgorithmException{
        System.out.println("beans.EditUserBean.updatePassword()");
        System.out.println(newPassword);
        UserModel um = userDb.findUser(userBean.getU_Id());
        if (um != null){
            um.setU_pass(util.Util.returnSHA256(newPassword));
            userDb.merge(um);
        }
    }
    
    
    //*** 退会用メソッド ***//
    public void unsubscribe(){
        System.out.println("beans.EditUserBean.unsubscribe()");
        UserModel um = userDb.loginCheck(loginId, loginPass);
        if (um != null){
            // 退会ー＞当該ユーザの削除を実施
            userDb.unsubscribe(um);
        }
    }
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
}
