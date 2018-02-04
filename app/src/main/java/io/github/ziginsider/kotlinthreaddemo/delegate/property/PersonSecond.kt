package io.github.ziginsider.kotlinthreaddemo.delegate.property

/**
 * Created by zigin on 04.02.2018.
 */

class PersonSecond(val name: String,
                   age: Int,
                   salary: Int): PropertyChangeAware() {

    val _age = ObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) { _age.setValue(value)}

    val _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) { _salary.setValue(value)}
}