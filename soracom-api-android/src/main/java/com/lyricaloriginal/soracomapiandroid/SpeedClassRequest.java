package com.lyricaloriginal.soracomapiandroid;

/**
 * 登録SIMの回線速度変更APIの変更内容リクエストをモデル化したクラスです。
 * <p/>
 * Created by LyricalMaestro on 2015/12/23.
 */
public class SpeedClassRequest {
    /**
     * 変更後の回線速度。<BR>
     * s1.minimum, s1.slow, s1.standard, s1.fastが有効。
     */
    public String speedClass;
}
