package app.xandone.com.yvertical;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private YverticalView yverticalView;
    private Button btn;

    private List<String> list = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yverticalView = (YverticalView) findViewById(R.id.yverticalView);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yverticalView.resetAnim();
            }
        });
        init();
    }

    public void init() {
        list.add("春眠不觉晓");
        list.add("处处闻帝鸟");
        list.add("夜来风雨声");
        list.add("花落知多少");
        list.add("谁谁谁");
        myAdapter = new MyAdapter(this, list);
        yverticalView.setAdapter(myAdapter);
        yverticalView.startScroll();
    }
}
