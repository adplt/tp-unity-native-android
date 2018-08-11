package Data;

import java.util.ArrayList;

public class Faculty{

    private int ID;
    private String faculty_name,note;

    public void setID(int ID){
        this.ID = ID;
    }

    public void setFacultyName(String faculty_name){
        this.faculty_name = faculty_name;
    }

    public void setNote(String note){
        this.note = note;
    }

    public int getID(){
        return ID;
    }

    public String getFacultyName(){
        return faculty_name;
    }

    public String getNote(){
        return note;
    }

}
