package com.ganhy.controller;

import com.ganhy.pojo.result.Result;
import com.ganhy.pojo.validation.ValBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Gan
 * @ClassName: ValidationController
 * @Date: 2020-1-8 15:23
 * @Description: 进行参数验证框架的测试Controller
 **/
@RestController
@RequestMapping(value = "/val")
public class ValidationController {


    @ApiOperation(value = "主要进行数据验证", notes = "主要进行数据验证", httpMethod = "POST", response = Result.class)
    @PostMapping(value = "/val")
    public Result val(@Valid @RequestBody ValBean bean,
                      BindingResult result) {
        if (result.hasErrors()) {
            //如果没有通过,跳转提示
            Map<String, String> map = getParamErrors(result);
            return new Result<Map<String, String>>(1, "error", "参数格式不正确", map);
        } else {
            // TODO 继续处理业务逻辑
        }
        return new Result<Map<String, String>>(1, "success", "业务处理成功", null);
    }

    /**
     * 获取错误的参数信息
     *
     * @param result
     * @return
     */
    private Map<String, String> getParamErrors(BindingResult result) {
        List<FieldError> list = result.getFieldErrors();
        Map<String, String> errorMap = new HashMap<>(list.size());
        for (FieldError error : list) {
            System.out.println("error.getField():" + error.getField());
            System.out.println("error.getDefaultMessage():" + error.getDefaultMessage());
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        return errorMap;
    }

}