package com.raven.pretender.controller;


import com.raven.pretender.config.ZipkinKafkaConfig;

import com.raven.pretender.util.GzipUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;


/**
 * @author: raven
 * @date: 2022/5/23
 * @description:
 */

@RestController
@Slf4j
public class ZipkinHttpController {

    private static final String CONTENT_ENCODING = "Content-Encoding";
    private static final String DEFAULT_CHARSET_NAME = "UTF-8";
    private static final String COMPRESS_GZIP = "gzip";
    private static final long DEFAULT_TIME_OUT = 10L;

    @Autowired
    private ZipkinKafkaConfig zipkinKafkaConfig;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping("/api/v2/spans")
    public String uploadSpansJson(@RequestBody byte[] param, HttpServletRequest req) {
        try {
            String msg = "";

            //获取数据压缩方式
            String encode = req.getHeader(CONTENT_ENCODING);

            //gzip压缩
            if (COMPRESS_GZIP.equals(encode)) {
                msg = GzipUtils.uncompressToString(param);
            }
            //无压缩
            else if (!StringUtils.hasLength(encode)) {
                msg = new String(param, DEFAULT_CHARSET_NAME);
            }

            //发送kafka
            if (StringUtils.hasLength(msg)) {
                kafkaTemplate.send(zipkinKafkaConfig.getZipkinTopic(), msg).get(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
            }

        } catch (Exception e) {
            log.error("ZipkinHttpController.uploadSpansJson error, cause by: {}", e);
        }

        return "success";
    }

}




