# SoracomApiAndroid
Android用SoracomApiライブラリ。

Square社のRetrofit-2.0.0beta2を使って作成しています。

最新バージョンは1.2.0です。

# 現在対応しているAPI
現在は以下のAPIに対応しています。

・/auth

認証用API

・/subscribers

登録しているSIM一覧取得

・/subscribers/{imsi}

指定したIMSIの情報を取得

・/subscribers/{imsi}/update_speed_class

指定したIMSIの速度クラスを変更

・/subscribers/{imsi}/activate

指定したIMSIの状態を「使用中」に変更

・/subscribers/{imsi}/deactivate

指定したIMSIの状態を「休止中」に変更

・/subscribers/{imsi}/set_expire_time

指定されたSubscriberの有効期限を更新

・/subscribers/{imsi}/unset_expire_time

指定されたSubscriberの有効期限を削除して無期限に変更

・/subscribers/{imsi}/set_group

指定されたSubscriberの所属先Groupを指定あるいは上書き変更

・/subscribers/{imsi}/unset_group

指定されたSubscriberのGroup指定を解除

・/stats/air/subscribers/{imsi}

指定したIMSIの通信量履歴を取得

・/groups(GET)

Groupの一覧を返す

・/groups(POST)

Groupを新規作成する

・/groups/{group_id}(GET)

Group IDで指定されたGroupを返す

・/groups/{group_id}(DELETE)

Group IDで指定されたGroupを削除する

・/groups/{group_id}/subscribers

Group IDで指定されたGroupに属するSubscriberの一覧を返す

# Downloads

AndroidStudioプロジェクト内のモジュール内のbuild.gradleのdependenciesブロック内に

`compile 'com.lyricaloriginal:soracom-api-android:+'`を追記します。

```groovy
android{
    // 中略
}

dependencies {

    //中略

    compile 'com.lyricaloriginal:soracom-api-android:+'
}
```

その後、Gradle#syncをすればダウンロード・プロジェクトへのインポートが完了します。

# Usage

### ユーザ認証

SoracomAPIを使うためには必ずユーザ認証をしないといけません。
ユーザ認証は`Soracom.API.auth`メソッドを使います。認証用のEmailアドレスとパスワードを引数にして認証した結果、`AuthInfo`オブジェクトが返ってきます。このオブジェクトの中の、`apiKey`, `token`は他のAPIを呼び出すときに用います。

```java
String email = "YOUR_EMAIL_ADDRESS";
String password = "YOUR_PASSWORD";
Call<AuthInfo> call = Soracom.API.auth(new AuthRequest(email, password));
Response<AuthInfo> response = call.execute();
AuthInfo authInfo = response.body();
```

上記コードは同期実行なのでバックグラウンドスレッドで実行しないといけません。非同期実行するためには`enqueue`メソッドを呼び出してください。以下にそのサンプルがあります。

```java
String email = "YOUR_EMAIL_ADDRESS";
String password = "YOUR_PASSWORD";
Call<AuthInfo> call = Soracom.API.auth(new AuthRequest(email, password));
call.enqueue(new Callback<AuthInfo> (){
    @Override
    public void onResponse(Response<AuthInfo> response, Retrofit retrofit) {
        AuthInfo authInfo = response.body();
    }

    @Override
    public void onFailure(Throwable t) {
    }
});
```

### Subscriber一覧取得

登録しているSIMの一覧を取得するためのコードを以下に記載します。以下に記載するコードは同期実行なので、非同期実行する場合には`enqueue`メソッドを使ってください。

```java
AuthInfo authInfo; // authの実行により得られたオブジェクト
Call<List<Subscriber>> call = Soracom.API.subscribers(authInfo.apiKey, authInfo.token);
Response<List<Subscriber>> response = call.execute();
List<Subscriber> subscribers = response.body();
```

# LICENSE

 Copyright 2015 LyricalMaestro(@maestro_L_jp)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
