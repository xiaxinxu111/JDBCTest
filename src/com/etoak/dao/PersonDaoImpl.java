package com.etoak.dao;

import com.etoak.factory.Factory;
import com.etoak.po.Person;
/*import com.sun.org.apache.bcel.internal.generic.Select;*/

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDaoIf{

    Connection con;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;

    @Override
    public boolean addPerson(Person per) {
        try{
            String sql = "insert into person values(null,?,?,?,?,?,?,?,?,?,'1')";
            con = Factory.getCon();
            pst = con.prepareStatement(sql);

            pst.setString(1,per.getName());
            pst.setString(2,per.getPass());
            pst.setString(3,per.getGender());
            pst.setInt(4,per.getAge());
            pst.setString(5,per.getHobby());
            pst.setDouble(6,per.getSalary());
            pst.setString(7,per.getEmail());
            pst.setDate(8,new java.sql.Date(per.getBirth().getTime()));
            pst.setString(9,per.getLevel());

            return pst.executeUpdate() == 1;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            Factory.close(null,pst,con);
        }
    }

    @Override
    public boolean delPersonById(Integer id) {
        try{
            String sql = "delete from person where id = ?";
            con = Factory.getCon();
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            return pst.executeUpdate() == 1;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            Factory.close(null,pst,con);
        }
    }

    @Override
    public boolean queryName(String name) {
       try{
           String sql = "select * from person where name = ?";
           con = Factory.getCon();
           pst = con.prepareStatement(sql);
           pst.setString(1,name);
           rs = pst.executeQuery();	

           return rs.next();
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }finally{
            Factory.close(rs,pst,con);
       }
    }

    @Override
    public Person queryNameAndPass(String name, String pass) {
        try{
            String sql = "select * from person where name = ? and pass = ?";
            con = Factory.getCon();
            pst = con.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,pass);
            rs = pst.executeQuery();

            if(rs.next()){
              return new Person(rs.getInt(1),rs.getString(2),rs.getString(3)
                      ,rs.getString(4),rs.getInt(5),rs.getString(6),rs.getDouble(7)
                      ,rs.getString(8),rs.getDate(9),rs.getString(10),rs.getString(11));
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            Factory.close(rs,pst,con);
        }
    }

    @Override
    public Integer queryCount() {
        try{
            /*1)写sql语句*/
            String sql = "select count(*) from person where status = '1'";
            /*2）创建链接 */
            con = Factory.getCon();
            /*3）创建执行器  并加载sql语句*/
            pst = con.prepareStatement(sql);
            /*4）如果有占位符  先将占位符进行赋值*/
            /*5）获取结果集*/
            rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            Factory.close(rs,pst,con);
        }
    }

    @Override
    public List<Person> queryPage(Integer index, Integer max) {
        try{
            String sql = "select * from person where status= '1' limit ?,?";
            con = Factory.getCon();
            pst = con.prepareStatement(sql);
            pst.setInt(1,index);
            pst.setInt(2,max);
            rs = pst.executeQuery();
            List<Person> list = new ArrayList<>();
            while(rs.next()){
                list.add(new Person(rs.getInt(1),rs.getString(2),rs.getString(3)
                        ,rs.getString(4),rs.getInt(5),rs.getString(6),rs.getDouble(7)
                        ,rs.getString(8),rs.getDate(9),rs.getString(10),rs.getString(11)));
            }
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            Factory.close(rs,pst,con);
        }
    }

    @Override
    public List<Person> queryAll() {
        try{
            String sql = "select * from person where status='1'";
            con = Factory.getCon();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            List<Person> list = new ArrayList<>();
            while(rs.next()){
                list.add(new Person(rs.getInt(1),rs.getString(2),rs.getString(3)
                ,rs.getString(4),rs.getInt(5),rs.getString(6),rs.getDouble(7)
                ,rs.getString(8),rs.getDate(9),rs.getString(10),rs.getString(11)));
            }
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            Factory.close(rs,pst,con);
        }
    }

    @Override
    public boolean dynamicUpdatePerson(Person per) {
        try{
           String sql = "update person set ";
           if(per.getName() != null){
               sql += "name = '"+ per.getName() +"',";
           }
           if(per.getPass() != null){
               sql += "pass = '"+ per.getPass() +"',";
           }
           if(per.getGender() != null){
               sql += "gender = '"+ per.getGender() +"',";
           }
           if(per.getAge() != null){
               sql += "age = "+ per.getAge() +",";
           }
           if(per.getHobby() != null){
               sql += "hobby = '"+ per.getHobby() +"',";
           }
           if(per.getSalary() != null){
               sql += "salary = "+ per.getSalary()+",";
           }
           if(per.getEmail() != null){
               sql += "email = '"+ per.getEmail() +"',";
           }
           if(per.getBirth() != null){
               sql += "birth = '"+ new SimpleDateFormat("yyyy-mm-dd").format(per.getBirth()) +"',";
           }
           if(per.getLevel() != null){
               sql += "level = '"+ per.getLevel() +"',";
           }
           if(per.getStatus() != null){
               sql += "status = '"+ per.getStatus() +"',";
           }

           sql = sql.substring(0,sql.length()-1);
           sql += " where id = "+per.getId();
           System.out.println(sql);

           con = Factory.getCon();
           st = con.createStatement();
           return st.executeUpdate(sql) == 1;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            Factory.close(rs,pst,con);
        }
    }

    @Override
    public boolean staticUpdatePerson(Person per) {
        try{
            String sql = "update person set name = ?,pass = ?,gender=?," +
                    "age=?,hobby=?,salary=?,email=?,birth=?,level=?,status=? where id=?";
            con = Factory.getCon();
            pst = con.prepareStatement(sql);
            pst.setString(1,per.getName());
            pst.setString(2,per.getPass());
            pst.setString(3,per.getGender());
            pst.setInt(4,per.getAge());
            pst.setString(5,per.getHobby());
            pst.setDouble(6,per.getSalary());
            pst.setString(7,per.getEmail());
            pst.setDate(8,new java.sql.Date(per.getBirth().getTime()));
            pst.setString(9,per.getLevel());
            pst.setString(10,per.getStatus());
            pst.setInt(11,per.getId());

            return pst.executeUpdate()==1;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            Factory.close(null,pst,con);
        }
    }
}
