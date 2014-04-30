package com.juzhi.sale.dao;

import com.juzhi.sale.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.*;
import java.sql.*;
import java.util.*;

/**
 * Created by xjwan on 4/30/14.
 */
public class ChannelTagDaoImpl implements ChannelTagDao {
    @Autowired
    private DataSource dataSource;

    @Override
    public void insertTag(Tag tag) {
        String sql = "insert into db_tag(tname,description,link,click_rate) value (?,?,?,?)";
        Connection conn = null;

        try {
            conn =dataSource.getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1,tag.getTname());
            prep.setString(2,tag.getDescription());
            prep.setString(3,tag.getLink());
            prep.setInt(4,tag.getClick_rate());
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
    public List<Tag> findTagByChannel(String cname) {
        String sql = "select * from db_tag";
        return null;
    }
}
