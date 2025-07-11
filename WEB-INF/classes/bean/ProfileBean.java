package bean;

import java.io.Serializable;

public class ProfileBean implements Serializable{
    // メンバ変数　インスタンス変数
    private String userid = null;
    private String pass = null;
    private String question = null;
    private String answer = null;
    private String icon = null;
    private String color = null;
    private String name = null;
    private long snum = 0L;
    private boolean snumpublic = false;
    private String gender = null;
    private String bloodtype = null;
    private String[] hobby = null;
    private String introduction = null;
    private String instagram = null;
    private String x = null;
    private String tiktok = null;

    public ProfileBean(){

    }

    // seterメソッドの定義
    public void setUserid(String userid){
        this.userid = userid;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public void setQuestion(String question){
        this.question = question;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSnum(long snum){
        this.snum = snum;
    }
    public void setSnumpublic(boolean snumpublic){
        this.snumpublic = snumpublic;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setBloodtype(String bloodtype){
        this.bloodtype = bloodtype;
    }
    public void setHobby(String[] hobby){
        this.hobby = hobby;
    }
    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }
    public void setInstagram(String instagram){
        this.instagram = instagram;
    }
    public void setX(String x){
        this.x = x;
    }
    public void setTiktok(String tiktok){
        this.tiktok = tiktok;
    }

    // geterメソッドの定義
    public String getUserid(){
        return userid;
    }
    public String getPass(){
        return pass;
    }
    public String getQuestion(){
        return question;
    }
    public String getAnswer(){
        return answer;
    }
    public String getIcon(){
        return icon;
    }
    public String getColor(){
        return color;
    }
    public String getName(){
        return name;
    }
    public long getSnum(){
        return snum;
    }
    public boolean getSnumpublic(){
        return snumpublic;
    }
    public String getGender(){
        return gender;
    }
    public String getBloodtype(){
        return bloodtype;
    }
    public String[] getHobby(){
        return hobby;
    }
    public String getIntroduction(){
        return introduction;
    }
    public String getInstagram(){
        return instagram;
    }
    public String getX(){
        return x;
    }
    public String getTiktok(){
        return tiktok;
    }
}