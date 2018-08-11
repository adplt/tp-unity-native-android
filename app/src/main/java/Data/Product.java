package Data;

public class Product{

    private int ID;
    private String branch_name,faculty_name,degree_name,note;

    public void setID(int ID){
        this.ID = ID;
    }

    public void setBranchName(String branch_name){
        this.branch_name = branch_name;
    }

    public void setFacultyName(String faculty_name){
        this.faculty_name = faculty_name;
    }

    public void setDegreeName(String degree_name){
        this.degree_name = degree_name;
    }

    public void setNote(String note){
        this.note = note;
    }

    public int getID(){
        return ID;
    }

    public String getBranchName(){
        return branch_name;
    }

    public String getFacultyName(){
        return faculty_name;
    }

    public String getDegreeName(){
        return degree_name;
    }

    public String getNote(){
        return note;
    }


}
