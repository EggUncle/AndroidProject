package uncle.egg.studysystem2.myFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import uncle.egg.studysystem2.R;

/**
 * Created by 西域战神阿凡提 on 2016/1/19.
 */
public class ThridFragment extends Fragment {

    private ListView lsError;
    private View view;

    private String[] testStr = {"数据结构", "数据库", "移动网络", "软件工程", "软件测试", "计算机系统", "前端开发", "移动应用"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.thridlayout, null);
        init();
        return view;
    }

    private void init() {
        lsError = (ListView) view.findViewById(R.id.ls_error);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.error_item, R.id.txt_item_error, testStr);
        lsError.setAdapter(arrayAdapter);
    }

}
