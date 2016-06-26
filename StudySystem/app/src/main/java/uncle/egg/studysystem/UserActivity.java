package uncle.egg.studysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class UserActivity extends AppCompatActivity {
    @ViewInject(R.id.user_management)
    ImageButton btnUserManagement;
    @ViewInject(R.id.communicate)
    ImageButton btnCommunicate;
    @ViewInject(R.id.my_error)
    ImageButton btnMyError;
    @ViewInject(R.id.learn_course)
    ImageButton btnLearnCourse;
    @ViewInject(R.id.video_learn)
    ImageButton btnVideoLearn;
    @ViewInject(R.id.unit_exercise)
    ImageButton btnUnitExercise;
    @ViewInject(R.id.course_test)
    ImageButton btnCourseTest;
    @ViewInject(R.id.complex_test)
    ImageButton btnComplexTest;
    @ViewInject(R.id.exit_system)
    ImageButton btnExitSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTitle("Android学习测试系统");
        ViewUtils.inject(this);
    }

    @OnClick({R.id.user_management, R.id.communicate, R.id.my_error,
            R.id.learn_course, R.id.video_learn, R.id.unit_exercise,
            R.id.course_test, R.id.complex_test, R.id.exit_system})
    public void myClick(View v) {
        switch (v.getId()) {
            case R.id.user_management: {
                Toast.makeText(this,"用户管理",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.communicate: {
                Toast.makeText(this,"交流互动",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.my_error: {
                Toast.makeText(this,"错题集",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.learn_course: {
                Toast.makeText(this,"课程学习",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,CourseActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.video_learn: {
                Toast.makeText(this,"视频学习",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.unit_exercise: {
                Toast.makeText(this,"章节练习",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,UnitTestActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.course_test: {
                Toast.makeText(this,"课程测试",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.complex_test: {
                Toast.makeText(this,"综合测试",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.exit_system: {
                Toast.makeText(this,"退出系统",Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
}
