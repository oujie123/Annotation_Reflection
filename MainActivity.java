package com.gacrnd.gcs.generictest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gacrnd.gcs.generictest.inject.InjectUtils;
import com.gacrnd.gcs.generictest.inject.InjectView;
import com.gacrnd.gcs.generictest.inject.UserParcelable;
import com.gacrnd.gcs.generictest.inject.UserSerializable;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tv)
    private TextView textView;

    @InjectView(R.id.btn)
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtils.injectView(this);
        textView.setText("hello OJ");
        final ArrayList<UserParcelable> userParcelableList = new ArrayList<>();
        userParcelableList.add(new UserParcelable("Jett"));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class)
                                    .putExtra("name","oujie")
                                    .putExtra("isBoy",true)
                                    .putExtra("attr","generous")
                                    .putExtra("array", new int[]{1, 2, 3, 4, 5, 6})
                                    .putExtra("userParcelable", new UserParcelable("Lance"))
                                    .putExtra("userParcelables", new UserParcelable[]{new UserParcelable("Alvin")})
                                    .putExtra("users",new UserSerializable[]{new UserSerializable("Jett")})
                                    .putExtra("strs",new String[]{"1","2"})
                                    .putParcelableArrayListExtra("userParcelableList", userParcelableList);
                startActivity(intent);
            }
        });
    }
}
