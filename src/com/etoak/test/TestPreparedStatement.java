package com.etoak.test;

import com.etoak.dao.PersonDaoIf;
import com.etoak.dao.PersonDaoImpl;
import com.etoak.po.Person;

import java.text.SimpleDateFormat;

public class TestPreparedStatement {
    public static void main(String[] args) {
        try{
            PersonDaoIf dao = new PersonDaoImpl();

            Person per=new Person(null,"张三","444444","1",23,"足球，乒乓球",6666.66,"123456@etoak.com",new SimpleDateFormat("yyyy-mm-dd").parse("1997-07-20"),"1",null);
            Person newper=new Person(7,"张三丰","88886666","1",77,"舞剑，修炼",22222.22,"123456@etoak.com",new SimpleDateFormat("yyyy-mm-dd").parse("2222-02-22"),"1","1");

            //System.out.println(dao.addPerson(per));

            //System.out.println(dao.delPersonById(6));

            /*for(Person pers : dao.queryAll()){
                System.out.println(pers);
            }*/
            //System.out.println(dao.queryName("张三"));
            //System.out.println(dao.queryNameAndPass("张三","444444"));
            //System.out.println(dao.queryCount());
            /*for(Person pers : dao.queryPage(0,4)){
                System.out.println(pers);
            }*/
            //System.out.println(dao.dynamicUpdatePerson(newper));
            System.out.println(dao.staticUpdatePerson(newper));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
