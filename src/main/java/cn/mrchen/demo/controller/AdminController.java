package cn.mrchen.demo.controller;


import cn.mrchen.demo.entity.Admin;
import cn.mrchen.demo.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminMapper adminMapper;

    @PostMapping("/query")
    public Admin query(@RequestBody Admin admin) {
        return adminMapper.selectByPrimaryKey(admin.getPkAdmin());
    }
}
