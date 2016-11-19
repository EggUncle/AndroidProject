package uncle.egg.studysystem4.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import uncle.egg.studysystem4.R;
import uncle.egg.studysystem4.utils.ReadFileUtil;

public class CourseListActivity extends AppCompatActivity {

    private ListView listCourse;
    private String[] strTest={"栈","链表","树","排序","图","队列"};
    private String strPath;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        type = getIntent().getStringExtra("type");
        Log.v("MY_TAG",type);
        switch (type) {
            case "doc":
                strPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.doc";

                break;
            case "ppt":
                strPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test1.ppt";
                break;
            case "video":
                break;
            default:
                strPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.doc";
                break;
        }

        init();
    }

    private void init(){
        listCourse  = (ListView) findViewById(R.id.list_course);
      //  strPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.doc";
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.error_item,R.id.txt_item_error,strTest);

        listCourse.setAdapter(arrayAdapter);
        listCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean flag = ReadFileUtil.openFile(CourseListActivity.this, strPath);
                if (flag == true) {
                    Toast.makeText(CourseListActivity.this, " 打开文件成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CourseListActivity.this, "打开文件失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
