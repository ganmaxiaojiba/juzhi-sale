package com.juzhi.sale.dao;

import com.juzhi.sale.entity.Hello;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * Created by xjwan on 4/29/14.
 */
public class HelloDaoImpl implements HelloDao {

    @Autowired
    private DataSource dataSource;

//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Override
    public void insert(Hello hello) {
        String sql = "insert into hello (name) values (?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, hello.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public List<Hello> findByName(String name) {

        //查询数据库的数据
        String sql = "select * from hello where name='" + name + "'";
        Connection conn = null;
        List list = new ArrayList<Hello>();

        try {
            conn = dataSource.getConnection();

            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet resultSet = prep.executeQuery(sql);

            while (resultSet.next()) {
                Hello hello = new Hello();
                hello.setId(resultSet.getInt(1));
                hello.setName(resultSet.getString(2));
                list.add(hello);
            }
            resultSet.close();
            prep.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
