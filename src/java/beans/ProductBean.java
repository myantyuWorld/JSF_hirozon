/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.ProductDb;
import ejb.RankingDb;
import entity.ProductModel;
import entity.RankingModel;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.Part;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author 5151021
 */
@Named(value = "productBean")
@SessionScoped
public class ProductBean extends SuperBean implements Serializable{

    private String id;
    private String name;
    private int count;
    private int price;
    private Part picture;   //*** 画像1 ***//
    private Part picture2;   //*** 画像2 ***//
    private Part picture3;   //*** 画像3 ***//
    private String c_id;	//*** カテゴリID ***//
    
    private String searchWord; // 検索用
    private List<ProductModel> resultList;
    @EJB
    ProductDb db;
    @EJB
    RankingDb rankingDb;
    
    /**
     * Creates a new instance of ProductBean
     */
    public ProductBean() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Part getPicture() {
        return picture;
    }
    public void setPicture(Part picture) {
        this.picture = picture;
    }
    public Part getPicture2() {
        return picture2;
    }
    public void setPicture2(Part picture2) {
        this.picture2 = picture2;
    }
    public Part getPicture3() {
        return picture3;
    }
    public void setPicture3(Part picture3) {
        this.picture3 = picture3;
    }
    public String getC_id() {
        return c_id;
    }
    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
    public String getSearchWord() {
        return searchWord;
    }
    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
    public List<ProductModel> getResultList() {
        return resultList;
    }
    public void setResultList(List<ProductModel> resultList) {
        this.resultList = resultList;
    }
    
    //*** 以降 SELF MADE METHOD ------------------- ***//
    
    //*** 商品テーブルの全件データ取得メソッド ***//
    public List<ProductModel> getAll(){
        return db.getAll();
    }
    
    //*** 商品テーブルのカテゴリ指定によるデータ取得メソッド ***//
    public List<ProductModel> getProductByCategory(String cId){
        return db.getProductByCategory(cId);
    }
    
    public List<ProductModel> getProductByBook(){
        return getProductByCategory("0009");
    }
    
    public List<ProductModel> getProductByElectric(){
        return getProductByCategory("0001");
    }
    
    public List<ProductModel> getProductByFoods(){
        return getProductByCategory("0002");
    }
    
    public List<ProductModel> getProductByMedichine() {
        return getProductByCategory("0006");
    }
    
    public List<ProductModel> getProductBySameCategories(){
        return getProductByCategory(c_id);
    }
    
    //*** ランキングテーブルの全件データ取得メソッド ***//
    public List<RankingModel> getRankingAll(){
        return rankingDb.getAll();
    }
    
    //*** 画像取得用メソッド ***//
    public StreamedContent getPic1() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            ExternalContext sv = context.getExternalContext();
            Map<String, String> map = sv.getRequestParameterMap();
            String key = map.get("pic1");	//*** ここの、指定方法を調べること ***//
            System.out.println(String.format("key : %s", key));
            DefaultStreamedContent ds = null;

            if (key != null) {
                ProductModel p = db.find(key);
                ByteArrayInputStream out = new ByteArrayInputStream(p.getP_img());
                ds = new DefaultStreamedContent(out);
            }
            return ds;
        } 
    }
    
    //*** 画像取得用メソッド ***//
    public StreamedContent getPic2() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            ExternalContext sv = context.getExternalContext();
            Map<String, String> map = sv.getRequestParameterMap();
            String key = map.get("pic2");	//*** ここの、指定方法を調べること ***//
            System.out.println(key);
            DefaultStreamedContent ds = null;

            if (key != null) {
//                Product p = db.find(key);
////                ByteArrayInputStream out = new ByteArrayInputStream(p.getP_img2());
//                ds = new DefaultStreamedContent(out);
            }
            return ds;
        }
    }
    
    //*** 画像取得用メソッド ***//
    public StreamedContent getPic3() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            ExternalContext sv = context.getExternalContext();
            Map<String, String> map = sv.getRequestParameterMap();
            String key = map.get("pic3");	//*** ここの、指定方法を調べること ***//
            System.out.println(key);
            DefaultStreamedContent ds = null;

            if (key != null) {
//                Product p = db.find(key);
//                ByteArrayInputStream out = new ByteArrayInputStream(p.getP_img3());
//                ds = new DefaultStreamedContent(out);
            }
            return ds;
        }
    }

    public String nextProductDetail(String pId) {
        System.out.println("beans.ProductBean.nextProductDetail()");
        
        ProductModel p = db.find(pId);  
        this.id = p.getP_id();
        this.name = p.getP_name();
        this.count = p.getP_count();
        this.price = p.getP_price();
        this.c_id = p.getC_id();
                
        return super.nextProductDetail(); //To change body of generated methods, choose Tools | Templates.
    }

    public String convertJapanPrice() {
        return super.convertJapanPrice(this.price); //To change body of generated methods, choose Tools | Templates.
    }
    
    //***  ***//
    public String searchProductByWord(){
        System.out.println("beans.ProductBean.searchProductByWord()");
        System.out.println("category -- : " + c_id);
        System.out.println("searchWord -- : " + searchWord);
        
        resultList = db.searchProductByWord(c_id, searchWord);
        resultList.forEach(d ->{System.out.println(d.getP_name());});
        
        return nextSearchResult();
    }
    
    public String popProductName(String pId){
        return db.getProductName(pId);
    }
    
    
    
}
