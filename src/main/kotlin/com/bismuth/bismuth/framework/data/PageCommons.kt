package com.bismuth.bismuth.framework.data

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable


class PageCommons {
    companion object {
        fun <T> getPaged(pageable: Pageable, listOfObjects: List<T>): Page<T> {
            var end: Int = (pageable.pageNumber * pageable.pageSize) - 1;
            val start: Int = (pageable.pageNumber - 1) * pageable.pageSize;
            if(end > listOfObjects.size)
                end = listOfObjects.size - 1;
            val pageRequest: PageRequest = PageRequest.of(pageable.pageNumber - 1, pageable.pageSize, pageable.sort);
            return PageImpl<T>(listOfObjects.subList(start, end + 1), pageRequest, listOfObjects.size.toLong());
        }
    }
}