package io.github.ziginsider.kotlinthreaddemo.delegate.property

/**
 * Created by zigin on 04.02.2018.
 */

class PersonThird(val name: String,
                  age: Int,
                  salary: Int): PropertyChangeAware() {

    var age: Int by ObservablePropertyThird(age, changeSupport)
    var salary: Int by ObservablePropertyThird(salary, changeSupport)
}