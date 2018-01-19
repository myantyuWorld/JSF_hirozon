/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.UserModel;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author 5151021
 */
@Stateless
public class UserDb {
    
    private static final int NAME = 1;
    private static final int MAILADDR = 2;
    private static final int TEL = 3;
    private static final int PASS = 4;

    @PersistenceContext(unitName = "HIROZONPU")
    private EntityManager em;

    //*** ログインチェックメソッド ***//
    public UserModel loginCheck(String id, String pass) {
        System.out.println("ejb.UserDb.loginCheck()");
        UserModel um = null;
        // NamedQuery で書き直した
        TypedQuery<UserModel> query = em.createNamedQuery("User.loginCheck", UserModel.class);
        query.setParameter(NAME, id);
        query.setParameter(MAILADDR, pass);
        try {
            um = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return um;
    }
    
    public UserModel find(String key){
        System.out.println("ejb.UserDb.find()");
        return em.find(UserModel.class, key);
    }

    //*** ログインIDから当該ユーザ情報を取得するメソッド ***//
    public UserModel findUser(String loginId){
        return em.createNamedQuery("User.findUser", UserModel.class).setParameter(1, loginId).getSingleResult();
    }
    
    //***  ***//
    public UserModel findUser(String uId, String userMailAddr, String loginPass) throws NoSuchAlgorithmException{
        return em.createNamedQuery("User.unsubscribe", UserModel.class)
                .setParameter(1, uId)
                .setParameter(2, userMailAddr)
                .setParameter(3, util.Util.returnSHA256(loginPass))
                .getSingleResult();
    }
    
    
    //*** 退会用削除メソッド ***//
    public void unsubscribe(UserModel um){
        UserModel model = em.find(UserModel.class, um.getU_id());
//        em.remove(model);
        // 実際に消すわけではなくて、フラグをupdateすることで退会とする
        model.setUserflg(1);
        
        em.merge(model);
    }

    //*** 更新用メソッド ***//
    public void merge(UserModel um){
        System.out.println("ejb.UserDb.merge()");
        
        // null check
        if (em == null) return;
        System.out.println("ユーザ情報の更新を実施");
        em.merge(um);
    }
    
    //*** 新規登録用メソッド ***//
    public String persist(UserModel um){
        System.out.println("ejb.UserDb.persist()");
        try {
            em.persist(um);
        } catch (Exception e){
            System.out.println("errorrrrrrrrrrrrrrrrrrrrrrr!");
            
            return ERROR;
            // ここでエラーが起きたら、メッセージでも出すようにする
        }
        return OK;
    }
    private static final String ERROR = "1";
    private static final String OK = "0";
    
}
