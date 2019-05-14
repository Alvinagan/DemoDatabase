package sg.edu.rp.c346.demodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;

    ListView lv;
    ArrayList<Task> al;
    TaskAdapter taskAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);

        tvResults = findViewById(R.id.tvResults);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        btnInsert = findViewById(R.id.btnInsert);

        al = new ArrayList<Task>();
        DBHelper db = new DBHelper(MainActivity.this);
        ArrayList<Task> data = db.getTasks();
        db.close();

        for (int i = 0; i < data.size(); i++) {
            Task task1 = new Task(data.get(i).getId(), data.get(i).getDescription(), data.get(i).getDate());
            al.add(task1);
            taskAdapter = new TaskAdapter(this, R.layout.row, al);


            lv.setAdapter(taskAdapter);

        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);

                db.insertTask("Submit RJ", "25 Apr 2016");
                db.close();
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++){
                    Log.d("Database Content", i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);
            }
        });
    }
}
