/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author user01
 */
public class Event {

    private final Integer id;
    private String name;
    private String description;
    private Date date;
    private Time time;
    private int user_id;

    private List<Reservation> reservations;
    private int restaurant;
    private String image;
    private int maxReservations;
    private float price;
    private int nbReservation;
    private boolean annule;

    public Event(int id) {
        this.id = id;
    }

    public Event(Integer id, String name, String description, Date date, Time time, List<Reservation> reservations, int restaurant, String image, int maxReservations, float price, int nbReservation, boolean estAnnule, int user_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.reservations = reservations;
        this.restaurant = restaurant;
        this.image = image;
        this.maxReservations = maxReservations;
        this.price = price;
        this.nbReservation = nbReservation;
        this.annule = estAnnule;
        this.user_id = user_id;

    }

    @Override
    public String toString() {
        return "{ \n"
                + "  id: " + id + "\n"
                + "  name: " + name + " \n"
                + "  description: " + description + "\n"
                + "  date: " + date + "\n"
                + "  time: " + time + "\n"
                + "  restaurant: " + restaurant + "\n"
                + "  image: " + image + "\n"
                + "  maxReservations: " + maxReservations + "\n"
                + "  price: " + price + "\n"
                + "  nbReservation: " + nbReservation + "\n"
                + "  user_id: " + user_id + "\n"
                + "}";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setRestaurant(int restaurant) {
        this.restaurant = restaurant;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setMaxReservations(int maxReservations) {
        this.maxReservations = maxReservations;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setNbReservation(int nbReservation) {
        this.nbReservation = nbReservation;
    }

    public void setAnnule(boolean estAnnule) {
        this.annule = estAnnule;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public int getRestaurant() {
        return restaurant;
    }

    public String getImage() {
        return image;
    }

    public int getMaxReservations() {
        return maxReservations;
    }

    public float getPrice() {
        return price;
    }

    public int getNbReservation() {
        return nbReservation;
    }

    public boolean isAnnule() {
        return annule;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

}
