/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanim.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tanim.model.Arranges;
import tanim.model.Attends;
import tanim.model.Contest;
import tanim.model.Course;
import tanim.model.Does;
import tanim.model.Exam;
import tanim.model.Media;
import tanim.model.Participates;
import tanim.model.Reads;
import tanim.model.Registers;
import tanim.model.Takes;
import tanim.model.Tour;
import tanim.model.Watches;

/**
 *
 * @author khair
 */

public class DataAccess {
    
    String dbURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
    String id = "hr";
    String password = "hr";
    
    Connection conn=null;
    public DataAccess(){
        try
        {
           Class.forName("oracle.jdbc.OracleDriver");
           conn = DriverManager.getConnection(dbURL, id, password);
            if(conn!=null) System.out.println("Connection successfully established.");
            else System.out.println("Could not establish connection");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
           
    public String[] existUser(String id,String userType)
    {
        String s[] = new String[4];
        s[0]="";
        try
        {
            String query;
            if(userType.equals("student"))query= "select * from Student where student_id=?";
            else query= "select * from Instructor where instructor_id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            
            ResultSet rs = stmt.executeQuery();
              
            
            if(rs.next())
            {
                System.out.println(rs.getString(1)+" "+rs.getString(2));
                s[0]=rs.getString(1);
                s[1]=rs.getString(2);
                s[2] = rs.getString(3);
                s[3] = rs.getString(4);
                return s ;
            }
            else {
                System.out.println("No data found for "+id+" ");
                return s;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return s ;
        }       
    }
    
    public String createAccount(String id, String name, String password, String semester,String userType)
    {
        try
        {
           String sql = "{? = call createAccount(?,?,?,?,?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(2,id);
            stmt.setString(3,name);
            stmt.setString(4,password);
            stmt.setString(5,semester);
            stmt.setString(6,userType);
            
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR); 
            stmt.execute();
            
            String s= stmt.getString(1);
            return s;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }       
    }
    
    public String updateAccount(String id, String name, String password, String semester,String userType)
    {
        try
        {
           String sql = "{? = call updateAccount(?,?,?,?,?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(2,id);
            stmt.setString(3,name);
            stmt.setString(4,password);
            stmt.setString(5,semester);
            stmt.setString(6,userType);
            
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR); 
            stmt.execute();
            
            String s= stmt.getString(1);
            return s;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }       
    }
    
    public int createReads(String id,String entry_time, String start,String end,String book_name)
    {
        try
        {
            String insertCommand= "insert into reads values(?,?,?,?,?)";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            
            stmt.setString(1, id);
            stmt.setString(2, entry_time);
            stmt.setString(3,start);
            stmt.setString(4,end);
            stmt.setString(5, book_name);
                        
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }       
    }
    
    public ArrayList<Reads> getReads(String id)
    {
        ArrayList<Reads> reads = new ArrayList<Reads> ();
        try
        {
            String insertCommand= "select * from reads where student_id=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            
            stmt.setString(1, id);
                                    
            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                String name = result.getString("book_name");
                String date = result.getString("entry_time");
                String start = result.getString("starting");
                String end = result.getString("ending");
                Reads r= new Reads(id,name,date,start,end);
                reads.add(r);
            }
            return reads;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return reads;
        }      
    }
    
    public int deleteReads(String id,String entry_time, String start,String end,String book_name)
    {
     try
        {
            String insertCommand= "delete from reads where student_id=?";
            
            if(!entry_time.isEmpty())insertCommand+=" and entry_time=?";
            else insertCommand+=" and entry_time is null";
            
            if(!start.isEmpty())insertCommand+=" and starting=?";
            else insertCommand+=" and starting is null";
            
            if(!end.isEmpty())insertCommand+=" and ending=?";
            else insertCommand+=" and ending is null";
            
            insertCommand+=" and book_name =?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);     
            stmt.setString(1, id);
            stmt.setString(5,book_name);
            
            if(!entry_time.isEmpty())stmt.setString(2, entry_time);;
            if(!start.isEmpty())stmt.setString(3,start);
            if(!end.isEmpty())stmt.setString(4,end);
                        
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }       
    }
    
    public int deleteParticipates(String student_id,String contest_id, String contestant_id)
    {
     try
        {
            String insertCommand= "delete from Participates where student_id=? and contest_id=?  and contestant_id=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);     
            stmt.setString(1, student_id);
            stmt.setString(2,contest_id);
            stmt.setString(3, contestant_id);
           
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }       
    }
    
    
   public ArrayList<Registers> getRegisters(String id)
    {
        ArrayList<Registers> registers = new ArrayList<Registers> ();
        try
        {
            String insertCommand= "select * from registers where student_id=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            
            stmt.setString(1, id);
                                    
            ResultSet result = stmt.executeQuery();
            
            
            while(result.next()){
                String cid = result.getString("course_id");
                String grade = result.getString("grade");
                
                Registers reg= new Registers(cid,grade);
                registers.add(reg);
            }
            return registers;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return registers;
        }      
    }
   
