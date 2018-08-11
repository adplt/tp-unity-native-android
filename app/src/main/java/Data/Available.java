package Data;

public class Available{

    private int ID;
    private String start, until;

    public void setID(int ID){
        this.ID = ID;
    }

    public void setStart(String start){
        this.start = start;
    }

    public void setUntil(String until){
        this.until = until;
    }

    public int getID(){
        return ID;
    }

    public String getStart(){
        return start;
    }

    public String getUntil(){
        return until;
    }
}
