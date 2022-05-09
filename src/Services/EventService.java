package Services;

import Models.Event;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Services.Iservice;
import Utils.DataSource;

/**
 *
 * @author user01
 */
public class EventService implements Iservice<Event> {

    Connection connection;

    public EventService() {
        connection = DataSource.getInstance().getCnx();

    }

    @Override
    public void ajouter(Event e) {
        System.out.println("add event ");
        System.out.println(e);
        try {
            String req = "INSERT INTO evennement (restaurant_id,nom, date, image, description, max_reservation, prix, user_id) VALUES ("
                    + "'" + e.getRestaurant() + "', "
                    + "'" + e.getName() + "', "
                    + "'" + e.getDate() + " " + e.getTime() + "', "
                    + "'" + e.getImage() + "' , "
                    + "'" + e.getDescription() + "', "
                    + e.getMaxReservations() + ","
                    + e.getPrice() + ","
                    + e.getUser_id()
                    + ")";
            Statement st = connection.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    @Override
    public void modifier(Event e) {
        int annule = (e.isAnnule() == true) ? 1 : 0;

        try {
            String req = "UPDATE evennement "
                    + "SET `nom`=" + "'" + e.getName() + "'"
                    + ",`description`=" + "'" + e.getDescription() + "'"
                    + ",`restaurant_id`=" + e.getRestaurant()
                    + ",`max_reservation`=" + e.getMaxReservations()
                    + ",`prix`=" + e.getPrice()
                    + ",`nb_reservations`=" + e.getNbReservation()
                    + ",`image`=" + "'" + e.getImage() + "'"
                    + ",`date`=" + "'" + e.getDate() + " " + e.getTime() + "'"
                    + ",`est_annule`=" + "'" + annule + "'"
                    + " WHERE id=" + e.getId();
            Statement st = connection.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    @Override
    public void supprimer(Event e) {
        try {
            String req = "delete from evennement where id = " + e.getId();
            Statement st = connection.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "delete from evennement where id = " + id;
            Statement st = connection.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public List<Event> getAll() {
        ReservationService reservationService = new ReservationService();
        List<Event> list = new ArrayList<>();
        try {
            String req = "select * from evennement";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Event e = new Event(rs.getInt("id"));
                e.setName(rs.getString("nom"));
                e.setDescription(rs.getString("description"));
                e.setDate(rs.getDate("date"));
                e.setTime(rs.getTime("date"));
                e.setRestaurant(rs.getInt("restaurant_id"));
                e.setReservations(new ArrayList<>());
                e.setImage(rs.getString("image"));
                e.setMaxReservations(rs.getInt("max_reservation"));
                e.setPrice(rs.getInt("prix"));
                e.setNbReservation(rs.getInt("nb_reservations"));
                e.setReservations(reservationService.recuperer(e));
                e.setAnnule(rs.getBoolean("est_annule"));
                e.setUser_id(rs.getInt("user_id"));

                list.add(e);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return list;
    }

    public List<Event> recuperer() {
        return getAll();
    }

    public Event recuperer(int id) {
        ReservationService reservationService = new ReservationService();
        Event e = new Event(id);

        try {

            String req = "select * from evennement where id=" + id;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                e.setName(rs.getString("nom"));
                e.setDescription(rs.getString("description"));
                e.setDate(rs.getDate("date"));
                e.setTime(rs.getTime("date"));
                e.setRestaurant(rs.getInt("restaurant_id"));
                e.setReservations(new ArrayList<>());
                e.setImage(rs.getString("image"));
                e.setMaxReservations(rs.getInt("max_reservation"));
                e.setPrice(rs.getInt("prix"));
                e.setNbReservation(rs.getInt("nb_reservations"));
                e.setReservations(reservationService.recuperer(e));
                e.setAnnule(rs.getBoolean("est_annule"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return e;
    }

    public List<Event> getUserEvents(int user_id) {
        ReservationService reservationService = new ReservationService();
        List<Event> list = new ArrayList<>();
        try {
            String req = "select * from evennement where user_id=" + user_id + " and est_annule=0";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Event e = new Event(rs.getInt("id"));
                e.setName(rs.getString("nom"));
                e.setDescription(rs.getString("description"));
                e.setDate(rs.getDate("date"));
                e.setTime(rs.getTime("date"));
                e.setRestaurant(rs.getInt("restaurant_id"));
                e.setReservations(new ArrayList<>());
                e.setImage(rs.getString("image"));
                e.setMaxReservations(rs.getInt("max_reservation"));
                e.setPrice(rs.getInt("prix"));
                e.setNbReservation(rs.getInt("nb_reservations"));
                e.setReservations(reservationService.recuperer(e));
                e.setAnnule(rs.getBoolean("est_annule"));

                list.add(e);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return list;
    }

}
