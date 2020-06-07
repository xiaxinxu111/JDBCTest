package com.etoak.test;

import com.etoak.dao.PersonDaoIf2;
import com.etoak.dao.PersonDaoImpl2;
import com.etoak.po.Person;

import java.text.SimpleDateFormat;

/**
 * Created by 78610 on 2019/12/11.
 */
public class TestDBUtils {
    public static void main(String[] args) {
        try{
            Person per=new Person(null,"王维","444444","1",23,"足球，乒乓球",6666.66,"123456@etoak.com",new SimpleDateFormat("yyyy-mm-dd").parse("1997-07-18"),"0",null);
            PersonDaoIf2 dao = new PersonDaoImpl2();

            //System.out.println(dao.addPerson(per));

            //System.out.println(dao.delPersonById(9));

            //System.out.println(dao.delPersonByName("王维"));

            /*for(Person per1 : dao.queryAll()){
                System.out.println(per1);
            }*/

            //System.out.println(dao.queryName("张三丰"));

            //System.out.println(dao.queryNameAndPass("张三丰","88886666"));

            /*for(Person pers:dao.queryNameLike("a")){
                System.out.println(pers);
            }*/

            //System.out.println(dao.queryCount());

            /*for(Person pers :dao.queryPage(0,6)){
                System.out.println(pers);
            }*/

            /*Person per1=new Person(7,"张三丰","333333","1",23,"足球，乒乓球",26666.66,"123456@etoak.com",new SimpleDateFormat("yyyy-mm-dd").parse("1997-07-18"),"1","1");
            System.out.println(dao.updatePerson(per1));*/

            //System.out.println(dao.fakeDelById(5));

            //System.out.println(dao.upLevelById(2));

            /*String[] arg = {"6","7","8","9"};
            System.out.println(dao.multiDelById(arg));*/

            Person pers=new Person(null,"a","444444","1",null,null,6666.66,".com",new SimpleDateFormat("yyyy-mm-dd").parse("1997-07-18"),"0",null);
            for(Person p : dao.queryPageCheck(pers,0,4)) {
                System.out.println(p);
            }

            /*for(Person p : dao.queryDate(new SimpleDateFormat("yyyy-mm-dd").parse("2000-01-01"),0,4)){
                System.out.println(p);
            }*/

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
