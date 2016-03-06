package com.lyricaloriginal.soracomapiandroid;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Soracom APIをまとめたクラスです。
 * <p/>
 * Created by LyricalMaestro on 2015/12/22.
 */
public final class Soracom {

    /**
     * APIメソッドをまとめたオブジェクトです。<BR>
     * このクラスのメソッドを呼び出した時点でサーバへの問い合わせはしません。
     * 実際のサーバへの問い合わせは、戻り値のenqueue, executeメソッドを呼び出してください。
     */
    public static final Api API;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.soracom.io/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API = retrofit.create(Api.class);
    }

    private Soracom() {
    }

    /**
     * APIメソッドをまとめたクラスです。
     */
    public interface Api {
        /*
            Auth
         */

        /**
         * Operator の認証を行う。 認証が成功した場合、API キー、オペレータ ID、 Token が返却される。
         * 認証が必要な API のリクエストには API キーと Token をヘッダーに付与する必要がある。
         *
         * @param authRequest 認証用リクエスト
         * @return callオブジェクト
         */
        @POST("auth")
        Call<AuthInfo> auth(@Body AuthRequest authRequest);

        /*
            Subscriber
         */

        /**
         * 登録しているSubScriberのリストを取得する。
         *
         * @param apiKey APIキー
         * @param token  Token
         * @return callオブジェクト
         */
        @GET("subscribers?tag_value_match_mode=exact")
        Call<List<SubScriber>> subscribers(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token
        );

        /**
         * 指定されたSubscriberの情報を返す。
         *
         * @param apiKey APIキー
         * @param token  Token
         * @param imsi   対象IMSI
         * @return callオブジェクト
         */
        @GET("subscribers/{imsi}")
        Call<SubScriber> subscriber(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("imsi") String imsi
        );

        /**
         * 指定されたSubscriberの速度クラスを変更します
         *
         * @param apiKey  APIキー
         * @param token   Token
         * @param imsi    対象IMSI
         * @param request 速度変更内容リクエスト
         * @return callオブジェクト
         */
        @POST("subscribers/{imsi}/update_speed_class")
        Call<SubScriber> updateSpeedClass(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("imsi") String imsi,
                @Body SpeedClassRequest request
        );

        /**
         * 指定されたSubscriberのステータスを有効化
         *
         * @param apiKey APIキー
         * @param token  Token
         * @param imsi   対象IMSI
         * @return callオブジェクト
         */
        @POST("subscribers/{imsi}/activate")
        Call<SubScriber> changeStatusActivate(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("imsi") String imsi
        );

        /**
         * 指定されたSubscriberを無効化
         *
         * @param apiKey APIキー
         * @param token  Token
         * @param imsi   対象IMSI
         * @return callオブジェクト
         */
        @POST("subscribers/{imsi}/deactivate")
        Call<SubScriber> changeStatusDeactivate(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("imsi") String imsi
        );

        /**
         * 指定されたSubscriberの有効期限を更新
         *
         * @param apiKey  APIキー
         * @param token   Token
         * @param imsi    対象IMSI
         * @param request 有効期限指定リクエスト
         * @return callオブジェクト
         */
        @POST("subscribers/{imsi}/set_expiry_time")
        Call<SubScriber> setExpiryTime(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("imsi") String imsi,
                @Body ExpiryTimeRequest request
        );

        /**
         * 指定されたSubscriberの有効期限を削除して無期限に変更
         *
         * @param apiKey APIキー
         * @param token  Token
         * @param imsi   対象IMSI
         * @return callオブジェクト
         */
        @POST("subscribers/{imsi}/unset_expiry_time")
        Call<SubScriber> setExpiryTime(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("imsi") String imsi
        );

        /*
            Stats
         */

        /**
         * IMSI で指定した Subscriber の通信量履歴を取得する。
         *
         * @param apiKey APIキー
         * @param token  Token
         * @param imsi   対象IMSI
         * @param from   集計対象時刻の始まり。unixtimeで指定。
         * @param to     集計対象時刻の終わり。unixtimeで指定。
         * @param period 集計単位。"month","day","minutes"のいずれかを指定。minutesはminutesは5分おき。
         * @return callオブジェクト
         */
        @GET("stats/air/subscribers/{imsi}")
        Call<List<AirStats>> airSubscribers(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("imsi") String imsi,
                @Query("from") int from,
                @Query("to") int to,
                @Query("period") String period
        );

        /*
            Groups
         */

        /**
         * Groupの一覧を返す
         *
         * @param apiKey APIキー
         * @param token  Token
         * @return Groupオブジェクトの一覧
         */
        @GET("groups")
        Call<List<Group>> groups(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token
        );

        /**
         * 指定したIDのGroupの情報を取得する
         *
         * @param apiKey  APIキー
         * @param token   Token
         * @param groupId groupId
         * @return 指定したgroupIdに該当するGroupオブジェクト
         */
        @GET("groups/{group_id}")
        Call<Group> group(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("group_id") String groupId
        );

        /**
         * Group IDで指定されたGroupに属するSubscriberの一覧を返す
         *
         * @param apiKey  APIキー
         * @param token   Token
         * @param groupId groupId
         * @return 指定したGroupIdに属するSubscriberの一覧
         */
        @GET("groups/{group_id}/subscribers")
        Call<List<SubScriber>> findSubscribersByGroupId(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("group_id") String groupId
        );

        /**
         * 新規にGroupを作成します。
         *
         * @param apiKey  APIキー
         * @param token   Token
         * @param groupRequest リクエストオブジェクト
         * @return callオブジェクト
         */
        @POST("groups")
        Call<Group> createGroup(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Body CreateGroupRequest groupRequest
        );
    }
}
