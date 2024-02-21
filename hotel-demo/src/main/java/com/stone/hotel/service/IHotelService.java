package com.stone.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.hotel.pojo.Hotel;
import com.stone.hotel.pojo.PageResult;
import com.stone.hotel.pojo.RequestParams;

public interface IHotelService extends IService<Hotel> {
    PageResult search(RequestParams params);
}
