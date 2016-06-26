package uncle.egg.studysystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class CourseActivity extends AppCompatActivity {
    @ViewInject(R.id.course_list)
    ListView listCourse;


    String[] courseName = {"C","C++","数据结构","离散数学","数据库","JAVA","JSP","Android"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        setTitle("课程学习");
        ViewUtils.inject(this);
        init();
        ArrayAdapter<String> arrayAdapter =
               new ArrayAdapter<String>(this,R.layout.itemlayout,courseName);

        listCourse.setAdapter(arrayAdapter);

    }

    private void init(){
        listCourse = (ListView) findViewById(R.id.course_list);
    }



}
