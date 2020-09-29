package com.example.rabbit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatContactActivity extends AppCompatActivity {

    private int[] imageId = new int[]{
            R.mipmap.head1, R.mipmap.head2, R.mipmap.head3, R.mipmap.head4, R.mipmap.head5
    };
    private String[] title = new String[]{
            "王一", "刘二", "张三", "李四", "王五"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_contact);
        List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imageId.length; i++) {
            Map<String, Object> map = new HashMap();
            map.put("image", imageId[i]);
            map.put("title", title[i]);
            listItem.add(map);
        }
        SimpleAdapter sa = new SimpleAdapter(ChatContactActivity.this, listItem,
                R.layout.contact_item,
                new String[]{"image", "title"}, new int[]{R.id.contact_image, R.id.contact_title});
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(sa);
        Log.i("ChatContact", ""+listItem.size());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Map<String, Object> map= (Map) adapterView.getItemAtPosition(position);
                Toast.makeText(ChatContactActivity.this, map.get("title").toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
