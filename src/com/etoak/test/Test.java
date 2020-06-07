package com.etoak.test;

import com.etoak.factory.Factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) {
        System.out.println("济南的雾霾来了");
        try{
            /*链接数据库*/
            Connection con = Factory.getCon();
            //测试链接是否成功
            /*if(con==null){
                System.out.println("链接失败！");
                return;
            }
            System.out.println("链接成功！");*/
            /*创建一个执行器  用来执行sql语句*/
            Statement st = con.createStatement();
            String dml1 = "insert into person values (null,'smoke','admin','1',40,null,5000.00,'admin@etoak.com','1990-04-04','0','1');";
            String dml2 = "delete from person where id = 2";
            String dql1 = "select * from person where status = '1'";
            /*
            * 1)boolean     execute()
            * 2)int         executeUpdate()   专门用来执行dml语句，返回的int是执行sql语句更改的记录数
            * 3）ResultSet  executeQuery()    专门用来执行dql语句，返回一个结果集
            *                                 解析结果集的步骤：
            *                                   首先判断结果集中是否存在有效数据，如果没有有效数据，则
            *                                   根本不需要进行解析，不能根据ResultSet是否为null来判断是否
            *                                   存在有效数据
            *                                   根据Boolean next() 来判断是否存在有效数据
            *                                       rs.get数据类型(列数)
            *                                       rs.get数据类型(列名)
            * 4)int[]       executeBatch()    专门用来批处理sql语句 返回一个更改记录数的数组
            * */
            /*int count = st.executeUpdate(dml1);
            System.out.println(count);*/
            ResultSet rs = st.executeQuery(dql1);
            while(rs.next()){
                System.out.println("ID:" + rs.getInt(1)+"\t用户姓名:"
                        + rs.getString(2) + "\t用户密码:"+rs.getString(3)
                        + "\t性别：" + rs.getString(4) + "\t年龄:" + rs.getInt(5)
                        + "\t爱好:" + rs.getString(6) + "\t薪资:" + rs.getInt(7)
                        + "\t邮箱:" + rs.getString(8) + "\t生日:" + rs.getDate(9)
                        + "\t权限:" + rs.getString(10) + "\t状态:" + rs.getString(11));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
