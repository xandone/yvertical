# yvertical
vertical auto  scroll view
## 效果图
![](https://github.com/xandone/yvertical/blob/master/pic/ezgif.com-video-to-gif.gif)
#### 1.layout(eg)
```Java
    <app.xandone.com.yvertical.YverticalView
        android:id="@+id/yverticalView"
        android:layout_width="match_parent"
        android:layout_height="60dp"/>

```

#### 2.init
```Java
        list.add("春眠不觉晓");
        list.add("处处闻帝鸟");
        list.add("夜来风雨声");
        list.add("花落知多少");
        list.add("谁谁谁");
        myAdapter = new MyAdapter(this, list);
        yverticalView.setAdapter(myAdapter);
        yverticalView.startScroll();
```
