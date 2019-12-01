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
public class Exam {
    public String course_id,exam_time,exam_type,syllabus;
    public Exam(String cid,String etime,String etype,String syl){
        course_id=cid;
        exam_time=etime;
        exam_type=etype;
        syllabus=syl;
    }
    
}
