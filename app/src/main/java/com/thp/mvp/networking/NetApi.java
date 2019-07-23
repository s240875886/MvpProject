package com.thp.mvp.networking;

import com.thp.mvp.model.NewsInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by long on 2016/8/22.
 * API 接口
 */
public interface NetApi {

    /**
     * 获取新闻列表
     * eg: http://c.m.163.com/nc/article/headline/T1348647909107/60-20.html
     *      http://c.m.163.com/nc/article/list/T1348647909107/60-20.html
     *
     * @param type 新闻类型
     * @param id 新闻ID
     * @param startPage 起始页码
     * @return
     */
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<NewsInfo>>> getNewsList(@Path("type") String type, @Path("id") String id, @Path("startPage") int startPage);



}
