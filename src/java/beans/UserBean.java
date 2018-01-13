/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.CartDb;
import ejb.HistoryDb;
import ejb.UserDb;
import ejb.ZipCodeDb;
import entity.BuyHistoryModel;
import entity.CartModel;
import entity.HistoryModel;
import entity.UserModel;
import entity.ZipCodeModel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.context.RequestContext;
import util.Util;

/**
 *
 * @author 5151021
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean extends SuperBean implements Serializable {

    private String u_Id;			//*** ユーザID ***//
    private String u_name;		//*** 氏名 ***//
    private String u_pass;		//*** パスワード（ハッシュ済み） ***//
    @NotEmpty(message = "未入力です！")
    private String u_pass2;
    @Pattern(regexp = "^.*@.*\\..*$|^.*@.*\\..*\\..*$|^.*@.*\\..*\\..*\\..*$", message = "メールアドレスの形式が正しくありません")
    private String u_mailaddr;		//*** メールアドレス ***//
    private String u_post;		//*** 郵便番号 ***//
    private String u_address;		//*** 住所 ***//
    private String u_pre;
    private String u_mansion;
    private String u_tel;			//*** 電話番号 ***//
    private String u_birth_day;		//*** 生年月日 ***//
    private String u_sex;			//*** 性別 ***//
    
    //***  ***//
    private Integer cartCount = 1;
    private String errMsgPassGenerate;
    private String idErrorMsg;
    
    @Inject
    ProductBean productBean;
    @Inject
    EditUserBean editUserBean;
    @Inject
    LoginBean loginBean;
    
    @EJB
    HistoryDb historyDb;
    @EJB
    CartDb cartDb;
    @EJB
    ZipCodeDb codeDb;
    @EJB
    UserDb db;
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    public String getU_Id() {
        return u_Id;
    }
    public void setU_Id(String u_Id) {
        this.u_Id = u_Id;
    }
    public String getU_name() {
        return u_name;
    }
    public void setU_name(String u_name) {
        this.u_name = u_name;
    }
    public String getU_pass() {
        return u_pass;
    }
    public void setU_pass(String u_pass) {
        this.u_pass = u_pass;
    }
    public String getU_pass2() {
        return u_pass2;
    }
    public void setU_pass2(String u_pass2) {
        this.u_pass2 = u_pass2;
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
    public String getU_pre() {
        return u_pre;
    }
    public void setU_pre(String u_pre) {
        this.u_pre = u_pre;
    }
    public String getU_mansion() {
        return u_mansion;
    }
    public void setU_mansion(String u_mansion) {
        this.u_mansion = u_mansion;
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

    public Integer getCartCount() {
        return cartCount;
    }
    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    public String getErrMsgPassGenerate() {
        return errMsgPassGenerate;
    }

    public void setErrMsgPassGenerate(String errMsgPassGenerate) {
        this.errMsgPassGenerate = errMsgPassGenerate;
    }

    public String getIdErrorMsg() {
        return idErrorMsg;
    }

    public void setIdErrorMsg(String idErrorMsg) {
        this.idErrorMsg = idErrorMsg;
    }
    

    @Override
    public String toString() {
        return this.u_Id + " : " + this.u_name;
    }
    
    //*** 自分の購入履歴データを取得するメソッド ***//
    public List<HistoryModel> getMyHistory(){
        return historyDb.getAll();
    }
    
    //*** 自分のカートの中身を取得するメソッド ***//
    public List<CartModel> getMyCart(){
        return cartDb.getAll(u_Id);
    }
    
    //*** カートに商品を追加するメソッド ***//
    public void addMyCart(){
        System.out.println("beans.UserBean.addMyCart()");
        
        //*** すでに商品がカートにあるか検索 ***//
        CartModel cartModel = cartDb.find(u_Id, productBean.getId());
        //  なければカートテーブルに対して新規登録を行う
        if (cartModel == null)
        {
            CartModel cartModel1 = new CartModel();
            cartModel1.setU_id(u_Id);
            cartModel1.setP_id(productBean.getId());
            cartModel1.setCount(cartCount);
            cartModel1.setPrice(productBean.getPrice());
            cartModel1.setDateTime(new Date());
            cartModel1.setExpiration(new Date());
            cartDb.persist(cartModel1);
        } else {
            // でなければ、カートテーブルに対して更新をかける
            cartModel.setCount(cartModel.getCount() + cartCount);
            cartDb.merge(cartModel);
        }
    }
    
    //*** カートの中身を購入するメソッド ***//
    public String buyMyCart(){
        System.out.println("beans.UserBean.buyMyCart()");
        
        // 当該ユーザのカートの中身を取得する
        List<CartModel> list = cartDb.getAll(u_Id);
        if (list != null){
        //  取得したリストを購入履歴テーブルにインサートしていく
            list.forEach(c -> {
                BuyHistoryModel buyHistoryModel = new BuyHistoryModel();
                buyHistoryModel.setU_id(c.getU_id());
                buyHistoryModel.setDatetime(new Date());
                buyHistoryModel.setCount(c.getCount());
                buyHistoryModel.setPrice(c.getPrice());
                buyHistoryModel.setP_id(c.getP_id());

                historyDb.persist(buyHistoryModel);
            });
            
            //  インサートしたあと、当該ユーザのカートの中身を全削除
            deleteCartAll();
            
        }
        return nextTopPage();
    }
    
    //*** 選択した商品をカートから削除するメソッド ***//
    public void deleteCartLine(){
        System.out.println("beans.UserBean.deleteCartLine()");
        
        int rs = cartDb.deleteLine(u_Id, productBean.getId());
    }
    
    //*** カートの全削除を行うメソッド ***//
    public void deleteCartAll(){
        System.out.println("beans.UserBean.deleteCartAll()");
        
        cartDb.deleteAll(u_Id);
    }
    
    //*** Ajax--パスワードとパスワード２が同一か検査するメソッド ***//
    public void ajaxPasswordCheck(){
        System.out.println("beans.UserBean.ajaxPasswordCheck()");
    }
    
    //*** Ajax--郵便番号から住所を検索するメソッド ***//
    public void ajaxSearchZipCode(){
        System.out.println("beans.UserBean.searchZipCode()");
        System.out.println(this.u_post);
        
       codeDb.searchZipInfo(u_post).forEach(d -> {
           this.u_pre = d.getPref();                                         // 県名セット
           this.u_address = d.getCity() + d.getStreet();        // 県名以降の住所をセット
       });
    }
    
    //*** Ajax--ユーザIDの重複をチェックするメソッド ***//
    public void ajaxUserIdCheck(){
        System.out.println("beans.UserBean.ajaxUserIdCheck()");
        System.out.println(this.u_Id);
        
        UserModel um = db.find(u_Id);
        if (um != null){
            this.idErrorMsg = "IDが重複しています";
            return ;
        }
        
        this.idErrorMsg = "";
    }
    
    //*** 新規登録 ***//
    public String addUser() throws NoSuchAlgorithmException{
        System.out.println("beans.UserBean.addUser()");
        UserModel um = new UserModel(
                u_Id,
                u_name, 
                util.Util.returnSHA256(u_pass),     //  ハッシュ化したpass
                u_mailaddr, 
                u_pre + u_address + u_mansion,  //  住所 「県名」＋「住所」＋「番地」 
                u_birth_day, 
                u_post, 
                u_sex, 
                u_tel
        );
        db.persist(um);
        addMessage("Welcome to Primefaces!!");
        return "";
//        return nextIndexPage();
    }
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //*** Beans-->JSへの値渡し reminder.xhtml->handleRequest()参照 ***//
    public void handlePassReminder(ActionEvent actionEvent) throws NoSuchAlgorithmException{
        System.out.println("beans.UserBean.handlePassReminder()");
        RequestContext context = RequestContext.getCurrentInstance();
        
        UserModel um = db.findUser(u_Id);
        boolean flg = true;
        if (um == null){
            errMsgPassGenerate = "そのIDのユーザは存在しません！";
            flg = false;
        } else {
            String pass = Util.getRandomString(5);
            um.setU_pass(Util.returnSHA256(pass));
            System.out.println(pass);
            System.out.println(Util.returnSHA256(pass));
            this.u_pass = pass;
            this.setU_pass(pass);
//            loginBean.setPass(pass);
            
            db.merge(um);
        }
        
        context.addCallbackParam("showFlag", flg);
    }
    
}
