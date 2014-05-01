package com.juzhi.sale.dao;

import com.juzhi.sale.entity.District;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by xjwan on 4/30/14.
 */
public class DistrictDaoImpl implements DistrictDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public void saveDistrict(District district) {
        String sql = "insert into db_district(dname,descrition) value (?,?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, district.getDname());
            prep.setString(2, district.getDescription());
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
}
