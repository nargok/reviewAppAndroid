package jp.techacademy.ryoichi.gokan.mentaltrainingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class factInputActivity extends AppCompatActivity {

    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_input);


        // タイトルを設定
        setTitle("事実を投稿する");

        // 事実を投稿する処理
        Button sendFactButton = (Button) findViewById(R.id.sendFactButton);
        sendFactButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("mentalApp", "sendFactButton onClick");

                EditText titleEditText = (EditText)findViewById(R.id.factTitleEditText);
                EditText contentEditText = (EditText)findViewById(R.id.factContentEditText);
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();

                if ((title.length() != 0) && (content.length() != 0)) {
                    HashMap<String, Fact> postFact = new HashMap<>();
                    Fact fact = new Fact();
                    fact.setTitle(title);
                    fact.setContent(content);
                    postFact.put("fact", fact);

                    CreateFact createFact = RetrofitUtils.build().create(CreateFact.class);
                    Call<Fact> task = createFact.createFact(postFact);
                    task.enqueue(new Callback<Fact>() {
                        @Override
                        public void onResponse(Call<Fact> call, Response<Fact> response) {
                            if (response.isSuccessful()) {
                                Log.d("mentalApp", "sendingFact was completed!");
                                msg = "投稿に成功しました";

                                // 作成された事実データのidを取り出す
                                int factId = response.body().getId();
                                Log.d("mentalApp", "createdFactId is " + String.valueOf(factId));

                            } else {
                                Log.d("mentalApp", "sendingFact was failed!");
                                msg = "事実の投稿に失敗しました";
                            }
                            Toast.makeText(factInputActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Fact> call, Throwable t) {
                            msg = "HTTP接続エラー";
                            Toast.makeText(factInputActivity.this, msg, Toast.LENGTH_SHORT).show();
                            Log.d("mentalApp", t.toString());
                        }
                    });
                }
            }
        });
    }

    public static class RetrofitUtils {
        public static Retrofit build() {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            return new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:3000/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
    }

}
