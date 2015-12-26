package com.lyricaloriginal.soracomapiandroid;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LyricalMaestro on 2015/12/22.
 */
public class SubScriber {

    /**
     * IMSI
     */
    public String imsi;
    /**
     * MSISDN
     */
    public String msisdn;
    /**
     * 製造番号
     */
    public String serialNumber;
    /**
     * 確保済みIPアドレス
     */
    public String ipAddress;
    /**
     * APN。(soracom.ioしか入らないと思うが)
     */
    public String apn;
    /**
     * 速度クラス。
     */
    public String speedClass;
    /**
     * 作成時間？？
     */
    public BigDecimal createdAt;
    /**
     * 最終修正日時
     */
    public BigDecimal lastModifiedAt;
    /**
     * 有効期限
     */
    public BigDecimal expiredAt;
    /**
     * グループID
     */
    public String groupId;
    /**
     * 解約できるかどうか？
     */
    public boolean terminationEnabled;
    /**
     * 有効期限。
     */
    public BigDecimal expiryTime;
    /**
     * ステータス。状態。<BR>
     * 「準備完了」、「使用中」、「休止中」など。
     */
    public String status;
    /**
     * プラン??
     */
    public int plan;
    /**
     * OperatorId
     */
    public String operatorId;

    /**
     * SIMのサイズ。[mini]とか[nano]とかが入る。
     */
    public String moduleType;

    /**
     * タグ情報
     */
    public Map<String, String> tags = new HashMap<String, String>();
    /**
     * セッション状態
     */
    public SessionStatus sessionStatus;

    public static class SessionStatus {
        /**
         * 最終更新日時
         */
        public BigDecimal lastUpdatedAt;
        /**
         * IMEI
         */
        public String imei;

        public String location;

        public String dnsServers;
        /**
         * オンラインかどうか
         */
        public boolean online;
        /**
         * IDアドレスだけど…?
         */
        public String ueIpAddress;

    }
}