    public ArrayList<Course> getCourses()
    {
        ArrayList<Course> courses = new ArrayList<Course> ();
        try
        {
            String insertCommand= "select * from course";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
                                                          
            ResultSet result = stmt.executeQuery();
                        
            while(result.next()){
                String cid = result.getString("course_id");
                String cname = result.getString("course_name");
                
                Course course = new Course(cname,cid);
                courses.add(course);
            }
            return courses;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return courses;
        }      
    }
    
    public int register(String id,String cid)
    {
        try
        {
            String insertCommand= "insert into registers(student_id,course_id) values(?,?)";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            
            stmt.setString(1, id);
            stmt.setString(2, cid);
                                   
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }       
    }
    public int unRegister(String id,String cid)
    {
        try
        {
            String insertCommand= "delete from Registers where student_id=? and course_id=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            
            stmt.setString(1, id);
            stmt.setString(2, cid);
                                   
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }       
    }
    
    public ArrayList<Exam> getExams(String cid)
    {
        ArrayList<Exam> exams = new ArrayList<Exam> ();
        try
        {
            String insertCommand= "select * from Exam where course_id=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1, cid);
                                                          
            ResultSet result = stmt.executeQuery();
                        
            while(result.next()){
                String etime = result.getString("exam_time");
                String etype = result.getString("exam_type");
                String syl = result.getString("syllabus");
                Exam exam = new Exam(cid,etime,etype,syl);
                exams.add(exam);
            }
            return exams;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return exams;
        }      
    }
    
