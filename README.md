# SoracomApiAndroid
Android用SoracomApiライブラリ。

Square社のRetrofit-2.0.0beta2を使って作成しています。

最新バージョンは1.1.0です。

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
