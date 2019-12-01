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
public class Participates {
    public String student_id,contest_id,contestant_id,result,team_name;

    public Participates(String student_id, String contest_id, String contestant_id, String result, String team_name) {
        this.student_id = student_id;
        this.contest_id = contest_id;
        this.contestant_id = contestant_id;
        this.result = result;
        this.team_name = team_name;
    }
   
       
}
