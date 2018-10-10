package com.mobilegradingsystem.mobilegradingsystem.teacher;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobilegradingsystem.mobilegradingsystem.R;
import com.mobilegradingsystem.mobilegradingsystem.objectModel.student.StudentClassObjectModel;
import com.mobilegradingsystem.mobilegradingsystem.objectModel.teacher.ParticipationCategoryGradeObjectModel;
import com.mobilegradingsystem.mobilegradingsystem.viewsAdapter.teacher.StudentListTeacherRecyclerViewAdapter;
import com.mobilegradingsystem.mobilegradingsystem.viewsAdapter.teacher.classRecordAdapters.ParticipationStudentsClassRecordRecyclerViewAdapter;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class ClassPartListStudentsAct extends AppCompatActivity {
    ArrayList<StudentClassObjectModel> studentClassObjectModelArrayList = new ArrayList<>();
    ParticipationStudentsClassRecordRecyclerViewAdapter studentListTeacherRecyclerViewAdapter;
    ParticipationCategoryGradeObjectModel participationCategoryGradeObjectModel;
    FirebaseFirestore db;
    FirebaseAuth auth;
    String classKey;
    Context context;
    RecyclerView studentList;
    String partKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_part_list_students);
        classKey = getIntent().getExtras().getString("key");
        partKey = getIntent().getExtras().getString("partKey");
        studentList = (RecyclerView) findViewById(R.id.studentList);
        db  =FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        context = this;
        studentListTeacherRecyclerViewAdapter  = new ParticipationStudentsClassRecordRecyclerViewAdapter(context,studentClassObjectModelArrayList,partKey);
        studentList.setLayoutManager(new LinearLayoutManager(context));
        studentList.setAdapter(studentListTeacherRecyclerViewAdapter);
        getStudents();

    }
    void getStudents(){
        db.collection("studentClasses")
                .whereEqualTo("status","approved").whereEqualTo("classCode",classKey).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                studentClassObjectModelArrayList.clear();
                for (DocumentSnapshot documentSnapshot:queryDocumentSnapshots.getDocuments()){
                    studentClassObjectModelArrayList.add(documentSnapshot.toObject(StudentClassObjectModel.class));
                }
                studentListTeacherRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }
}
