package com.lyricaloriginal.soracomapiandroid;

/**
 * authメソッドのリクエストjsonをモデル化したクラスです。
 * <p/>
 * Created by LyricalMaestro on 2015/12/22.
 */
public class AuthRequest {
    /**
     * emailアドレス
     */
    public final String email;
    /**
     * パスワード
     */
    public final String password;

    /**
     * コンストラクタ
     *
     * @param email    emailアドレス
     * @param password パスワード
     */
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
