package com.juzhi.sale.dao;

import com.juzhi.sale.entity.Hello;

import java.util.List;

/**
 * Created by xjwan on 4/29/14.
 */
public interface HelloDao {
    public void insert(Hello hello);
//    public Hello findByName(int id);

    public List<Hello> findByName(String name);

    /*

    WikiUser
        id
        username
        passwd

    public List<Wikiuser> findByUsername(String username);
     */
}
