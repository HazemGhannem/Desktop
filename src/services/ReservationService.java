/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;

/**
 *
 * @author user01
 */
public class ReservationService implements Iservice<Reservation> {

    Connection connection;

    public ReservationService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Reservation r) {
        int estPaye = (r.isIsPaid() == true) ? 1 : 0;

        try {
            String req = "INSERT INTO reservation (evennement_id , rendez_vous, est_paye, code) VALUES ("
                    + r.getEvennementID() + ","
                    + "'" + r.getDate() + " " + r.getTime() + "', "
                    + estPaye + ","
                    + "'" + r.getCode() + "' "
                    + ")";
            Statement st = connection.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void modifier(Reservation r) {
        int estPaye = (r.isIsPaid() == true) ? 1 : 0;
        try {
            String req = "UPDATE reservation "
                    + "SET `evennement_id`=" + r.getEvennementID()
                    + ",`rendez_vous`=" + "'" + r.getDate() + " " + r.getTime() + "'"
                    + ",`est_paye`=" + estPaye
                    + ",`code`=" + "'" + r.getCode() + "'"
                    + " WHERE id=" + r.getId();
            Statement st = connection.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "delete from reservation where id = " + id;
            Statement st = connection.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public List<Reservation> recuperer() {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "select * from reservation";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Reservation r = new Reservation(rs.getInt("id"));
                r.setDate(rs.getDate("rendez_vous"));
                r.setTime(rs.getTime("rendez_vous"));

                r.setIsPaid(rs.getBoolean("est_paye"));
                r.setCode(rs.getString("code"));
                r.setEvennementID(rs.getInt("evennement_id"));
                list.add(r);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return list;
    }

    public List<Reservation> recuperer(Event e) {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "select * from reservation where evennement_id=" + e.getId();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Reservation r = new Reservation(rs.getInt("id"));
                r.setDate(rs.getDate("rendez_vous"));
                r.setTime(rs.getTime("rendez_vous"));

                r.setIsPaid(rs.getBoolean("est_paye"));
                r.setCode(rs.getString("code"));
                r.setEmail(rs.getString("email"));
                r.setEvennementID(rs.getInt("evennement_id"));

                list.add(r);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return list;
    }
}
