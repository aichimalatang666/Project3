package com.example.project3;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionModeActivity extends AppCompatActivity {

    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_mode_listview);

        // 初始化列表数据
        listView = findViewById(R.id.listView);
        items = new ArrayList<>();

        // 创建列表项（图标和文本）
        String[] itemTexts = {"One", "Two", "Three", "Four", "Five"};
        for (String text : itemTexts) {
            Map<String, Object> item = new HashMap<>();
            item.put("icon", R.drawable.ic_launcher_background);
            item.put("text", text);
            items.add(item);
        }

        // 创建SimpleAdapter来绑定图标和文本
        adapter = new SimpleAdapter(this, items, R.layout.action_mode_list_item,
                new String[]{"icon", "text"}, new int[]{R.id.itemIcon, R.id.itemText});
        listView.setAdapter(adapter);

        // 设置多选模式并启用ActionMode
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                // 更新ActionMode的标题
                int count = listView.getCheckedItemCount();
                mode.setTitle(count + " selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // 加载上下文菜单
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_action_mode, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.delete) {
                    // 删除选中的项目
                    for (int i = listView.getAdapter().getCount() - 1; i >= 0; i--) {
                        if (listView.isItemChecked(i)) {
                            items.remove(i);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    mode.finish();  // 结束ActionMode
                    Toast.makeText(ActionModeActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // 当ActionMode结束时执行
            }
        });
    }
}
