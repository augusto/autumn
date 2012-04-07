package org.autumn.model.repositories;

import org.autumn.model.domain.Page;

public interface PageRepository {

    Page findPageByName(String pageName);
}
