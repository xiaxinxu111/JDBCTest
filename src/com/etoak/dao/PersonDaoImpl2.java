package com.etoak.dao;

import com.etoak.factory.Factory;
import com.etoak.po.Person;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class PersonDaoImpl2 implements PersonDaoIf2{

    Connection con;
    //引入选择执行器
    QueryRunner qr = new QueryRunner();


    @Override
    public boolean addPerson(Person per) {
        try{
            String sql = "insert into person values (null,?,?,?,?,?,?,?,?,?,'1')";
            con = Factory.getCon();
            //Query.update(Connection,sql语句,占位符)  返回值是更改的数据个数;
            return qr.update(con,sql,per.getName(),per.getPass(),per.getGender(),per.getAge()
            ,per.getHobby(),per.getSalary(),per.getEmail(),per.getBirth(),per.getLevel()) == 1;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        /*
        * DBUtils自动释放资源  不需要我们手动释放资源
        *
        * */
    }

    @Override
    public boolean delPersonById(Integer id) {
        try{
            String sql = "delete from person where id = ?";
            con = Factory.getCon();
            return qr.update(con,sql,id) == 1;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delPersonByName(String name) {
        try{
            String sql = "delete from person where name = ? and status = '1'";
            con = Factory.getCon();
            return qr.update(con,sql,name) >= 1;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Person> queryAll() {
        try{
            String sql = "select * from person where status ='1'";
            con = Factory.getCon();
            /*
            * qr.query(con,sql,new ResultSetHandler(),Object... parem)
            *
            *   如果返回值为封装实体类的集合
            *   new BeanListHandler<>()
            *
            *   如果返回值为实体
            *   new BeanHandler()
            *
            *   封装类似count、avg、max、min、sum......函数的执行结果
            *   new ScalarHandler()
            * */
            return qr.query(con,sql,new BeanListHandler<Person>(Person.class));

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean queryName(String name) {
        try{
            String sql = "select * from person where name = ? and status = '1'";
            con = Factory.getCon();
            /*
            *
            * */
            return qr.query(con,sql,new BeanHandler<Person>(Person.class),name) != null;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Person queryNameAndPass(String name, String pass) {
        try{
            String sql = "select * from person where name = ? and pass = ? and status = '1'";
            con = Factory.getCon();
            return qr.query(con,sql,new BeanHandler<Person>(Person.class),name,pass);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Person> queryNameLike(String args) {
        try{
            String sql = "select * from person where name like ? and status = '1'";
            con = Factory.getCon();
            return qr.query(con,sql,new BeanListHandler<Person>(Person.class),'%'+args+'%');
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer queryCount() {
        try{
            String sql = "select count(*) from person";
            con = Factory.getCon();
            return Integer.parseInt(qr.query(con,sql,new ScalarHandler()).toString());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Person> queryPage(Integer index, Integer max) {
        try{
            String sql = "select * from person where status='1' limit ?,?";
            con = Factory.getCon();
            return qr.query(con,sql,new BeanListHandler<Person>(Person.class),index,max);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean multiDelById(String[] args) {
        try{
            String sql = "delete from person where id in (";
            for(String s : args){
                sql += s+",";
            }
            sql = sql.substring(0,sql.length()-1);
            sql += ")";
            System.out.println(sql);
            con = Factory.getCon();
            return qr.update(con,sql) > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /*
    * 条件查询： name  gender age hobby email
    * */
    @Override
    public List<Person> queryPageCheck(Person per, Integer index, Integer max) {
        try{
            /* 拼接字符串  */
            String sql = "select * from person where 1=1 and ";
            if(per.getName() != null){
                sql +="name like '%" + per.getName() + "%' and ";
            }
            if(per.getGender() != null){
                sql += "gender = " + per.getGender() + " and ";
            }
            if(per.getAge() != null){
                sql += "age = " + per.getAge() + " and ";
            }
            if(per.getHobby() != null){
                sql += "hobby like '%" + per.getHobby() + "%' and ";
            }
            if(per.getEmail() != null){
                sql += "email like '%" + per.getEmail() + "%' and ";
            }
            sql = sql.substring(0,sql.length() - 4);
            sql += " limit ?,?";
            System.out.println(sql);
            con = Factory.getCon();
            return qr.query(con,sql,new BeanListHandler<Person>(Person.class),index,max);
            /* 不拼接字符串 
            * List<Object> list = new ArrayList<>;
            * String sql = "select * from person where 1=1 ";
            * if(per.getName() != null){
            *   sql += " and name like ?";
            *   list.add("%" + per.getName() + "%");
            * }
            * if(per.getGender() != null){
            *   sql += " and gender = ?";
            *   list.add(per.getGender());
            * }
            * if(per.getAge() != null){
            *   sql += " and age = ?";
            *   list.add(per.getAge());
            * }
            * if(per.getHobby() != null){
            *   sql += " and hobby like ?";
            *   list.add("%" + per.getHobby() + "%");
            * }
            * if(per.getEmail() != null){
            *   sql += " and email like ?"
            *   list.add("%" + per.getEmail() + "%");
            * }
            * sql += "limit ?,?";
            * list.add(index);
            * list.add(max);
            * con = Factory.getCon();
            * return qr.query(con,sql,new BeanListHandler<Person>(Person.class),list.toArray());
            * */
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updatePerson(Person per) {
        try{
            String sql = "update person set name = ?,pass = ?,gender = ?" +
                    ",age = ?,hobby = ?,salary = ?,email = ?,birth = ?,level = ?,status = ?" +
                    " where id = ?";
            con = Factory.getCon();
            return qr.update(con,sql,per.getName(),per.getPass(),per.getGender()
                    ,per.getAge(),per.getHobby(),per.getSalary(),per.getEmail()
                    ,per.getBirth(),per.getLevel(),per.getStatus(),per.getId()) ==1;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean fakeDelById(Integer id) {
        try{
            String sql = "update person set status ='0' where id = ?";
            con = Factory.getCon();
            return qr.update(con,sql,id) == 1;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean upLevelById(Integer id) {
        try{
            String sql = "update person set level = 1 where id = ?";
            con = Factory.getCon();
            return qr.update(con,sql,id) == 1;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //拿取1990-01-01~填写日期之间的用户并且分页
    @Override
    public List<Person> queryDate(Date date, Integer index, Integer max) {
        try{
            String sql = "select * from person where birth between '1990-01-01' and ? limit ?,?";
            con = Factory.getCon();

            return qr.query(con,sql,new BeanListHandler<Person>(Person.class),new java.sql.Date(date.getTime()),index,max);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
