package com.lyricaloriginal.soracomapiandroid;

import android.content.Context;
import android.util.Log;

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
    private final String EMAIL = "YOUR_EMAIL";
    private final String password = "YOUR_PASSWORD";

    private final String IMSI = "TARGET_IMSI";

    private AuthInfo mAuthInfo = null;

    @Before
    public void setup() throws IOException {
        Call<AuthInfo> call = Soracom.API.auth(
                new AuthRequest(EMAIL, password));
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
