package com.tyt.chezhu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.TruckRouteRestult;

/**
 * 货车路线规划查询:https://lbs.amap.com/api/android-sdk/guide/route-plan/truck
 *
 * */
public class MainActivity extends AppCompatActivity implements RouteSearch.OnTruckRouteSearchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            RouteSearch mRouteSearch = new RouteSearch(this);
            mRouteSearch.setOnTruckRouteSearchListener(this);

            LatLonPoint mStartPoint = new LatLonPoint(1f,1f);
            LatLonPoint mEndPoint = new LatLonPoint(1f,1f);
            final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(mStartPoint, mEndPoint);
            //设置车牌
            fromAndTo.setPlateProvince("京");
            fromAndTo.setPlateNumber("A000XXX");
            RouteSearch.TruckRouteQuery query = new RouteSearch.TruckRouteQuery(fromAndTo, RouteSearch.DRIVING_SINGLE_DEFAULT, null, RouteSearch.TRUCK_SIZE_LIGHT);
            //设置车辆信息
            query.setTruckAxis(6);
            query.setTruckHeight(3.9f);
            query.setTruckWidth(3);
            query.setTruckLoad(45);
            query.setTruckWeight(50);

            mRouteSearch.calculateTruckRouteAsyn(query);
        } catch (AMapException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTruckRouteSearched(TruckRouteRestult truckRouteRestult, int i) {
        //解析result获取算路结果，可参考官方demo
        //建议通过TruckPath中getRestriction() 判断路线上是否存在限行
    }
}