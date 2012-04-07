package org.autumn.inject;

import org.autumn.model.repositories.PageRepository;
import org.autumn.plugins.repositories.InMemoryPageRepository;

public class InjectModel {

    private static final PageRepository pageRepository = new InMemoryPageRepository();

    private InjectModel() {
    }

    public static PageRepository injectPageRepository() {
        return pageRepository;
    }
}
