package com.bismuth.bismuth.framework.data

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class PageCommons {
    companion object {
        fun <T> getPaged(pageable: Pageable, listOfObjects: List<T>): Page<T> {
            val start = pageable.offset.toInt();
            val end = (if (start + pageable.pageSize > listOfObjects.size) listOfObjects.size else start + pageable.pageSize);
            return PageImpl<T>(listOfObjects.subList(start, end), pageable, listOfObjects.size.toLong());
        }
    }
}