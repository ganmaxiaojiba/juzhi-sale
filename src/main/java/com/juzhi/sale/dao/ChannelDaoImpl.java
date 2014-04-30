package com.juzhi.sale.dao;

import com.juzhi.sale.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.*;
import java.sql.*;
import java.util.*;

/**
 * Created by xjwan on 4/30/14.
 */
public class ChannelDaoImpl implements ChannelDao {

    @Autowired
    private DataSource dataSource;

    public ChannelDaoImpl() {

    }
    @Override
    public void saveChannel(Channel channel) {
        String sql = "insert into db_channel(cname) values (?)";
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,channel.getCname());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if ( null!=connection) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Channel> findChannelByDistrict(String dname) {
        String sql = "select * from db_channel where ";
        return null;
    }
}
