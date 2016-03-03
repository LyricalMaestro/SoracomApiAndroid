package com.lyricaloriginal.soracomapiandroid;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.lyricaloriginal.soracom_api_android.BuildConfig;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by LyricalMaestro on 2016/03/02.
 */
public class SoracomTest {

    private final String IMSI = "TARGET_IMSI";

    private AuthInfo mAuthInfo = null;

    @Before
    public void setup() throws IOException {
        List<String> lines = AssetsUtils.readAssetsText("Account.txt");
        String email = lines.get(0).split("=")[1];
        String password = lines.get(1).split("=")[1];

        Call<AuthInfo> call = Soracom.API.auth(
                new AuthRequest(email, password));
        Response<AuthInfo> resp = call.execute();
        if (!resp.isSuccess()) {
            Assert.fail("認証に失敗しました。 code = " + resp.code());
        }

        mAuthInfo = resp.body();
    }

    @Test
    public void stat() throws IOException {
        Call<List<AirStats>> call = Soracom.API.airSubscribers(
                mAuthInfo.apiKey,
                mAuthInfo.token,
                IMSI,
                1445040001,
                1445050000,
                "day"
        );
        Response<List<AirStats>> resp = call.execute();
        if (!resp.isSuccess()) {
            Assert.fail("通信量履歴取得に失敗 code = " + resp.code());
        }
        List<AirStats> lists = resp.body();
        for (AirStats airStats : lists) {
            Log.d("SoracomApi", airStats.toString());
        }
    }
}
