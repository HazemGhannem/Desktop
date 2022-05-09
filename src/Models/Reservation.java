package Models;

import java.sql.Date;
import java.sql.Time;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author user01
 */
public class Reservation {

    private final int id;
    private Date date;
    private Time time;
    private boolean isPaid;
    private String code;
    private String email;

    private int evennementID;

    public Reservation(int id) {
        this.id = id;

    }

    public Reservation(int id, Date date, Time time, boolean isPaid, String code, int evennementID, String email) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.isPaid = isPaid;
        this.code = code;
        this.evennementID = evennementID;
        this.email = email;
    }

    @Override
    public String toString() {
        return "{ \n"
                + "  id: " + id + "\n"
                + "  date: " + date + "\n"
                + "  time: " + time + "\n"
                + "  evennementID: " + evennementID + "\n"
                + "  isPaid: " + isPaid + "\n"
                + "  code: " + code + "\n"
                + "  email: " + email + "\n"
                + "} \n";

    }

    public int getId() {
        return id;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public String getCode() {
        return code;
    }

    public int getEvennementID() {
        return evennementID;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getEmail() {
        return email;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setEvennementID(int evennementID) {
        this.evennementID = evennementID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
