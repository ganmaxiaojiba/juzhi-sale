package com.juzhi.sale.dao;

import com.juzhi.sale.entity.ChannelTagRel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjwan on 4/30/14.
 */
public class ChannelTagRelDaoImpl implements ChannelTagRelDao {
    @Autowired
    private DataSource dataSource;

    @Override
    public void insertTagRel(ChannelTagRel channelTagRel) {
        String sql = "insert into db_c_t_relation(c_id,t_id) value(?,?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1,channelTagRel.getCid());
            prep.setInt(2,channelTagRel.getTid());
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (null != conn) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<ChannelTagRel> findTagIdByChannelId(int cid) {
        String sql = "select * from db_c_t_relation where c_id = "+cid;
        Connection conn = null;
        List list = new ArrayList<ChannelTagRel>();
        try {
            conn = dataSource.getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery(sql);

            while (rs.next()){
                ChannelTagRel channelTagRel = new ChannelTagRel();
                channelTagRel.setCid(rs.getInt(1));
                channelTagRel.setTid(rs.getInt(2));
                list.add(channelTagRel);
            }

            rs.close();
            prep.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(null != conn) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
