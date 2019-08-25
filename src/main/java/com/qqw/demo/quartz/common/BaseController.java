package com.qqw.demo.quartz.common;

import com.github.pagehelper.Page;
import com.qqw.demo.quartz.utils.JsonPluginsUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BaseController {

    public Logger logger = LoggerFactory.getLogger(getClass());

    //成功的数据返回结果集的格式
    public String getListSuccessButNoResult() {
        return JSONObject.fromObject("{code:'0',msg:'成功',total:0,resList:[]}").toString();
    }

    //权限不够的返回数据结果集格式
    //成功的数据返回结果集格式
    public String success() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "0");
        jsonObject.put("msg", "成功");
        return jsonObject.toString();
    }

    //成功的数据返回结果集格式
    public String success(String beansStr) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "0");
        jsonObject.put("msg", "成功");
        jsonObject.put("data", beansStr);
        return jsonObject.toString();
    }

    //失败的数据返回结果集格式
    public String failure(String code, String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        return jsonObject.toString();
    }
    public String failure(Integer code, String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        return jsonObject.toString();
    }


    /**
     * 查询分页结果
     *
     * @param list
     * @param pageCur
     * @param pageSize
     * @param total
     * @return
     */
    @Deprecated
    public String pageTotal(List<?> list, int pageCur, int pageSize, int total, int maxPage) {

        String jsonArrStr = JsonPluginsUtil.beanListToJson(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", jsonArrStr);
        jsonObject.put("total", total);
        jsonObject.put("page", pageCur);
        jsonObject.put("maxPage", maxPage);
        jsonObject.put("size", pageSize);

        return jsonObject.toString();
    }

    /**
     * <p>Title: pageTotal<p>
     * <p>Description: <p>
     *
     * @date 2017-8-23 16:53
     */
    public String pageTotal(Page<?> page) {
        String jsonArrStr = JsonPluginsUtil.beanListToJson(page);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", jsonArrStr);
        jsonObject.put("total", page.getTotal());
        jsonObject.put("page", page.getPageNum());
        jsonObject.put("maxPage", page.getPages());
        jsonObject.put("size", page.getPageSize());

        return jsonObject.toString();
    }
}


