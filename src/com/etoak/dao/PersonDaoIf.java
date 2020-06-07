package com.etoak.dao;

import com.etoak.po.Person;

import java.util.List;


public interface PersonDaoIf {
    public boolean addPerson(Person per);

    public boolean delPersonById(Integer id);

    public boolean queryName(String name);

    public Person queryNameAndPass(String name,String pass);

    public Integer queryCount();

    public List<Person> queryPage(Integer index, Integer max);

    public List<Person> queryAll();

    public boolean dynamicUpdatePerson(Person per);

    public boolean staticUpdatePerson(Person per);


}
