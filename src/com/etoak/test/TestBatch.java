package com.etoak.test;


import com.etoak.factory.Factory;

import java.sql.Connection;
import java.sql.Statement;

public class TestBatch {
    public static void main(String[] args) {
        try{
            /*
            * 批处理  ：  一次多次执行sql语句   不可以是dql语句
            * */
            String dml1 = "insert into person values (null,'smoke','admin','1',23,null,5000.00,'admin@etoak.com','1990-04-04','0','1');";
            String dml2 = "insert into person values (null,'smoke','nima','1',40,null,5000.00,'admin@etoak.com','1990-04-04','0','1');";
            String dml3 = "insert into person values (null,'smoke','sile','1',30,null,5000.00,'admin@etoak.com','1990-04-04','0','1');";
            String dml4 = "delete from person where age >30";
            String dml5 = "update person set name = 'NMSL' where id = '13'";

            Connection con = Factory.getCon();

            //关闭mysql的自动提交事务
            con.setAutoCommit(false);
            Statement st = con.createStatement();

            st.addBatch(dml1);
            st.addBatch(dml2);
            st.addBatch(dml3);
            st.addBatch(dml4);
            st.addBatch(dml5);
            /*执行批处理   注意这里返回的是一个int类型的数组
            * 数组中封装了每次执行sql语句返回的更改的记录数*/
            int[] count = st.executeBatch();
            //恢复事务
            con.commit();
            //恢复mysql的自动提交事务
            con.setAutoCommit(true);
            for(int i : count){
                System.out.println(i);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
