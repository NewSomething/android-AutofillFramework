/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.autofillframework.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.autofill.AutofillManager;
import android.widget.Toast;

import com.example.android.autofillframework.R;


public class VirtualSignInActivity extends AppCompatActivity {

    private CustomVirtualView mCustomVirtualView;
    private AutofillManager mAutofillManager;

    public static Intent getStartActivityIntent(Context context) {
        Intent intent = new Intent(context, VirtualSignInActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.virtual_login_activity);

        mCustomVirtualView = (CustomVirtualView) findViewById(R.id.custom_view);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetFields();
            }
        });
        mAutofillManager = getSystemService(AutofillManager.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void resetFields() {
        mCustomVirtualView.resetFields();
    }

    /**
     * Emulates a login action.
     */
    private void login() {
        String username = mCustomVirtualView.getUsernameText().toString();
        String password = mCustomVirtualView.getPasswordText().toString();
        boolean valid = isValidCredentials(username, password);
        if (valid) {
            Intent intent = WelcomeActivity.getStartActivityIntent(VirtualSignInActivity.this);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Dummy implementation for demo purposes. A real service should use secure mechanisms to
     * authenticate users.
     */
    public boolean isValidCredentials(String username, String password) {
        return username != null && password != null && username.equals(password);
    }
}
