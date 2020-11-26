package com.example.vegetables.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.vegetables.mapper.OrderItemMapper;
import com.example.vegetables.mapper.OrderMapper;
import com.example.vegetables.mapper.SuggestionsInfoMapper;
import com.example.vegetables.model.MallOrder;
import com.example.vegetables.model.OrderItem;
import com.example.vegetables.model.SuggestionsInfo;
import com.example.vegetables.model.SuggestionsInfoTotal;
import com.example.vegetables.service.OrderService;
import com.example.vegetables.util.DateUtil1;
import com.example.vegetables.util.ExcelUtil;
import com.example.vegetables.util.OrderNoUtil;


import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, MallOrder> implements OrderService {
    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    SuggestionsInfoMapper suggestionsInfoMapper;

    @Override
    public MallOrder selectById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<MallOrder> selectByUserId(String id) {
        Wrapper<MallOrder> orderWrapper = new EntityWrapper<>();
        orderWrapper.eq("user_id", id);
        return this.selectList(orderWrapper);
    }

    @Override
    public String save(List<OrderItem> orderItems) {
        String orderNo = OrderNoUtil.getCode(new Date());
        BigDecimal totalPrice = BigDecimal.ZERO;
        String pickUpId = null;
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderNo(orderNo);
            totalPrice.add(orderItem.getTotalPrice());
            pickUpId = orderItem.getPickUpId();
            orderItemMapper.insert(orderItem);
        }
        MallOrder order = new MallOrder();
        order.setStatus(1);
        order.setOrderNo(orderNo);
        order.setUserId("");
        order.setTotalPrice(totalPrice);
        order.setPickUpId(pickUpId);
        baseMapper.insert(order);
        return orderNo;
    }

    @Override
    public void updateOrderStatus(Integer status, String orderNo) {
        MallOrder order = baseMapper.selectByOrderNo(orderNo);
        if (order != null) {
            if (2 == status) {
                order.setStatus(status);
                order.setPayTime(new Date());
            } else if (3 == status) {
                order.setStatus(status);
                order.setPickUpTime(new Date());
            }
            baseMapper.updateById(order);
        }
    }

    @Override
    public void getComplaintDownLoadInfo(HttpServletResponse response) throws IOException, ParseException {
        List<String> suggestionsInfos = suggestionsInfoMapper.listName();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<SuggestionsInfoTotal> list=new ArrayList<>();
        for (String name : suggestionsInfos) {
            SuggestionsInfoTotal suggestionsInfoTotal=new SuggestionsInfoTotal();
            Date d1 = sdf.parse("2020-08-01");//定义起始日期
            Date d2 = sdf.parse("2020-09-01");//定义结束日期
            Calendar dd = Calendar.getInstance();//定义日期实例
            dd.setTime(d1);//设置日期起始时间
            List<String> timeList = new ArrayList<>();
            while (dd.getTime().before(d2)) {//判断是否到结束日期
                String str = sdf.format(dd.getTime());
                SuggestionsInfo byName = suggestionsInfoMapper.getByName(name, str);
                if (byName==null){
                    timeList.add("-");
                }else {
                    suggestionsInfoTotal.setEmployeeName(byName.getEmployeeName());
                    suggestionsInfoTotal.setChecktype(byName.getChecktype());
                    suggestionsInfoTotal.setPositionName(byName.getPositionName());
                    suggestionsInfoTotal.setOrgUnit(byName.getOrgUnit());
                    timeList.add(sdf1.format(byName.getInputDatetime()));
                }
                dd.add(Calendar.DAY_OF_YEAR, 1);//日期加1
            }
            suggestionsInfoTotal.setTimeList(timeList);
            list.add(suggestionsInfoTotal);
        }

        String[] header = {"员工姓名", "组织单位", "职位名称", "打卡类型",
                "1","2", "3", "4", "5", "6", "7","8","9","10",
                "11","12", "13", "14", "15", "16", "17","18","19","20",
                "21","22", "23", "24", "25", "26", "27","28","29","30","31"};

        SXSSFWorkbook hb = new SXSSFWorkbook();
        List<String> headerList = Arrays.asList(header);
        ExcelUtil.createExcel("8月考勤", headerList, hb);
        int inetRowIndex = 2;

      for (SuggestionsInfoTotal suggestionsInfoTotal:list){
          List<String> timeList = suggestionsInfoTotal.getTimeList();
          List<String> objects = new ArrayList<>();
          objects.add(suggestionsInfoTotal.getEmployeeName());
          objects.add(suggestionsInfoTotal.getOrgUnit());
          objects.add(suggestionsInfoTotal.getPositionName());
          objects.add(suggestionsInfoTotal.getChecktype());
          int size = timeList.size();
          for (int i=0;i<size;i++){
              objects.add(timeList.get(i));
          }
            hb = ExcelUtil.createExcelData(hb, objects, 0, inetRowIndex++, headerList.size());
        }

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String zipFileName = "8月考勤.xls";
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(zipFileName, "UTF-8"));
        OutputStream outputStream = response.getOutputStream();
        hb.write(outputStream);
        outputStream.close();
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse("2020-08-01");//定义起始日期
        Date d2 = sdf.parse("2020-09-01");//定义结束日期
        Calendar dd = Calendar.getInstance();//定义日期实例
        dd.setTime(d1);//设置日期起始时间
        List<String> timeList = new ArrayList<>();
        while (dd.getTime().before(d2)) {//判断是否到结束日期
            System.out.println(   sdf.format(dd.getTime()));
            dd.add(Calendar.DAY_OF_YEAR, 1);//日期加1
        }
    }

}
