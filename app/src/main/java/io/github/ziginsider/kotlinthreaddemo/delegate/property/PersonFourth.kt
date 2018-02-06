package io.github.ziginsider.kotlinthreaddemo.delegate.property

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Created by zigin on 04.02.2018.
 */

class PersonFourth(val name: String,
                   age: Int,
                   salary: Int) : PropertyChangeAware() {

    private val observer = {
        prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}