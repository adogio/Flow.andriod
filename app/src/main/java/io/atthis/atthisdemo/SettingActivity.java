package io.atthis.atthisdemo;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class SettingActivity extends AppCompatActivity {
    private Button Logout;
    private Button CreateTask;
    private Button EditProfile;
    private UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        userInfo = new UserInfo(getIntent());
        Logout = (Button) this.findViewById(R.id.logoutbutton);
        CreateTask = (Button) this.findViewById(R.id.SettingCreateTask);
        EditProfile = (Button) this.findViewById(R.id.SettingEditProfile);
        Logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                editor.putString("token", "");
                editor.commit();
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, MainActivity.class);
                JPushInterface.setAlias(SettingActivity.super.getBaseContext(), "logout", new TagAliasCallback() {
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                        setTitle(i+"");
                    }
                });
                startActivity(intent);
                SettingActivity.this.finish();
            }

        });
        CreateTask.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, CreateTaskActivity.class);
                userInfo.setIntent(intent);
                startActivity(intent);
            }
        });
        EditProfile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, EditProfileActivity.class);
                userInfo.setIntent(intent);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
