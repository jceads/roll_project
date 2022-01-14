package com.example.proje_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class activityYoklama extends AppCompatActivity {

    TextView txt_studentName,txt_queueofStudent;
    Button btn_var,btn_yok;
    ArrayList<Student> students = new ArrayList<>();
    int index = 0;
    Boolean situtation=false;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoklama);
        inits();

            btn_var.setOnClickListener(v -> {
                if(index <= (students.size()-1)){                               //Listenin dışına çıkıp exception vermemesi için kontrol kullanılır.
                    txt_queueofStudent.setText((index+1) +"/"+(students.size())); //Listedeki elemanın kaçıncı sırada olduğunu gösterir
                    situtation = true;                                            // Elamanın yoklamasına var geçer.
                                                                                    // Burada true =var, false = yok durumlarını temsil eder
                    isClicked(true);
                    writeHere(students,index,situtation);                       //Elemanı, sırasını ve durumunu kaydeden metot
                    index++;                                                    //bir sonraki elemana geçmesi için manuel olarak index artılıyor.
                    txt_queueofStudent.setText((index+1) +"/"+(students.size()));//Ekranda isim değişikliği karmaşası olmaması için isim hemen değiştirilir.
                    changeStudent(index);                                        //İndexdeki eleman çağırılır
                    changeActivity(index);                                      //Listenin sonuna gelindiğinin kontrolünü ve activity değişiminin kontrolünü yapar.
                   // Toast.makeText(activityYoklama.this,"Ogrenciye "+situtation+" durumu verildi",Toast.LENGTH_SHORT).show();
                }
                else
                    txt_studentName.setText(index);

            });

            btn_yok.setOnClickListener(v -> {
                if(index <= (students.size()-1)){
                    txt_queueofStudent.setText((index+1) +"/"+(students.size()));
                    situtation =false;
                    isClicked(true);
                    writeHere(students,index,situtation);   //YOK demesi
                    index++;
                    txt_queueofStudent.setText((index+1) +"/"+(students.size()));
                    changeStudent(index);
                    changeActivity(index);
                }

            });

    }

    private void changeActivity(int index)
    {
        if(index == 12)
        {
            Intent intent = new Intent(this,OlmayanlarListesi.class);
            intent.putExtra("StudentExtra",students);
            startActivity(intent);
            finish();
        }
    }


    private void changeStudent(int index) {
        if(index < (students.size())){
            txt_studentName.setText(students.get(index).getName());
        }
        else{
            changeActivity(index);
            txt_studentName.setText("Ogrenci Bitti");
        }
    }


    private void writeHere(ArrayList<Student> students,int index,boolean situtation) //yoklama alır
    {
        students.get(index).setStudentRoll(situtation);
    }


    private void inits(){
    txt_studentName = findViewById(R.id.txt_studentName);
    txt_queueofStudent = findViewById(R.id.txt_queueofStudent);
    btn_var = findViewById(R.id.btn_here);
    btn_yok = findViewById(R.id.btn_absent);
    parseXML();
      if(students !=null){
          txt_studentName.setText(students.get(0).getName());
      }
        txt_queueofStudent.setText((index+1) +"/"+(students.size())); //Sınıf sırasını sayısa olarak yazar
    }


    private void parseXML()
    {
        XmlPullParserFactory parserFactory; //gerekli referans burada oluşturuldu.
        try {                                                  //Az sonra yapılacak işlemlerde bir hata olmasında programı kırabileceği
                                                                // için try catch bloğu içerisine alınması gerekmektedir.
            parserFactory= XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream inputStream = getAssets().open("database.xml"); // verileri çekebileceğimiz objeye dosyayı gösteriyoruz
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(inputStream,null);

            processParsing(parser); // burada farklı bi metod yazarak daha anlaşılır bir kod olması amaçlanmıştır.

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    private void processParsing(XmlPullParser parser) throws XmlPullParserException, IOException
    {
        int eventType = parser.getEventType();
        Student currentStudent = null;

        while(eventType!=XmlPullParser.END_DOCUMENT)
        {
            String eltName =null;
            switch (eventType)
            {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();
                    if("student".equals(eltName))
                    {
                        currentStudent = new Student();
                        students.add(currentStudent);
                    }
                    else if(currentStudent != null)
                    {
                        if("name".equals(eltName))
                        {
                            currentStudent.setName(parser.nextText());
                        }
                    }
                    else if("studentID".equals(eltName))
                    {
                            currentStudent.setStudentID(parser.nextText());
                    }
                    break;
            }
            eventType = parser.next();
        }
        printStudent(students);
    }

    public void printStudent(ArrayList<Student> students){

        StringBuilder builder = new StringBuilder();

        for(Student student : students){
            if(isClicked(true)){
                builder.append(student.getName()).append("\n");
                //txt.setText(builder.toString());
                isClicked(false);
            }

        }
    }

    private boolean isClicked(boolean situtation) {
        return situtation;
    }
}