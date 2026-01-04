package com.service;

import com.model.Stat;
import java.util.List;

public interface StatService {
    // 统计当日借阅量
    boolean statBorrowCount(String date);
    // 统计当日用户数
    boolean statUserCount(String date);
    // 查询月度统计
    List<Stat> listMonthStat(String month);
    // 查询热门图书TOP10
    List<Object[]> listHotBooks();
}
