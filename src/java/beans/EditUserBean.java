/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.UserDb;
import ejb.ZipCodeDb;
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
    private String newPost;
    private String newPre;
    private String newAddress;
    private String newMansion;

    public String getNewPre() {
        return newPre;
    }

    public void setNewPre(String newPre) {
        this.newPre = newPre;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    public String getNewMansion() {
        return newMansion;
    }

    public void setNewMansion(String newMansion) {
        this.newMansion = newMansion;
    }
    
    

    public String getNewPost() {
        return newPost;
    }

    public void setNewPost(String newPost) {
        this.newPost = newPost;
    }
    
    @Inject
    UserBean userBean;
    
    @EJB
    UserDb userDb;
    
    @EJB
    ZipCodeDb codeDb;
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
            
            addMessage("電話番号の変更が完了しました");
            userBean.setU_tel(newTel);
            newTel = "";
            
            return;
        }
        addMessage("変更できませんでした");
    }
    
    //*** 新しいパスワードで当該ユーザ情報を更新するメソッド ***//
    public void updatePassword() throws NoSuchAlgorithmException{
        System.out.println("beans.EditUserBean.updatePassword()");
        System.out.println(newPassword);
        UserModel um = userDb.findUser(userBean.getU_Id());
        if (um != null){
            um.setU_pass(util.Util.returnSHA256(newPassword));
            userDb.merge(um);
            
            addMessage("パスワードの変更が完了しました");
            newPassword = "";
            
            return;
        }
        addMessage("変更できませんでした");
    }
    
    //***  ***//
    public void updatePostAddr() {
        System.out.println("beans.EditUserBean.updatePostAddr()");
        
        UserModel um = userDb.findUser(userBean.getU_Id());
        if (um != null){
            um.setU_post(newPost);
            um.setU_address(newPre + newAddress + newMansion);
            userDb.merge(um);
            
            userBean.setU_post(newPost);
            userBean.setU_address(newPre + newAddress + newMansion);
            addMessage("住所変更が完了しました");
            newPost = "";
            newPre = "";
            newAddress = "";
            newMansion = "";
            
            return ;
        }
        addMessage("変更できませんでした");
    }
    
    //*** Ajax--郵便番号から住所を検索するメソッド ***//
    public void ajaxSearchZipCode() {
        System.out.println("beans.EditUserBean.ajaxSearchZipCode()");
        System.out.println(newPost);

        codeDb.searchZipInfo(newPost).forEach(d -> {
            this.newPre = d.getPref();                                         // 県名セット
            this.newAddress = d.getCity() + d.getStreet();        // 県名以降の住所をセット
        });
    }
    
    //*** 退会用メソッド ***//
    public String unsubscribe(){
        System.out.println("beans.EditUserBean.unsubscribe()");
//        UserModel um = userDb.loginCheck(userBean.getU_Id(), loginPass);
        UserModel um = userDb.findUser(userBean.getU_Id());
        if (um != null){
            // 退会ー＞当該ユーザの削除を実施
            userDb.unsubscribe(um);
            
            addMessage("退会処理が完了しました");
            nowMailAddr = "";
            loginPass = "";
            return "index.xhtml?faces-redirect=true";
        }
        addMessage("エラーが発生しました");
        
        return "";
    }
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        System.out.println(FacesContext.getCurrentInstance().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
}
