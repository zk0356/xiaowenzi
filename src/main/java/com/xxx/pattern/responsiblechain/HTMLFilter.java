package com.xxx.pattern.responsiblechain;

/**
 * Created by xiaowenzi on 2019/1/15.
 */
public class HTMLFilter implements Filter {
    @Override
    public void doFilter(Request req, Response rep, FilterChain chain) {
        req.setReqStr(req.getReqStr().replace("<script>", "<>"));
        req.setReqStr(req.getReqStr() + "---HtmlFilter()---");
        chain.doFilter(req, rep);
        rep.setRepStr(rep.getRepStr() + "---HtmlFilter()---");
    }
}
