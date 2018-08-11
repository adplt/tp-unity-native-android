package Data;

import java.util.ArrayList;

public class Branch{

    private int ID;
    private String branch_name,code;

    public void setID(int ID){
        this.ID = ID;
    }

    public void setBranchName(String branch_name){
        this.branch_name = branch_name;
    }

    public void setCode(String code){
        this.code=code;
    }

    public int getID(){
        return ID;
    }

    public String getBranchName(){
        return branch_name;
    }

    public String getCode(){
        return code;
    }

}
