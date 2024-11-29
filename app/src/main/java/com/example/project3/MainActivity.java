package com.example.project3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private DialogHelper dialogHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取按钮
        Button listViewButton = findViewById(R.id.button_listview);

        // 设置按钮点击事件
        listViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动ListViewActivity
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });


        // 初始化 DialogHelper
        dialogHelper = new DialogHelper(this);

        // 获取 AlertDialog 按钮
        Button alertDialogButton = findViewById(R.id.button_alertdialog);

        // 设置按钮点击事件
        alertDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示登录对话框
                dialogHelper.showLoginDialog();
            }
        });

        // XML定义菜单按钮
        Button xmlMenuButton = findViewById(R.id.button_xmlmenu);
        xmlMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动MenuActivity
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        // ActionMode菜单按钮
        Button actionModeButton = findViewById(R.id.button_actionmode_menu);
        actionModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动ActionModeActivity
                Intent intent = new Intent(MainActivity.this, ActionModeActivity.class);
                startActivity(intent);
            }
        });
    }



}