package Data;

public class Absence{

    private int ID;
    private String absence_in,absence_out,info_support;

    public void setID(int ID){
        this.ID = ID;
    }

    public void setAbsenceIn(String absence_in){
        this.absence_in = absence_in;
    }

    public void setAbsenceOut(String absence_out){
        this.absence_out = absence_out;
    }

    public void setInfoSupport(String info_support){
        this.info_support = info_support;
    }

    public int getID(){
        return ID;
    }

    public String getAbsenceIn() {
        return absence_in;
    }

    public String getAbsenceOut() {
        return absence_out;
    }

    public String getInfoSupport() {
        return info_support;
    }
}
