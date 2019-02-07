package com.xxx.pattern.responsiblechain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiaowenzi on 2019/1/15.
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Request request = new Request();
        Response response = new Response();
        request.setReqStr("被就业了：），敏感信息，<script>");
        response.setRepStr("response");

        new FilterChain().addFilter(new HTMLFilter())
                         .addFilter(new SensitiveFilter())
                         .doFilter(request, response);
        logger.info(request.getReqStr());
        logger.info(response.getRepStr());
    }
}
