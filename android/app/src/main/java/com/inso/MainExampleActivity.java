package com.inso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.react.ReactActivity;
import com.inso.example.Hybrid.NativeAct;
import com.inso.example.Login.XiaoMiSampleAct;
import com.inso.watch.commonlib.constants.PermissionConstants;
import com.inso.watch.commonlib.utils.L;
import com.inso.watch.commonlib.utils.PermissionUtils;
import com.inso.watch.commonlib.utils.ServiceUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainExampleActivity extends ReactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (!PermissionUtils.isGranted(PermissionConstants.CONTACTS, PermissionConstants.PHONE)) {
            PermissionUtils.permission(PermissionConstants.CONTACTS, PermissionConstants.PHONE)
                    .rationale(new PermissionUtils.OnRationaleListener() {
                        @Override
                        public void rationale(final ShouldRequest shouldRequest) {
                            L.d("permission rationale");
                        }
                    })
                    .callback(new PermissionUtils.FullCallback() {
                        @Override
                        public void onGranted(List<String> permissionsGranted) {
                            L.d("permission onGranted");
                            ServiceUtils.startService("com.inso.example.IncomingCall");
                        }

                        @Override
                        public void onDenied(List<String> permissionsDeniedForever,
                                             List<String> permissionsDenied) {
                            L.d("permission onDenied");
                        }
                    })
                    .request();
        }

    }

    @Override
    protected String getMainComponentName() {
        return "inso";
    }

    @OnClick({R.id.btn_example_comm, R.id.btn_example_xiaomi_login})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_example_xiaomi_login:
                intent = new Intent(MainExampleActivity.this, XiaoMiSampleAct.class);
                break;
            case R.id.btn_example_comm:
                intent = new Intent(MainExampleActivity.this, NativeAct.class);
                break;
        }
        startActivity(intent);
    }
}
