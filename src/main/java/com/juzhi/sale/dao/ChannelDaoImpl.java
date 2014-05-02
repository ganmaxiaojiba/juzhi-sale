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

    @Autowired
    private DistrictDao districtDao;

    public ChannelDaoImpl() {

    }

    @Override
    public void saveChannel(Channel channel, String dname) {
        String sql = "insert into db_channel(cname) values (?)";
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, channel.getCname());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != connection) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        int cid = findIdByChannelName(channel.getCname());
        int did = districtDao.findIdByDistrictName(dname);

        sql = "insert into db_d_c_relation(d_id, c_id) value (?,?)";

        try {
            connection = dataSource.getConnection();
            PreparedStatement prep = connection.prepareStatement(sql);

            prep.setInt(1, did);
            prep.setInt(2, cid);

            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != connection) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int findIdByChannelName(String channelName) {
        String sql = "select c_id from db_channel where cname = '" + channelName + "'";
        Connection connection = null;

        List<Integer> ids = new ArrayList<Integer>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery(sql);


            while (rs.next()) {

                ids.add(rs.getInt(1));
            }
            rs.close();
            prep.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != connection) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ids.get(0);
    }

    @Override
    public List<Channel> findChannel() {
        String sql = "select * from db_channel";
        Connection connection = null;

        List<Channel> channels = new ArrayList<Channel>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery(sql);


            while (rs.next()) {
                Channel channel = new Channel();
                channel.setCid(rs.getInt(1));
                channel.setCname(rs.getString(2));
                channels.add(channel);
            }
            rs.close();
            prep.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != connection) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return channels;
    }

    @Override
    public List<Channel> findChannelByDistrict(String dname) {
        String sql = "select * from db_channel a left join db_d_c_relation b on a.c_id=b.c_id " +
                "left join db_district c on b.d_id=c.d_id where c.dname='" + dname + "'";

        Connection conn = null;
        List list = new ArrayList<Channel>();
        try {
            conn = dataSource.getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery(sql);

            while (rs.next()) {
                Channel channel = new Channel();
                channel.setCid(rs.getInt(1));
                channel.setCname(rs.getString(2));
                list.add(channel);
            }
            rs.close();
            prep.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != conn) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<Tag> findTagsByChannelId(int cid) {


        String sql = "SELECT * " +
                "FROM db_tag t " +
                "WHERE t.t_id " +
                "IN ( " +
                "SELECT r.t_id " +
                "FROM db_c_t_relation r  " +
                "WHERE r.c_id="+cid+")";

        Connection conn = null;
        List tagList = new ArrayList<Tag>();
        try {
            conn = dataSource.getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
// QUESTION: why don't use '?'
//            prep.setInt(1, cid);

            ResultSet rs = prep.executeQuery(sql);

            while (rs.next()) {
                Tag tag = new Tag();

                tag.settid(rs.getInt(1));
                tag.setTname(rs.getString(2));
                tag.setDescription(rs.getString(3));
                tag.setLink(rs.getString(4));
                tag.setClick_rate(rs.getInt(5));

                tagList.add(tag);
            }

            rs.close();
            prep.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != conn) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tagList;
    }
}
