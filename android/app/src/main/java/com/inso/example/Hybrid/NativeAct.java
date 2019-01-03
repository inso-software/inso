package com.inso.example.Hybrid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.react.ReactActivity;
import com.inso.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Comment:
 * Author: ftc300
 * Date: 2019/1/2
 * Blog: www.ftc300.pub
 * GitHub: https://github.com/ftc300
 */

public class NativeAct extends ReactActivity {
    @BindView(R.id.btn_gotorn)
    Button mBtnGotorn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_example_native);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tv, R.id.btn_gotorn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv:
                break;
            case R.id.btn_gotorn:
                Intent intent = new Intent(NativeAct.this,RNAct.class);
                intent.putExtra("data","SwitchToHelloReact");
                startActivity(intent);
                break;
        }
    }
}
