package cn.cyyaw.sys;


import cn.cyyaw.jpa.BaseConstants;
import cn.cyyaw.sys.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;


@RequestMapping("/sys")
@RestController
public class SysController {


    @Autowired
    protected DbService dbService;

    /**
     * 读取数据库结构
     */
    @PostMapping("/readstructure")
    public Map readstructure(@RequestBody Map<String, Object> map) throws SQLException, ClassNotFoundException {
        String url = (String) map.get("url");
        String user = (String) map.get("user");
        String password = (String) map.get("password");
        url = url + "?user=" + user + "&password=" + password + "&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        dbService.readstructure(url, user, password);
        return BaseConstants.statusMessage(true, "读取成功！");
    }


}
