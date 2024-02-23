package com.stone.hotel.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stone.hotel.mapper.HotelMapper;
import com.stone.hotel.pojo.Hotel;
import com.stone.hotel.service.IHotelService;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {
}
