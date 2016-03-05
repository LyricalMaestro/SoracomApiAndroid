package com.lyricaloriginal.soracomapiandroid;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Subscriberグループのモデルクラスです。
 * <p/>
 * @see  //configurationは取得できません。
 * Created by LyricalMaestro on 2016/03/05.
 */
public class Group {

    /**
     * 作成時間
     */
    public BigDecimal createdTime;

    /**
     * グループID
     */
    public String groupId;

    /**
     * 最終更新時間
     */
    public BigDecimal lastModifiedTime;

    /**
     * オペレータID
     */
    public String operatorId;

    /**
     * タグ
     */
    public Map<String, String> tags = new HashMap<String, String>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("groupId : " + groupId + ",");
        sb.append("createdTime : " + createdTime + ",");
        sb.append("lastModifiedTime : " + lastModifiedTime + ",");
        sb.append("operatorId : " + operatorId + ",");
        sb.append("[");
        for (Map.Entry<String, String> entry : tags.entrySet()) {
            sb.append(entry.getKey() + ":" + entry.getValue() + ",");
        }
        sb.append("]");
        return sb.toString();
    }
}
