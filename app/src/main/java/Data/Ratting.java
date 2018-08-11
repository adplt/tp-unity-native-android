package Data;

public class Ratting{

    private int ID;
    private String from,to,date_created,comment;
    private float time_management,initiative,responsible;

    public void setID(int ID){
        this.ID=ID;
    }

    public void setFrom(String from){
        this.from = from;
    }

    public void setTo(String to){
        this.to = to;
    }

    public void setDateCreated(String date_created) {
        this.date_created = date_created;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTimeManagement(float time_management){
        this.time_management=time_management;
    }

    public void setInitiative(float initiative){
        this.initiative=initiative;
    }

    public void setResponsible(float responsible){
        this.responsible=responsible;
    }

    public int getID(){
        return ID;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDateCreated() {
        return date_created;
    }

    public String getComment() {
        return comment;
    }

    public float getTimeManagement(){
        return time_management;
    }

    public float getInitiative(){
        return initiative;
    }

    public float getResponsible(){
        return responsible;
    }

}