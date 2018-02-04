package io.github.ziginsider.kotlinthreaddemo.delegate.property

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

/**
 * Created by zigin on 04.02.2018.
 */

open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}