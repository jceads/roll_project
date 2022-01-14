package com.example.proje_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import io.alterac.blurkit.BlurLayout;

public class OlmayanlarListesi extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String> names ;
    BlurLayout blurLayout;
    Button btn_okay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olmayanlar_listesi);

        init();

        ArrayList<Student> students = new ArrayList<Student>();
        names = new ArrayList<String>();
        students = (ArrayList<Student>) getIntent().getSerializableExtra("StudentExtra");

        WriteStudentNametoArray(students);
        recyclerViewAdapter recyclerViewAdapter = new recyclerViewAdapter(this,names);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn_okay.setOnClickListener(v -> {
            onBackPressed();
            /*finishAndRemoveTask();
            System.exit(1);*/
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        blurLayout.startBlur();
    }
    public void onBackPressed(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    protected void onStop() {
        blurLayout.pauseBlur();
        super.onStop();
    }

    private void WriteStudentNametoArray(ArrayList<Student> students)
    {
        for(int i = 0; i<students.size();i++){
            if(students.get(i).getStudentRoll()==false){
                names.add(students.get(i).getName());
            }
        }
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        btn_okay = findViewById(R.id.btn_okay);
        blurLayout = findViewById(R.id.blurLayout);


    }
}