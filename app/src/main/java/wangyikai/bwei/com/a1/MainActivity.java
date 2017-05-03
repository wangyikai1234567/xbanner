package wangyikai.bwei.com.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private XBanner mXBanner;
    private PagerBean mBean;
    private List<String> ls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        //初始化控件
        mXBanner = (XBanner) findViewById(R.id.banner);

        //获取图片
        getPic();
    }

    public void getPic() {

        final RequestParams params = new RequestParams(MyUrls.CD);
//        params.addQueryStringParameter("要拼接的字段名", "要拼接的字段内容");
        x.http().get(params, new Callback.CommonCallback<String>() {
            //成功
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                mBean = gson.fromJson(result, PagerBean.class);
                for (int i = 0; i < mBean.getResult().getData().size(); i++) {
                    //获取到图片
                    String pic = mBean.getResult().getData().get(i).getSteps().get(i).getImg();
                    ls.add(pic);
                }


                // 为XBanner绑定数据
                mXBanner.setData(ls, null);
                // XBanner适配数据
                mXBanner.setmAdapter(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, View view, int position) {
                        Glide.with(MainActivity.this).load(ls.get(position)).into((ImageView) view);

                    }
                });
// 设置XBanner的页面切换特效
                mXBanner.setPageTransformer(Transformer.Default);
// 设置XBanner页面切换的时间，即动画时长
                mXBanner.setPageChangeDuration(1000);
            }

            //失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            //取消
            @Override
            public void onCancelled(CancelledException cex) {
            }

            //结束
            @Override
            public void onFinished() {
            }
        });
    }
}
