package com.lisss79.testapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // This is test_branch1

        ActivityResultContract<String, ActivityResult> contract =
                new ActivityResultContract<String, ActivityResult>() {

            @Override
            public ActivityResult parseResult(int resultCode, @Nullable Intent intent) {
                ActivityResult result = new ActivityResult(resultCode, intent);
                return result;
            }

            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, String type) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType(type);
                return intent;
            }
        };

        ActivityResultLauncher<String> openDocumentLauncher =
                registerForActivityResult(contract, result -> {
                    System.out.println(result.getResultCode());
                    System.out.println(result.getData().getData());
                });
        openDocumentLauncher.launch("*/*");

    }
}