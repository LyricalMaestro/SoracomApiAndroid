package com.lyricaloriginal.soracomapiandroid;

import java.util.HashMap;
import java.util.Map;

/**
 * 指定した集計単位でまとめた通信量履歴のモデルクラスです。
 * <p/>
 * Created by LyricalMaestro on 2016/01/03.
 */
public class AirStats {
    /**
     * 対象日時。periodの指定によって値のフォーマットが変わる。
     */
    String date;
    /**
     * 対象日時のUnixTime。
     */
    int unixtime;
    /**
     * 実際の通信量履歴まとめ。速度クラス別に集計される。
     */
    Map<String, TrafficStats> dataTrafficStatsMap = new HashMap<String, TrafficStats>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("date : " + date + ",");
        sb.append("unixtime : " + unixtime + ",");
        sb.append("[");
        for (Map.Entry<String, TrafficStats> entry : dataTrafficStatsMap.entrySet()) {
            sb.append(entry.getKey() + ":{" + entry.getValue().toString() + "},");
        }
        sb.append("]");
        return sb.toString();
    }
}
