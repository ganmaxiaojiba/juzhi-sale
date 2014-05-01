package com.juzhi.sale.dao;

import com.juzhi.sale.entity.District;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<District> findDistrict() {
        String sql = "select * from db_district";
        Connection connection = null;

        List<District> districts = new ArrayList<District>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery(sql);


            while (rs.next()) {
                District district = new District();
                district.setdid(rs.getInt(1));
                district.setDname(rs.getString(2));
                district.setDescription(rs.getString(3));
                districts.add(district);
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
        return districts;
    }

    @Override
    public int findIdByDistrictName(String districtName) {
        String sql = "select d_id from db_district where dname = '"+districtName+"'";
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
}
