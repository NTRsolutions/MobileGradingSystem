package com.mobilegradingsystem.mobilegradingsystem.viewsAdapter.teacher;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.mobilegradingsystem.mobilegradingsystem.R;
import com.mobilegradingsystem.mobilegradingsystem.appModules.GlideApp;
import com.mobilegradingsystem.mobilegradingsystem.objectModel.AnnouncementObjectModel;
import com.mobilegradingsystem.mobilegradingsystem.objectModel.ProgramsObjectModel;
import com.mobilegradingsystem.mobilegradingsystem.objectModel.StudentProfileProfileObjectModel;
import com.mobilegradingsystem.mobilegradingsystem.objectModel.UserProfileObjectModel;
import com.mobilegradingsystem.mobilegradingsystem.objectModel.student.StudentClassObjectModel;
import com.mobilegradingsystem.mobilegradingsystem.objectModel.teacher.ParticipationCategoryGradeObjectModel;
import com.mobilegradingsystem.mobilegradingsystem.student.FeedBackAct;

import java.util.ArrayList;

import javax.annotation.Nullable;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Keji's Lab on 19/01/2018.
 */

public class AnnoucementListTeacherRecyclerViewAdapter
        extends RecyclerView.Adapter<AnnoucementListTeacherRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<AnnouncementObjectModel> announcementObjectModelArrayList = new ArrayList<>();
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title,description,feedBack,date,update;

        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            feedBack = (TextView) view.findViewById(R.id.feedBack);
            date = (TextView) view.findViewById(R.id.date);
            update = (TextView) view.findViewById(R.id.update);
        }
    }

    public AnnoucementListTeacherRecyclerViewAdapter(Context c, ArrayList<AnnouncementObjectModel> announcementObjectModels){

        this.announcementObjectModelArrayList = announcementObjectModels;
        this.context =c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_announcement_teacher,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final AnnouncementObjectModel announcementObjectModel = announcementObjectModelArrayList.get(position);
        holder.title.setText(announcementObjectModel.getTitle());
        holder.description.setText(announcementObjectModel.getDescription());
        holder.date.setText(announcementObjectModel.getTimeStamp()+"");
        holder.feedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FeedBackAct.class);
                i.putExtra("key",announcementObjectModel.getKey());
                context.startActivity(i);
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog(announcementObjectModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return announcementObjectModelArrayList.size();
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position, ProgramsObjectModel programsObjectModel);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickListener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private void updateDialog(final AnnouncementObjectModel announcementObjectModel){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dlg_update_announcement);
        Window window = dialog.getWindow();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.show();
        final EditText announcementTitle  = (EditText) dialog.findViewById(R.id.announcementTitle);
        final EditText description = (EditText) dialog.findViewById(R.id.desciption);
        try{
            announcementTitle.setText(announcementObjectModel.getTitle());
            description.setText(announcementObjectModel.getDescription());
        }catch (NullPointerException e){

        }
        dialog.findViewById(R.id.saveChanages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore.getInstance().collection("announcement")
                        .document(announcementObjectModel.getKey())
                        .update("title",announcementTitle.getText().toString(),
                                "description",description.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }
}


