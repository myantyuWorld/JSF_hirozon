/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author yuichi_develop
 * すべての画面遷移をもつ
 */
public class SuperBean implements Serializable{

    /**
     * Creates a new instance of SuperBean
     */
    public SuperBean() {
    }
    
    //*** ログインページへの画面遷移メソッド ***//
    public String nextIndexPage(){
        System.out.println("call public String nextIndexPage()");
        return "index.xhtml?faces-redirect=true";
    }
    
    //*** トップページへの画面遷移メソッド ***//
    public String nextTopPage(){
        System.out.println("call public String nextTopPage()");
        return "top.xhtml?faces-redirect=true";
    }
    
    //*** マイページへの画面遷移メソッド ***//
    public String nextMyPage(){
        System.out.println("call public String nextMyPage()");
        return "mypage?faces-redirect=true";
    }
    
    //*** 商品詳細ページへの画面遷移メソッド ***//
    public String nextProductDetail(){
        System.out.println("call public String nextProductDetail()");
        
        
        return "product_detail.xhtml?faces-redirect=true";
    }
    
    //*** アカウント編集ページへの画面遷移メソッド ***//
    public String nextAccount(){
        System.out.println("beans.SuperBean.nextAccount()");
        return "account.xhtml?faces-redirect=true";
    }
    
    //*** 自分の購入履歴ページへの画面遷移メソッド ***//
    public String nextMyHistory(){
        System.out.println("beans.SuperBean.nextMyHistory()");
        return "history.xhtml?faces-redirect=true";
    }
    
    //*** 商品検索結果ページへの画面遷移メソッド ***//
    public String nextSearchResult(){
        return "search.xhtml?faces-redirect=true";
    }
    
    //***  ***//
    public String nextMemberPage(){
        System.out.println("beans.SuperBean.nextMemberPage()");
        return "__member.xhtml?faces-redirect=true";
    }
    
    //*** 日本円表示とするメソッド ***//
    public String convertJapanPrice(int price){
        return "￥" + NumberFormat.getInstance(Locale.JAPAN).format(price);
    }
}
