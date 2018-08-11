package Data;

public class Comment{

    private int ID,score;
    private String no_prm,from,to,date_created,comment,url_image;

    public void setID(int ID){
        this.ID=ID;
    }

    public void setNoPRM(String no_prm){
        this.no_prm=no_prm;
    }

    public void setFrom(String from){
        this.from = from;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setURLImage(String url_image){
        this.url_image = url_image;
    }

    public void setDateCreated(String date_created) {
        this.date_created = date_created;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getID(){
        return ID;
    }

    public String getNoPRM(){
        return no_prm;
    }

    public String getFrom() {
        return from;
    }

    public int getScore(){
        return score;
    }

    public String getURLImage(){
        return url_image;
    }

    public String getDateCreated() {
        return date_created;
    }

    public String getComment() {
        return comment;
    }

}
