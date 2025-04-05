package com.xingxing.springboot_demo.cotroller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.transform.Result;
import java.util.logging.Logger;

@Slf4j
@RestController
public class DeptController {
    //private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @RequestMapping("/dept")
    public Result.success();


}
