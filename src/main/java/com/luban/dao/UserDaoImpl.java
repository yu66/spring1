package com.luban.dao;

import com.luban.Anno.Test;
import org.springframework.stereotype.Repository;

@Repository("indexDao")
public class UserDaoImpl implements UserDao {

    public void query() {
        System.out.println("query");
    }

    @Test
    public void query2(){
        System.out.println("query2");
    }
}
