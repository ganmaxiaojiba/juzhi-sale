package com.juzhi.sale.dao;

import com.juzhi.sale.entity.Channel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "insert into db_channel(cname) " +
                "select '"+channel.getCname()+"' from db_channel where not exists(select * from db_channel where cname= '"+channel.getCname()+"')";
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setString(1, channel.getCname());
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
    public void saveChannelListByDName( String dname) {

        int did = districtDao.findIdByDistrictName(dname);
        String sql = "insert into db_d_c_relation(d_id, c_id) value (?,?)";

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
    public List<Channel> findChannelsByDistrictId(int did) {
        String sql = "SELECT * " +
                "FROM db_channel c " +
                "WHERE c.c_id " +
                "IN ( " +
                "SELECT r.c_id " +
                "FROM db_d_c_relation r  " +
                "WHERE r.d_id=" + did + ")";

        Connection conn = null;
        List channelList = new ArrayList<Channel>();
        try {
            conn = dataSource.getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
// QUESTION: why don't use '?'
//            prep.setInt(1, cid);

            ResultSet rs = prep.executeQuery(sql);

            while (rs.next()) {
                Channel channel = new Channel();

                channel.setCid(rs.getInt(1));
                channel.setCname(rs.getString(2));

                channelList.add(channel);
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
        return channelList;
    }

    @Override
    public void deleteChannelByChannelId(int cid) {

        String sql1 = "delete from db_d_c_relation where c_id=" + cid;
        String sql2 = "delete from db_c_t_relation where c_id=" + cid;
        String sql3 ="delete from db_channel where c_id = "+cid;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement prep1 = conn.prepareStatement(sql1);
            prep1.execute(sql1);

            PreparedStatement prep2 = conn.prepareStatement(sql2);
            prep2.execute(sql2);

            PreparedStatement prep3 = conn.prepareStatement(sql3);
            prep3.execute(sql3);

            prep3.close();
            prep2.close();
            prep1.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != conn) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
