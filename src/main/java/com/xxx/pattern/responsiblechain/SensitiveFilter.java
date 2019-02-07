package com.xxx.pattern.responsiblechain;

/**
 * Created by xiaowenzi on 2019/1/15.
 */
public class SensitiveFilter implements Filter {
    @Override
    public void doFilter(Request req, Response rep, FilterChain chain) {
        req.setReqStr(req.getReqStr().replace("敏感", "").replace("被就业", "就业"));
        req.setReqStr(req.getReqStr() + "===SensitiveFilter");
        chain.doFilter(req, rep);
        rep.setRepStr(rep.getRepStr() + "===SensitiveFilter");
    }
}
