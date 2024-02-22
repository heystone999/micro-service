package com.stone.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.hotel.pojo.Hotel;
import com.stone.hotel.pojo.PageResult;
import com.stone.hotel.pojo.RequestParams;

import java.util.List;
import java.util.Map;

public interface IHotelService extends IService<Hotel> {
    PageResult search(RequestParams params);

    Map<String, List<String>> filters(RequestParams params);

    List<String> getSuggestions(String prefix);
}
