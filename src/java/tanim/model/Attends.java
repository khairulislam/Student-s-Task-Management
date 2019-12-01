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
public class Attends {
    public String student_id,marks,exam_time,course_id;

    public Attends(String student_id, String marks, String exam_time, String course_id) {
        this.student_id = student_id;
        this.marks = marks;
        this.exam_time = exam_time;
        this.course_id = course_id;
    }
    
}
