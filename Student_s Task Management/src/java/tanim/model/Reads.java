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
public class Reads {
    public String student_id,book_name,entry_time,starting,ending;
    public Reads(String id,String name,String date,String start,String end){
        student_id=id;
        book_name=name;
        entry_time=date;
        starting=start;
        ending =end;
    }    
}
