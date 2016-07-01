package uncle.egg.studysystem3.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import uncle.egg.studysystem3.R;

public class CourseListActivity extends AppCompatActivity {

    private ListView listCourse;
    private String[] strTest={"栈","链表","树","排序","图","队列"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        init();
    }

    private void init(){
        listCourse  = (ListView) findViewById(R.id.list_course);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.error_item,R.id.txt_item_error,strTest);

        listCourse.setAdapter(arrayAdapter);
    }
}
