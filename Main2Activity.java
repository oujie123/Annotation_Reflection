package com.gacrnd.gcs.generictest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gacrnd.gcs.generictest.inject.Autowired;
import com.gacrnd.gcs.generictest.inject.InjectUtils;
import com.gacrnd.gcs.generictest.inject.InjectView;
import com.gacrnd.gcs.generictest.inject.UserParcelable;
import com.gacrnd.gcs.generictest.inject.UserSerializable;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    @InjectView(R.id.tv2)
    private TextView tv;

    @Autowired()
    private String name;

    @Autowired("attr")
    private String attr;

    @Autowired("isBoy")
    private boolean isBoy;

    @Autowired
    private int[] array;

    @Autowired
    UserParcelable userParcelable;

    @Autowired
    UserParcelable[] userParcelables;

    @Autowired
    List<UserParcelable> userParcelableList;

    @Autowired("users")
    UserSerializable[] userSerializables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        InjectUtils.injectView(this);
        InjectUtils.injectShow(this);

        tv.setText(name + "," + isBoy + "\r\n" + userSerializables.toString()
                + "\r\n" + userParcelableList.toString() + "\r\n" + userParcelables.toString()
                + "\r\n" + userParcelable.toString() + "\r\n" + array.length + "\r\n" + attr);
    }


}
