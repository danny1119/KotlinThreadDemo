package io.github.ziginsider.kotlinthreaddemo.delegate.property

import java.beans.PropertyChangeSupport

/**
 * Created by zigin on 04.02.2018.
 */

class ObservableProperty(val propName: String,
                         var propValue: Int,
                         val changeSupport: PropertyChangeSupport) {
    fun getValue(): Int = propValue
    fun setValue(newValue: Int){
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName, oldValue, newValue)
    }
}