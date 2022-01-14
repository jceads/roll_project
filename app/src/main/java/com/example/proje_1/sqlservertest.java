package com.example.proje_1;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class sqlservertest extends AppCompatActivity {

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlservertest);

        //inits();
        TextView txt1,txt2;
        Button btn;

        txt1=findViewById(R.id.txt_studentidforsql);
        txt2=findViewById(R.id.txt_usernameforsql);
        btn=findViewById(R.id.btn_sql);


        XmlPullParserFactory pullParserFactory;
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser= pullParserFactory.newPullParser();
            InputStream inputStream = getApplicationContext().
                    getAssets().open("database.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);

            ArrayList<Student> students = parseXML(parser);
            String text = "";
            for (Student student : students ){
                text += "id : " + student.getStudentID() + " name : " + student.getName()
                        + " class id: " + student.getStudentID() + "\n";

            }
            txt1.setText(text);

        } catch (XmlPullParserException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }


        /*TextView username, studentid;
        Button btn_sql;

        username = findViewById(R.id.txt_usernameforsql);
        studentid = findViewById(R.id.txt_studentidforsql);
        btn_sql = findViewById(R.id.btn_sql);

        btn_sql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection = (Connection) new ConnectionClass();
                try {

                    if(connection !=null){
                        String sqlget = "select * from TBL_STUDENT where ID= '" + username.toString()+"'";
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(sqlget);
                        username.setText(resultSet.getString(2));
                    }


                }
                catch (Exception e){
                    Toast.makeText(sqlservertest.this,"Hata",Toast.LENGTH_SHORT);
                    Log.e("Error",e.getMessage());
                }

            }
        });*/





            //@Override
            //public void onClick(View view) {
                /*Connection connection = (Connection) new ConnectionClass();
                try {
                    if(connection != null){
                        String sqlget="select * from TBL_STUDENT where ID= '"+ studentidforsql.toString()+"'";
                        Statement st= connection.createStatement();
                        ResultSet resultSet = st.executeQuery(sqlget);
                        //while (resultSet.next()){
                        txt_usernameforsql.setText(resultSet.getString(2));
                        // }
                    }
                }
                catch (Exception e){
                    Log.e("Error",e.getMessage());
                }*



    }*/
    private ArrayList<Student> parseXML(XmlPullParser parser) throws XmlPullParserException, IOException{
        ArrayList<Student> students = null;
        int eventType = parser.getEventType();
        Student student =null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch (eventType)
            {
                case XmlPullParser.START_DOCUMENT:
                    students = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if(name.equals("student")){
                        student=new Student();
                        student.setStudentID(parser.getAttributeValue(null,"id"));
                    }
                    else if (student != null){
                        if(name.equals("name")){
                            student.setName(parser.nextText());
                        } else if(name.equals("class id")){
                            student.setStudentID(parser.nextText());


                        }
                    }
                    break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if(name.equalsIgnoreCase("class id")&& student != null)
                                students.add(student);
            }
            eventType = parser.next();

        }
        return students;
    }


    private void inits(){
        TextView txt1,txt2;
        Button btn;
        txt1=findViewById(R.id.txt_studentidforsql);
        txt2=findViewById(R.id.txt_usernameforsql);
        btn=findViewById(R.id.btn_sql);
    }


}
