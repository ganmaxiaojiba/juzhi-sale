package com.juzhi.sale.dao;

import com.juzhi.sale.entity.DistrictChannelRel;

import java.util.List;

/**
 * Created by xjwan on 4/30/14.
 */

public interface DistrictChannelRelDao {
    public void insertRel(DistrictChannelRel districtChannelRel);

    public List<DistrictChannelRel> findChannelIdByDistrictId(int did);
}
