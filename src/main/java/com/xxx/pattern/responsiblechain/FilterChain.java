package com.xxx.pattern.responsiblechain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaowenzi on 2019/1/15.
 */
public class FilterChain {
    private List<Filter> filters = new ArrayList<>();
    private int index = 0;

    public FilterChain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    public void doFilter(Request request, Response response) {
        if (index == filters.size()) {
            return;
        }

        Filter filter = filters.get(index);
        index++;
        filter.doFilter(request, response, this);
    }
}
