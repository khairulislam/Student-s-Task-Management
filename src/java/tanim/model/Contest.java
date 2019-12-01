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
public class Contest {
    public String contest_id,contest_time,site;
    public Contest(String cid,String ctime,String csite){
        contest_id=cid;
        contest_time=ctime;
        site=csite;
    }
    
}
