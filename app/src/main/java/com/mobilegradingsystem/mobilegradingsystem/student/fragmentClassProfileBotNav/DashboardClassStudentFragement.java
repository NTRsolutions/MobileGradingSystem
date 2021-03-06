package com.mobilegradingsystem.mobilegradingsystem.student.fragmentClassProfileBotNav;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobilegradingsystem.mobilegradingsystem.R;
import com.mobilegradingsystem.mobilegradingsystem.objectModel.teacher.TeacherClassObjectModel;
import com.mobilegradingsystem.mobilegradingsystem.student.ClssProfileStudentBotNav;
import com.mobilegradingsystem.mobilegradingsystem.teacher.ClssProfileTeacherBotNav;
import com.mobilegradingsystem.mobilegradingsystem.teacher.fragment.ClassProfileBotBNav.ItemListDialogFragment;

import javax.annotation.Nullable;

public class DashboardClassStudentFragement extends Fragment {
    FirebaseFirestore db;
    ClssProfileStudentBotNav act;
    BottomSheetBehavior bottomSheetBehavior;
    ItemListDialogFragment itemListDialogFragment;
    TextView studentNumbers,className,announcementNumber;
    EditText title,desciption;
    String loading = "loading...";

    public DashboardClassStudentFragement(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        act = (ClssProfileStudentBotNav) getActivity();
        db = FirebaseFirestore.getInstance();
        View view = inflater.inflate(R.layout.frag_class_dashboard_student, container, false);



        return view;
    }
}
