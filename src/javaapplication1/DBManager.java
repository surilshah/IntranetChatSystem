package javaapplication1;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBManager {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DBManager(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ics","root","");
            st=con.createStatement();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void close(){
        if(rs!=null){
            try{
                rs.close();
            }catch(SQLException e){
                
            }
        }
        if(st!=null){
            try{
                st.close();
            }catch(SQLException e){
                
            }
        }        
        if(con!=null){
            try{
                con.close();
            }catch(SQLException e){
                
            }
        }
    }
    
    public void getData(){
        try{
            String query="select * from reguser";
            rs=st.executeQuery(query);
            while(rs.next()){
                String username=rs.getString("username");
                String pass=rs.getString("password");
                String name=rs.getString("fullname");
                System.out.println("User Name:"+username+" Password:"+pass+" Full Name: "+name);
            }            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
     public void addData(String s){
        try {
            st.executeUpdate(s);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public ResultSet getResult(String query){
        try{
            rs=st.executeQuery(query);
            if(rs!=null)
                return rs;
        }catch(Exception e){
            
        }
        return null;
    }
    
    public boolean login(String user,String pass){
        String query="select username,password from reguser";
        try{
            rs=st.executeQuery(query);
            while(rs.next()){
                String u=rs.getString("username");
                String p=rs.getString("password");
                if(user.equals(u)&&pass.equals(p))
                    return true;
            }
        }catch(Exception e){
            
        }
        return false;
    }
}