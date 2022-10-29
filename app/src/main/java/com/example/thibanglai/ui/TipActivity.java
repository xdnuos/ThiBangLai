package com.example.thibanglai.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thibanglai.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class TipActivity extends AppCompatActivity {

    TextView textView;
    BottomNavigationView bottomNavigationView;
    ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        textView = findViewById(R.id.tv_content_tip);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml("I, MẸO HỌC PHẦN LÝ THUYẾT\n" +
                    "    <br>\n" +
                    "    1, Câu hỏi liên quan đến khái niệm\n" +
                    "    <br>\n" +
                    "    Học viên phải nhớ các khái niệm quan trọng sau để khi gặp đáp án có chứa các cụm từ liên quan ở dưới thì chọn luôn làm đáp án đúng.\n" +
                    "    <br><br>\n" +
                    "    <ul>\n" +
                    "        <li>\n" +
                    "            Phần đường xe chạy: được sử dụng cho các phương tiện giao thông qua lại.\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Làn đường: có bề rộng đủ cho xe chạy an toàn.\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Dải phân cách: phân chia phần đường của xe cơ giới và xe thô sơ.\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Dừng xe, đỗ xe: Chọn luôn đáp án 2.\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Người điều khiển giao thông: là cảnh sát giao thông, người được giao nhiệm vụ hướng dẫn giao thông.\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Phương tiện giao thông cơ giới đường bộ: chọn đáp án 2 (kể cả xe máy điện)\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Phương tiện giao thông thô sơ đường bộ: chọn đáp án 1 (xe lăn dùng cho người khuyết tật)\n" +
                    "        </li>\n" +
                    "    </ul>\n" +
                    "    <br>2, Câu hỏi liên quan đến con số\n" +
                    "    <ul><br>\n" +
                    "        <li>\n" +
                    "            Câu hỏi chứa đáp án có Số 5 ( 5m, 5 năm) --> măc định đúng.\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Còi xe: Chỉ được sử dụng Còi từ 5h-22h ( Cấm từ 22h đến 5h sáng hôm sau).\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Tốc độ xe cơ giới: Tốc độ tối đa trong khu vực đông dân cư của:\n" +
                    "            <ul>\n" +
                    "                <li>\n" +
                    "                    Xe gắn máy : 40 km/h\n" +
                    "                </li>\n" +
                    "                <li>\n" +
                    "                    Xe mô tô và xe con: Không có giải phân cách giữa là 50 km/h, Có giải phân cách giữa là 60 km/h.\n" +
                    "                </li>\n" +
                    "            </ul>\n" +
                    "        </li>\n" +
                    "    </ul>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml("I, MẸO HỌC PHẦN LÝ THUYẾT\n" +
                    "    <br>\n" +
                    "    1, Câu hỏi liên quan đến khái niệm\n" +
                    "    <br>\n" +
                    "    Học viên phải nhớ các khái niệm quan trọng sau để khi gặp đáp án có chứa các cụm từ liên quan ở dưới thì chọn luôn làm đáp án đúng.\n" +
                    "    <br><br>\n" +
                    "    <ul>\n" +
                    "        <li>\n" +
                    "            Phần đường xe chạy: được sử dụng cho các phương tiện giao thông qua lại.\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Làn đường: có bề rộng đủ cho xe chạy an toàn.\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Dải phân cách: phân chia phần đường của xe cơ giới và xe thô sơ.\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Dừng xe, đỗ xe: Chọn luôn đáp án 2.\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Người điều khiển giao thông: là cảnh sát giao thông, người được giao nhiệm vụ hướng dẫn giao thông.\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Phương tiện giao thông cơ giới đường bộ: chọn đáp án 2 (kể cả xe máy điện)\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Phương tiện giao thông thô sơ đường bộ: chọn đáp án 1 (xe lăn dùng cho người khuyết tật)\n" +
                    "        </li>\n" +
                    "    </ul>\n" +
                    "    2, Câu hỏi liên quan đến con số:\n" +
                    "    <br><ul>\n" +
                    "        <li>\n" +
                    "            Câu hỏi chứa đáp án có Số 5 ( 5m, 5 năm) --> măc định đúng.\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Còi xe: Chỉ được sử dụng Còi từ 5h-22h ( Cấm từ 22h đến 5h sáng hôm sau).\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "            Tốc độ xe cơ giới: Tốc độ tối đa trong khu vực đông dân cư của:\n" +
                    "            <ul>\n" +
                    "                <li>\n" +
                    "                    Xe gắn máy : 40 km/h\n" +
                    "                </li>\n" +
                    "                <li>\n" +
                    "                    Xe mô tô và xe con: Không có giải phân cách giữa là 50 km/h, Có giải phân cách giữa là 60 km/h.\n" +
                    "                </li>\n" +
                    "            </ul>\n" +
                    "        </li>\n" +
                    "    </ul>"));
        }

        btn_back =findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),TimKiemActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        //startActivity(new Intent(getApplicationContext(),TimKiemActivity.class));
                        //overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}