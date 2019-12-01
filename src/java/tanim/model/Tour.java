/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanim.model;

/**
 *
 * @author khair
 */
public class Tour {
    public String tour_place,tour_date,user_id;

    public Tour(String user_id,String tour_place, String tour_date) {
        this.tour_place = tour_place;
        this.tour_date = tour_date;
        this.user_id = user_id;
    }
}
