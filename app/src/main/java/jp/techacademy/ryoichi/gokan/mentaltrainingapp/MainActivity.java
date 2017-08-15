package jp.techacademy.ryoichi.gokan.mentaltrainingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 事実投稿画面への遷移処理
        Button mDisplayFactInputButton = (Button) findViewById(R.id.displayFactInputButton);

        mDisplayFactInputButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("MentalApp", "display factInput Screen");
                Intent intent = new Intent(getApplicationContext(), factInputActivity.class);
                startActivity(intent);
            }
        });

    }
}
