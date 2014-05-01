package com.juzhi.sale.dao;

import com.juzhi.sale.entity.DistrictChannelRel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjwan on 4/30/14.
 */
public class DistrictChannelRelDaoImpl implements DistrictChannelRelDao {
    @Autowired
    private DataSource dataSource;

    @Override
    public void insertRel(DistrictChannelRel districtChannelRel) {
        String sql = "insert into db_d_c_relation(d_id,c_id) value (?,?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, districtChannelRel.getDid());
            prep.setInt(2, districtChannelRel.getCid());
            prep.executeUpdate();
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
    }

    @Override
    public List<DistrictChannelRel> findChannelIdByDistrictId(int did) {
        String sql = "select * from db_d_c_relation where d_id=" + did;
        Connection conn = null;
        List list = new ArrayList<DistrictChannelRel>();
        try {
            conn = dataSource.getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet resultSet = prep.executeQuery(sql);

            while (resultSet.next()) {
                DistrictChannelRel districtChannelRel = new DistrictChannelRel();
                districtChannelRel.setDid(resultSet.getInt(1));
                districtChannelRel.setCid(resultSet.getInt(2));
                list.add(districtChannelRel);
            }

            resultSet.close();
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
}
