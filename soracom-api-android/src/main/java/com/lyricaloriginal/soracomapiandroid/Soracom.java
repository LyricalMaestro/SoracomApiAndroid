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

/**
 * Soracom APIをまとめたクラスです。
 * <p/>
 * Created by LyricalMaestro on 2015/12/22.
 */
public class Soracom {

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

    public interface Api {
        /*
            Auth
         */

        /**
         * Operator の認証を行う。 認証が成功した場合、API キー、オペレータ ID、 Token が返却される。
         * 認証が必要な API のリクエストには API キーと Token をヘッダーに付与する必要がある。
         *
         * @param authRequest
         * @return
         */
        @POST("auth")
        Call<AuthInfo> auth(@Body AuthRequest authRequest);

        /*
            Subscriber
         */

        /**
         * 登録しているSubScriberのリストを取得する。
         *
         * @param apiKey
         * @param token
         * @return
         */
        @GET("subscribers?tag_value_match_mode=exact")
        Call<List<SubScriber>> subscribers(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token
        );

        /**
         * 指定されたSubscriberの情報を返す。
         *
         * @param apiKey
         * @param token
         * @param imsi
         * @return
         */
        @GET("subscribers/{imsi}")
        Call<SubScriber> subscriber(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("imsi") String imsi
        );

        @POST("subscribers/{imsi}/update_speed_class")
        Call<SubScriber> updateSpeedClass(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("imsi") String imsi,
                @Body SpeedClassRequest request
        );

        @POST("subscribers/{imsi}/activate")
        Call<SubScriber> changeStatusActivate(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("imsi") String imsi
        );

        @POST("subscribers/{imsi}/deactivate")
        Call<SubScriber> changeStatusDeactivate(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("imsi") String imsi
        );

        @POST("subscribers/{imsi}/set_expiry_time")
        Call<SubScriber> setExpiryTime(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("imsi") String imsi,
                @Body ExpiryTimeRequest request
        );

        @POST("subscribers/{imsi}/unset_expiry_time")
        Call<SubScriber> setExpiryTime(
                @Header("X-Soracom-API-Key") String apiKey,
                @Header("X-Soracom-Token") String token,
                @Path("imsi") String imsi
        );
    }
}
