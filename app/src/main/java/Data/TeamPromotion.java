package Data;

import java.lang.String;

public class TeamPromotion{

    private String ID,name,gender,join_date,password,email,address,phone_number,work_number,staff_handle,birthday,url_image,url_background;
    private int score,id_degree,id_branch;

    public String setID(String ID){
        this.ID = ID;
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setPhoneNumber(String phone_number){
        this.phone_number = phone_number;
    }

    public void setWorkNumber(String work_number){
        this.work_number = work_number;
    }

    public void setDegree(int id_degree){
        this.id_degree = id_degree;
    }

    public void setBranch(int id_branch){
        this.id_branch=id_branch;
    }

    public void setJoinDate(String join_date){
        this.join_date = join_date;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    public void setURLImage(String url_image){
        this.url_image = url_image;
    }

    public void setURLBackground(String url_background){
        this.url_background = url_background;
    }

    public String getID(){
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getGender(){
        return gender;
    }

    public int getScore(){
        return score;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail(){
        return email;
    }

    public String getAddress(){
        return address;
    }

    public String getPhoneNumber(){
        return phone_number;
    }

    public String getWorkNumber(){
        return work_number;
    }

    public int getDegree(){
        return id_degree;
    }

    public int getBranch(){
        return id_branch;
    }

    public String getJoinDate(){
        return join_date;
    }

    public String getStaffHandle(){
        return staff_handle;
    }

    public String getBirthday(){
        return birthday;
    }

    public String getURLImage(){
        return url_image;
    }

    public String getURLBackground(){
        return url_background;
    }

}