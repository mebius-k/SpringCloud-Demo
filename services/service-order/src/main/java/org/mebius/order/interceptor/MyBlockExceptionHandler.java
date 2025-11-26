package org.mebius.order.interceptor;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mebius.bean.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    @Autowired
    ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, String s, BlockException e) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        R error = R.error(500, "Sentinel开始限流 " + e.getClass());
        String json = objectMapper.writeValueAsString(error);
        response.getWriter().write(json);
    }
}
