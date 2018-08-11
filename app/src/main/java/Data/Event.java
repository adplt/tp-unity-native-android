package Data;

public class Event{

    private int ID,TP;
    private String name,address,start,end,note,event_picture;

    public void setID(int ID){
        this.ID = ID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setStart(String start){
        this.start = start;
    }

    public void setEnd(String end){
        this.end = end;
    }

    public void setTP(int TP){
        this.TP=TP;
    }

    public void setNote(String note){
        this.note = note;
    }

    public void setEventPicture(String event_picture){
        this.event_picture=event_picture;
    }

    public int getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getStart(){
        return start;
    }

    public String getEnd(){
        return end;
    }

    public int getTP(){
        return TP;
    }

    public String getNote(){
        return note;
    }

    public String getEventPicture(){
        return event_picture;
    }

}
