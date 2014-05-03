package com.juzhi.sale.dao;

import com.juzhi.sale.entity.Tag;
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
public class ChannelTagDaoImpl implements ChannelTagDao {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private ChannelDao channelDao;

    @Override
    public void insertTag(Tag tag, String channelName) {
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
    public void saveTag(Tag tag, String cname) {
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

        int tid = findIdByChannelTagName(tag.getTname());
        int cid = channelDao.findIdByChannelName(cname);

        sql = "insert into db_c_t_relation(c_id, t_id) value (?,?)";

        try {
            conn =dataSource.getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);

            prep.setInt(1,cid);
            prep.setInt(2,tid);

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
    public List<Tag> findTagByName(String tname) {
        String sql = "select * from db_tag where tname = '"+tname+"'";
        Connection conn = null;
        List list = new ArrayList<Tag>();

        try {
            conn = dataSource.getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet resultSet = prep.executeQuery(sql);

            while(resultSet.next()){
                Tag tag = new Tag();
                tag.setTname(resultSet.getString("tname"));
                tag.setDescription(resultSet.getString("description"));
                tag.setLink(resultSet.getString("link"));
                tag.setClick_rate(resultSet.getInt("click_rate"));
                list.add(tag);
            }

            resultSet.close();
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
        return list;
    }

    @Override
    public int findIdByChannelTagName(String channelTagName) {
        String sql = "select t_id from db_tag where tname = '"+channelTagName+"'";
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

    @Override
    public void deleteTagbyTId(int tid) {
        String sql1= "delete from db_c_t_relation where t_id="+tid;
        String sql2 = "delete from db_tag where t_id="+tid;
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement prep1 = conn.prepareStatement(sql1);
            prep1.execute(sql1);

            PreparedStatement prep2 =conn.prepareStatement(sql2);
            prep2.execute(sql2);

            prep2.close();
            prep1.close();
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
    public List<Tag> findTag() {
        String sql = "select * from db_tag";
        Connection conn = null;
        List<Tag> tagList = new ArrayList<>();

        try {
            conn = dataSource.getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery(sql);

            while (rs.next()){
                Tag tag = new Tag();
                tag.setTname(rs.getString("tname"));
                tag.setDescription(rs.getString("description"));
                tag.setLink(rs.getString("link"));
                tag.setClick_rate(rs.getInt("click_rate"));
                tagList.add(tag);
            }

            rs.close();
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
        return tagList;
    }

}
