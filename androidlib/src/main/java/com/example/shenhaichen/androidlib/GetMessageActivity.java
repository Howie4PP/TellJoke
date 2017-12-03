package com.example.shenhaichen.androidlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class GetMessageActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_message);
        textView = findViewById(R.id.show_message_textview);

        String mes = getIntent().getStringExtra(Tools.SEND_MESSAGE);

        textView.setText(mes);

    }
}
