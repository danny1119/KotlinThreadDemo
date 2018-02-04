package io.github.ziginsider.kotlinthreaddemo.delegate.property

/**
 * Created by zigin on 04.02.2018.
 */

class PersonFirst(val name: String, age: Int, salary: Int): PropertyChangeAware() {

    var age: Int = age
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("age",
                    oldValue,
                    newValue)
        }

    var salary: Int = salary
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("salary",
                    oldValue,
                    newValue)
        }
}