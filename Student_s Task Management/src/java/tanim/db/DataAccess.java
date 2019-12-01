/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanim.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tanim.model.Contest;
import tanim.model.Course;
import tanim.model.Exam;
import tanim.model.Participates;
import tanim.model.Reads;
import tanim.model.Registers;

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
           
    public String[] existUser(String id,String password,String userType)
    {
        String s[] = new String[3];
        s[0]="";
        try
        {
            String query;
            if(userType.equals("student"))query= "select * from Student where student_id=? and password=?";
            else query= "select * from Instructor where instructor_id=? and instructor_password=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
              
            
            if(rs.next())
            {
                System.out.println(rs.getString(1)+" "+rs.getString(2));
                s[0]=rs.getString(1);
                s[1]=rs.getString(2);
                s[2] = userType;
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
    
    public int createAccount(String id, String name, String password, String semester,String userType)
    {
        try
        {
            String insertCommand;
            if(userType.equals("student"))insertCommand= "insert into student values(?,?,?,?)";
            else insertCommand= "insert into instructor values(?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, password);
            
            if(userType.equals("student"))stmt.setString(4, semester);
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
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
            String insertCommand= "select * from Contest where student_id is null";
           
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
     
     public int createContests(String student_id,String contest_id, String site,String contest_time)
    {
        try
        {
            String insertCommand= "insert into Contest values(?,?,?,?)";
           
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            
            stmt.setString(2, student_id);
            stmt.setString(1, contest_id);
            stmt.setString(3,site);
            stmt.setString(4,contest_time);
                                    
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }       
    }
}
