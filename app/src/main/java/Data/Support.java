package Data;

public class Support{

    private int ID;
    private String support_start,support_end,job_description,support_type; //only indoor & outdoor

    public void setID(int ID){
        this.ID = ID;
    }

    public void setSupportStart(String support_start){
        this.support_start = support_start;
    }

    public void setSupportEnd(String support_end){
        this.support_end = support_end;
    }

    public void setJobDescription(String job_description){
        this.job_description = job_description;
    }

    public void setSupportType(String support_type){
        this.support_type = support_type;
    }

    public int getID(){
        return ID;
    }

    public String getSupportStart(){
        return support_start;
    }

    public String getSupportEnd(){
        return support_end;
    }

    public String getJobDescription(){
        return job_description;
    }

    public String getSupportType(){
        return support_type;
    }
}