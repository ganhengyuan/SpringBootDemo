package com.ganhy.controller;

import com.ganhy.pojo.result.Result;
import com.ganhy.util.QRCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Gan
 * @ClassName: QRController
 * @Date: 2019-12-16 14:52
 * @Description: 二维码测试
 **/
@RestController
@RequestMapping("qr")
public class QRController {


    @GetMapping("/qrCode")
    public void getQrCode(String codeContent, HttpServletResponse response) {
        try {
            /**
             * 调用工具类生成二维码并输出到输出流中
             */
            QRCodeUtil.createCodeToOutputStream(codeContent, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
