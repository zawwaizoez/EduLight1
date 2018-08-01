package com.example.lenovo.edulight;

public class Category {
    String subject_name,teacher_name;
    int subject_icon;
    public Category()
    {

    }
    public Category(String subject_name, String teacher_name, int subject_icon)
    {
        this.subject_name = subject_name;
        this.teacher_name = teacher_name;
        this.subject_icon = subject_icon;
    }


    public String getSubjectName() {
        return subject_name;
    }

    public String getTeacherName() {
        return teacher_name;
    }

    public int getSubjectIcon() {
        return subject_icon;
    }

    public void setSubjectName(String subject_name) {
        this.subject_name = subject_name;
    }

    public void setTeacherName(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public void setSubjectIcon(int subject_icon) {
        this.subject_icon = subject_icon;
    }
}
