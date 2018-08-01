package com.example.lenovo.edulight;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Category> categoryList;
    private Typeface thinFont,normalFont,boldFont;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subjectName, teacherName;
        public ImageView subjectIcon;

        public MyViewHolder(View view) {
            super(view);
            subjectName = view.findViewById(R.id.category_item_title);
            teacherName = view.findViewById(R.id.category_item_teacher_name);
            subjectIcon = view.findViewById(R.id.category_item_icon);
            thinFont = Typeface.createFromAsset(context.getAssets(),"oxygen.light.ttf");
            normalFont = Typeface.createFromAsset(context.getAssets(),"oxygen.regular.ttf");
            boldFont = Typeface.createFromAsset(context.getAssets(),"oxygen.bold.ttf");
            subjectName.setTypeface(normalFont);
            teacherName.setTypeface(thinFont);
        }
    }


    public CategoryRecyclerViewAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item_row, parent, false);

        return new MyViewHolder(itemView);
    }
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.subjectName.setText(categoryList.get(position).getSubjectName());
        holder.teacherName.setText(categoryList.get(position).getTeacherName());
        holder.subjectIcon.setImageResource(categoryList.get(position).getSubjectIcon());
    }
    @Override
    public int getItemCount() {
        return categoryList.size();
    }

}


