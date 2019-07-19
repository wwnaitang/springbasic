package cn.mrchen.basic.controller;


import cn.mrchen.basic.entity.PsnInfoVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/psninfo")
public class PsnInfoController {

    @PostMapping("/query/all")
    public PsnInfoVO[] queryAll() {
        PsnInfoVO psnInfoVO = new PsnInfoVO();
        psnInfoVO.setPkPsnInfo("11111111");
        psnInfoVO.setName("aaaa");
        psnInfoVO.setAge(12);
        psnInfoVO.setBirthday(new Date(System.currentTimeMillis()));
        return new PsnInfoVO[]{psnInfoVO};
    }

    @PostMapping("/query/pk")
    public PsnInfoVO queryByPk(@RequestBody PsnInfoVO psnInfoVO) {
        psnInfoVO.setName("13457");
        psnInfoVO.setAge(21);
        return psnInfoVO;
    }


}
