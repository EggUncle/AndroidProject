package uncle.egg.listviewfresh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ReFreshListView listView;
    private String[] testStr  = {"1","2","3","4","5","6","7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        listView = (ReFreshListView) findViewById(R.id.list);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,testStr);

        listView.setAdapter(arrayAdapter);
    }
}