    public ArrayList<Contest> getContests()
    {
        ArrayList<Contest> contests = new ArrayList<Contest> ();
        try
        {
            String insertCommand= "select * from Contest where departmental='y'";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            
            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                String contest_id = result.getString("contest_id");
                String site = result.getString("site");
                String contest_time = result.getString("contest_time");
                
                Contest contest = new Contest(contest_id,contest_time,site);
                contests.add(contest);
            }
            return contests;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return contests;
        }      
    }
    
    public Contest getContestsById(String contest_id,String instructor_id)
    {
        Contest contest=null;
        try
        {
            String insertCommand= "select * from Contest where contest_id=? and user_id=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1,contest_id);
            stmt.setString(2,instructor_id);
            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                String site = result.getString("site");
                String contest_time = result.getString("contest_time");
                
                contest = new Contest(contest_id,contest_time,site);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return contest;
    }
    
    public ArrayList<Participates> getParticipates(String student_id)
    {
        ArrayList<Participates> pars = new ArrayList<Participates> ();
        try
        {
            String insertCommand= "select * from Participates where student_id=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1, student_id);
            
            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                String contest_id = result.getString("contest_id");
                String contestant_id = result.getString("contestant_id");
                String res = result.getString("result");
                String team_name = result.getString("team_name");
                
                Participates p = new Participates(student_id,contest_id,contestant_id,res,team_name);
                pars.add(p);
            }
            return pars;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return pars;
        }      
    }
    
     public int createParticipates(String id,String contest_id, String contestant_id,String res,String team_name)
    {
        try
        {
            String insertCommand= "insert into Participates values(?,?,?,?,?)";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            
            stmt.setString(1, id);
            stmt.setString(2, contest_id);
            stmt.setString(3,contestant_id);
            stmt.setString(4,res);
            stmt.setString(5, team_name);
                        
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }       
    }
     
     public int createContests(String student_id,String contest_id, String site,String contest_time,String dept)
    {
        try
        {
            String insertCommand= "insert into Contest values(?,?,?,?,?)";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            
            stmt.setString(1, contest_id);
            stmt.setString(2, student_id);
            stmt.setString(3,site);
            stmt.setString(4,contest_time);
            stmt.setString(5,dept);
                                    
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }       
    }
     
      public int createArranges(String instructor_id,String contest_id)
    {
        try
        {
            String insertCommand= "insert into Arranges values(?,?)";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            
            stmt.setString(1, instructor_id);
            stmt.setString(2, contest_id);
                                                
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }       
    }
     
     public ArrayList<Watches> getWatches(String student_id)
    {
        ArrayList<Watches> watches = new ArrayList<Watches> ();
        try
        {
            String insertCommand= "select * from Watches where student_id=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1, student_id);
            
            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                String media_name = result.getString("media_name");
                String watch_date = result.getString("watch_date");
                               
                Watches watch = new Watches(media_name,watch_date,student_id);
                watches.add(watch);
            }
            return watches;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return watches;
        }      
    }
     
     public ArrayList<Tour> getTours()
    {
        ArrayList<Tour> tours = new ArrayList<Tour> ();
        try
        {
            String insertCommand= "select * from Tour";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
                                                          
            ResultSet result = stmt.executeQuery();
                        
            while(result.next()){
                String user_id= result.getString(1);
                String tour_place =result.getString(2);
                String tour_date=result.getString(3);
                Tour tour = new Tour(user_id,tour_place,tour_date);
                tours.add(tour);
            }
            return tours;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return tours;
        }      
    }
     public ArrayList<Does> getDoes(String student_id)
    {
        ArrayList<Does> does = new ArrayList<Does> ();
        try
        {
            String insertCommand= "select * from Does where student_id=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1,student_id);
                                                          
            ResultSet result = stmt.executeQuery();
                        
            while(result.next()){
                String tuition_id= result.getString(1);
                String tuition_place =result.getString(3);
                String salary=result.getString(4);
                Does d = new Does(tuition_id,student_id,tuition_place,salary);
                does.add(d);
            }
            return does;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return does;
        }      
    }
     public int createTakes(String instructor_id,String course_id)
    {
        try
        {
            String insertCommand= "insert into Takes values(?,?)";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            
            stmt.setString(1, instructor_id);
            stmt.setString(2, course_id);
            
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }       
    }
     
     public ArrayList<Takes> getTakes(String instructor_id)
    {
        ArrayList<Takes> takes = new ArrayList<Takes> ();
        try
        {
            String insertCommand= "select * from Takes where instructor_id=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1,instructor_id);
                                                          
            ResultSet result = stmt.executeQuery();
                        
            while(result.next()){
                String course_id= result.getString(2);
               
                Takes take = new Takes(instructor_id,course_id);
                takes.add(take);
            }
            return takes;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return takes;
        }      
    }
     
     public int deleteTakes(String instructor_id,String course_id)
    {
        try
        {
            String insertCommand= "delete from Takes where instructor_id=? and course_id=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            
            stmt.setString(1, instructor_id);
            stmt.setString(2, course_id);
            
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }       
    }
     
      public int deleteExams(String course_id,String exam_time)
    {
     try
        {
            String insertCommand= "delete from Exam where course_id=?";
            
            if(!exam_time.isEmpty())insertCommand+=" and exam_time=?";
            else insertCommand+=" and exam_time is null";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);     
            stmt.setString(1, course_id);
            
            if(!exam_time.isEmpty())stmt.setString(2, exam_time);;
            
                        
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }       
    }
      
      public String createExams(String course_id,String exam_time, String exam_type,String syllabus)
    {
        try
        {
            String sql = "{? = call addExam(?,?,?,?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(2,course_id);
            stmt.setString(3,exam_time);
            stmt.setString(4,exam_type);
            stmt.setString(5,syllabus);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR); 
            stmt.execute();
            
            String msg= stmt.getString(1);
            return msg;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }       
    }
      
      
      public ArrayList<Arranges> getArranges(String instructor_id)
    {
        ArrayList<Arranges> arranges = new ArrayList<Arranges> ();
        try
        {
            String insertCommand= "select * from Arranges where instructor_id=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1,instructor_id);
                                                          
            ResultSet result = stmt.executeQuery();
                        
            while(result.next()){
                String course_id= result.getString(2);
               
                Arranges arrange = new Arranges(instructor_id,course_id);
                arranges.add(arrange);
            }
            return arranges;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return arranges;
        }      
    }
      
      public ArrayList<Attends> getAttends(String course_id,String exam_time)
    {
        ArrayList<Attends> attends = new ArrayList<Attends> ();
        try
        {
            String insertCommand= "select * from Attends where course_id=? and exam_time=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1,course_id);
            stmt.setString(2,exam_time);
                                                          
            ResultSet result = stmt.executeQuery();
                        
            while(result.next()){
                String student_id= result.getString(1);
                String marks= result.getString(2);
               
               Attends attend= new Attends(student_id,marks,exam_time,course_id);
               attends.add(attend);
            }
            return attends;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return attends;
        }      
    }
      
      public String getMarksById(String student_id,String course_id,String exam_time)
    {
        try
        {
            String insertCommand= "select marks from Attends where student_id=? and course_id=? and exam_time=?";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1,student_id);
            stmt.setString(2,course_id);
            stmt.setString(3,exam_time);
                                                          
            ResultSet result = stmt.executeQuery();
             
            String marks="";
            if(result.next()){
                marks= result.getString(1);
            }
            return marks;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }      
    }
      
      public void addMarks(String student_id,String mark, String exam_time,String course_id)
    {
        try
        {
            String sql = "call addMarks(?,?,?,?)";
            CallableStatement stmt = conn.prepareCall(sql);
            
            stmt.setString(1,student_id);
            stmt.setString(2,mark);
            stmt.setString(3,exam_time);
            stmt.setString(4,course_id);
            
            stmt.execute();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }       
    }
      
}
