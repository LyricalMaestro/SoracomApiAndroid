package com.lyricaloriginal.soracomapiandroid;

/**
 * 通信量をまとめたクラスです。
 * <p/>
 * Created by LyricalMaestro on 2016/01/03.
 */
public class TrafficStats {

    /**
     * アップロードバイトサイズ(単位はByte)
     */
    public long uploadByteSizeTotal;
    /**
     * ダウンロードバイトサイズ(単位はByte)
     */
    public long downloadByteSizeTotal;
    /**
     * アップロードのパケットサイズ?
     */
    public int uploadPacketSizeTotal;
    /**
     * ダウンロードのパケットサイズ
     */
    public int downloadPacketSizeTotal;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("uploadByteSizeTotal:" + uploadByteSizeTotal + ",");
        sb.append("downloadByteSizeTotal:" + downloadByteSizeTotal + ",");
        sb.append("uploadPacketSizeTotal:" + uploadPacketSizeTotal + ",");
        sb.append("downloadPacketSizeTotal:" + downloadPacketSizeTotal);
        return sb.toString();
    }
}
