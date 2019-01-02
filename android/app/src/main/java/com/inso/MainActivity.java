package com.inso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    public Button helloReact;
    public Button hello_react2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloReact=(Button)findViewById(R.id.hello_react);
        hello_react2=(Button)findViewById(R.id.hello_react2);
        helloReact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HelloReactActivity.class);
                intent.putExtra("data","SwitchToHelloReact");
                startActivity(intent);
            }
        });
        hello_react2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainApplication.exampleReactNativePackage.toastExample.nativeCallRn();
            }
        });
    }

    @Override
    protected String getMainComponentName() {
        return "inso";
    }
}
