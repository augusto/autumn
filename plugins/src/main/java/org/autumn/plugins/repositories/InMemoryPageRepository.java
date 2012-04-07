package org.autumn.plugins.repositories;

import org.autumn.model.domain.Page;
import org.autumn.model.repositories.PageRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryPageRepository implements PageRepository {

    private Map<String, Page> pages = new HashMap<String, Page>();

    @Override
    public Page findPageByName(String pageName) {
        return pages.get(pageName);
    }

}
