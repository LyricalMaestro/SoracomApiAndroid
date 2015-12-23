package com.lyricaloriginal.soracomapiandroid;

/**
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
     * @param email
     * @param password
     */
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
