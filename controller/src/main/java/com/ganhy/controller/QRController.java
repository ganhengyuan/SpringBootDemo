package com.ganhy.controller;

import com.ganhy.entity.result.Result;
import com.ganhy.util.QRCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Api(value = "二维码生成工具", tags = "二维码生成操作相关的接口")
@PreAuthorize("hasAuthority('qr')")
@RestController
@RequestMapping("qr")
public class QRController {

    @ApiOperation(value = "生成二维码", notes="生成二维码", httpMethod = "GET", response = Result.class)
    @GetMapping("/qrCode")
    public void getQrCode(@ApiParam(name = "codeContent", value = "二维码信息", required = true) String codeContent,
                          HttpServletResponse response) {
        try {
            //调用工具类生成二维码并输出到输出流中
            QRCodeUtil.createCodeToOutputStream(codeContent, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
