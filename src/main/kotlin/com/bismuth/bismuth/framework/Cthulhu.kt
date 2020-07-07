package com.bismuth.bismuth.framework

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class Cthulhu : ApplicationContextAware {

    @Throws(BeansException::class)
    override fun setApplicationContext(context: ApplicationContext) {
        Companion.context = context
    }

    companion object {
        private var context: ApplicationContext? = null
        fun <T : Any?> getBean(beanClass: Class<T>): T {
            return context!!.getBean(beanClass)
        }
    }
}