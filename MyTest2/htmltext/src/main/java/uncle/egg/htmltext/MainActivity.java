package uncle.egg.htmltext;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private ImageView img;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        //img = (ImageView) findViewById(R.id.img);
        webView = (WebView) findViewById(R.id.webview);
        String html = "\n" +
                "\"<p><img alt=\"\" src=\"http://ww3.sinaimg.cn/large/c85e4a5cjw1f671i8gt1rj20vy0vydsz.jpg\" /></p>\n" +
                "\n" +
                "<h3>iOS</h3>\n" +
                "\n" +
                "<ul>\n" +
                "\t<li><a href=\"http://skyseraph.com/2016/07/18/Tools/Jenkins%20Gitlab%E6%8C%81%E7%BB%AD%E9%9B%86%E6%88%90%E6%89%93%E5%8C%85%E5%B9%B3%E5%8F%B0%E6%90%AD%E5%BB%BA/\" target=\"_blank\">iOS/Android App Jenkins CI打包平台搭建详细流程</a>&nbsp;(SkySeraph)\n" +
                "\n" +
                "\t<ul>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li><a href=\"http://www.jianshu.com/p/e28e6a846a6c\" target=\"_blank\">Swift的安全保障 - Optional特性</a>&nbsp;(None)\n" +
                "\t<ul>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li><a href=\"https://github.com/GGGHub/Reader\" target=\"_blank\">iOS 上完程度较高的一款开源电子阅读器，支持 txt，epub。</a>&nbsp;(代码家)\n" +
                "\t<ul>\n" +
                "\t\t<li><a href=\"https://github.com/GGGHub/Reader\" target=\"_blank\"><img src=\"https://github.com/GGGHub/Reader/raw/master/LSYReader/demo.gif\" title=\"iOS 上完程度较高的一款开源电子阅读器，支持 txt，epub。\" /></a></li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li><a href=\"https://github.com/KittenYang/DynamicMaskSegmentSwitch\" target=\"_blank\">带遮罩效果的 SegmentSwitch 组件</a>&nbsp;(代码家)\n" +
                "\t<ul>\n" +
                "\t\t<li><a href=\"https://github.com/KittenYang/DynamicMaskSegmentSwitch\" target=\"_blank\"><img src=\"https://github.com/KittenYang/DynamicMaskSegmentSwitch/raw/master/SwitcherBounceable.gif\" title=\"带遮罩效果的 SegmentSwitch 组件\" /></a></li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li><a href=\"https://github.com/zhxnlai/ZLSwipeableViewSwift\" target=\"_blank\">很风骚的一款卡片滑动效果工具库</a>&nbsp;(代码家)\n" +
                "\t<ul>\n" +
                "\t\t<li><a href=\"https://github.com/zhxnlai/ZLSwipeableViewSwift\" target=\"_blank\"><img src=\"https://github.com/zhxnlai/ZLSwipeableViewSwift/raw/master/Previews/rewind.gif\" title=\"很风骚的一款卡片滑动效果工具库\" /></a></li>\n" +
                "\t\t<li><a href=\"https://github.com/zhxnlai/ZLSwipeableViewSwift\" target=\"_blank\"><img src=\"https://github.com/zhxnlai/ZLSwipeableViewSwift/raw/master/Previews/undo.gif\" title=\"很风骚的一款卡片滑动效果工具库\" /></a></li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "</ul>\n" +
                "\n" +
                "<h3>Android</h3>\n" +
                "\n" +
                "<ul>\n" +
                "\t<li><a href=\"http://hlong.xyz/2016/07/20/Google%20IO%202016%20%E7%AC%94%E8%AE%B0%E4%B9%8BEspresso%20%E8%BF%9B%E9%98%B6/\" target=\"_blank\">Google I/O 2016 笔记之Espresso 进阶</a>&nbsp;(Jack Wang)\n" +
                "\n" +
                "\t<ul>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li><a href=\"https://github.com/robinhood/ticker\" target=\"_blank\">一个滑动动画的textview（更好的显示温度，金额等）</a>&nbsp;(有时放纵)\n" +
                "\t<ul>\n" +
                "\t\t<li><a href=\"https://github.com/robinhood/ticker\" target=\"_blank\"><img src=\"https://github.com/robinhood/ticker/raw/master/assets/ticker_main.gif\" title=\"一个滑动动画的textview（更好的显示温度，金额等）\" /></a></li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li><a href=\"https://github.com/qifengdeqingchen/SeatTable\" target=\"_blank\">电影票在线选座</a>&nbsp;(Jason)\n" +
                "\t<ul>\n" +
                "\t\t<li><a href=\"https://github.com/qifengdeqingchen/SeatTable\" target=\"_blank\"><img src=\"https://github.com/qifengdeqingchen/SeatTable/raw/master/demo.gif\" title=\"电影票在线选座\" /></a></li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li><a href=\"https://github.com/Cleveroad/LoopBar\" target=\"_blank\">遇见 LoopBar 从Cleveroad。在Android环境中导航的新方法</a>&nbsp;(None)\n" +
                "\t<ul>\n" +
                "\t\t<li><a href=\"https://github.com/Cleveroad/LoopBar\" target=\"_blank\"><img src=\"https://raw.githubusercontent.com/Cleveroad/LoopBar/master/images/demo.gif\" title=\"遇见 LoopBar 从Cleveroad。在Android环境中导航的新方法\" /></a></li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li><a href=\"https://github.com/yydcdut/RxMarkdown\" target=\"_blank\">一个利用RxJava在TextView和EditText上渲染的markdown解析器，支持大部分语法以及部分语法在EditText上实时预览</a>(yydcdut)\n" +
                "\t<ul>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li><a href=\"http://blog.csdn.net/luoyanglizi/article/details/52029091\" target=\"_blank\">Android：学习AIDL，这一篇文章就够了(下)</a>&nbsp;(lypeer)\n" +
                "\t<ul>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "</ul>\n" +
                "\n" +
                "<h3>拓展资源</h3>\n" +
                "\n" +
                "<ul>\n" +
                "\t<li><a href=\"http://blog.csdn.net/dd864140130/article/details/49515403\" target=\"_blank\">[java基础]深入虚拟机字节码执行引擎</a>&nbsp;(sbbic)\n" +
                "\n" +
                "\t<ul>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li><a href=\"https://github.com/hemanth/functional-programming-jargon\" target=\"_blank\">白话函数式编程！</a>&nbsp;(Shipeng Xu)\n" +
                "\t<ul>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "</ul>\n" +
                "\n" +
                "<h3>休息视频</h3>\n" +
                "\n" +
                "<ul>\n" +
                "\t<li><a href=\"http://bangumi.bilibili.com/anime/v/30478\" target=\"_blank\">那年那兔那些事儿，好玩儿的历史动画片（看完这个感觉历史能学好了</a>&nbsp;(lxxself)&nbsp;\n" +
                "\n" +
                "\t<ul>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "</ul>\n" +
                "<embed height=\"415\" pluginspage=\"http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash\" quality=\"high\" src=\"http://static.hdslb.com/miniloader.swf\" type=\"application/x-shockwave-flash\" width=\"544\"></embed>\n" +
                "\n" +
                "<p>感谢所有默默付出的编辑们，愿大家有美好一天。</p>\n" +
                "\"";

//        text.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
//        text.setText(Html.fromHtml(html));


        Document doc = Jsoup.parse(html);
        //   Elements uls = doc.select("*");

        if (doc != null) {
            Elements liElements = doc.getAllElements().get(0).getElementsByTag("h3");
            for (Element a: liElements) {
                Log.v("MY_TAG",  a.text());

            }
        }
//        Elements h3s = doc.select("h3");
//        if (h3s != null) {
//            for (Element h3 : h3s) {
//                StringBuilder linkBuffer = new StringBuilder();
//                linkBuffer.append(h3);
//                Log.v("MY_TAG", linkBuffer.toString());
//            }
//        }
//
//
//        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

//        Html.ImageGetter imgGetter = new Html.ImageGetter() {
//            public Drawable getDrawable(String source) {
//                Drawable drawable = null;
//                URL url;
//                try {
//                    url = new URL(source);
//                    drawable = Drawable.createFromStream(url.openStream(), "");  //获取网路图片
//                } catch (Exception e) {
//                    return null;
//                }
//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
//                        .getIntrinsicHeight());
//
//                return drawable;
//            }
//        };
//        img.setImageDrawable(imgGetter.getDrawable("http://img.my.csdn.net/uploads/201207/19/1342693934_4666.png"));
    }
}
