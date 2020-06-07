package com.etoak.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Factory {
    /*
    *   链接数据库首先应该加载驱动，
    *
    * */
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getCon(){
        try{
            return DriverManager.getConnection("jdbc:mysql:///et1910","root","etoak");
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void close(ResultSet rs,Statement st,Connection con){
        try{
            if(rs !=null)
                rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
               if(st!=null)
                    st.close();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                try{
                    if(con!=null)
                        con.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
