package io.github.ziginsider.kotlinthreaddemo.delegate.property

import java.beans.PropertyChangeSupport
import kotlin.reflect.KProperty

/**
 * Created by zigin on 04.02.2018.
 */

class ObservablePropertyThird(var propValue: Int,
                              val changeSupport: PropertyChangeSupport) {

    operator fun getValue(p: PersonThird, prop: KProperty<*>): Int = propValue

    operator fun setValue(p: PersonThird, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}