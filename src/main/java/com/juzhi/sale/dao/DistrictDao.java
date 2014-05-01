package com.juzhi.sale.dao;

import com.juzhi.sale.entity.District;

import java.util.List;

/**
 * Created by xjwan on 4/30/14.
 */
public interface DistrictDao {
    public void saveDistrict(District district);

    public List<District> findDistrict();

    public int findIdByDistrictName(String districtName);
}
