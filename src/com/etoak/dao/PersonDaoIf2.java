package com.etoak.dao;

import com.etoak.po.Person;

import java.util.Date;
import java.util.List;

public interface PersonDaoIf2 {
    //1:添加一个Person
    public boolean addPerson(Person per);
    //2：根据id删除一个Person
    public boolean delPersonById(Integer id);
    //3:根据name删除一个person
    public boolean delPersonByName(String name);
    //4：查看所有person
    public List<Person> queryAll();
    //5：根据name查询
    public boolean queryName(String name);
    //根据name和pass查询
    public Person queryNameAndPass(String name,String pass);
    //7：姓名模糊查询
    public List<Person> queryNameLike(String args);
    //8：拿取总记录数
    public Integer queryCount();
    //9：分页查询
    public List<Person> queryPage(Integer index,Integer max);
    //10：批量真删除
    public boolean multiDelById(String[] args);
    //11:条件模糊分页查询
    public List<Person> queryPageCheck(Person per,Integer index,Integer max);
    //12:修改
    public boolean updatePerson(Person per);
    //13:根据id假删除
    public boolean fakeDelById(Integer id);
    //14:提升权限
    public boolean upLevelById(Integer id);
    //15：根据日期区间拿取数据
    //拿取1990-01-01~填写日期间的用户并且分页
    public List<Person> queryDate(Date date,Integer index,Integer max);


}
