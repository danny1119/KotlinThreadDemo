package io.github.ziginsider.kotlinthreaddemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import io.github.ziginsider.kotlinthreaddemo.adapters.Kadapter
import io.github.ziginsider.kotlinthreaddemo.adapters.setUp
import io.github.ziginsider.kotlinthreaddemo.adapters.setUpIm
import io.github.ziginsider.kotlinthreaddemo.delegate.property.PersonFirst
import io.github.ziginsider.kotlinthreaddemo.delegate.property.PersonFourth
import io.github.ziginsider.kotlinthreaddemo.delegate.property.PersonSecond
import io.github.ziginsider.kotlinthreaddemo.delegate.property.PersonThird
import io.github.ziginsider.kotlinthreaddemo.model.Destination

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.item_view.view.*
import kotlinx.serialization.Mapper
import kotlinx.serialization.json.JSON
import java.beans.PropertyChangeListener
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock
import kotlin.collections.ArrayList
import kotlin.concurrent.scheduleAtFixedRate
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType
import kotlin.system.measureNanoTime

class MainActivity : AppCompatActivity() {

    private val TAG = "Zig"

    private var recyclerAdapter: Kadapter<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        println(listOf(2,7,4,2,3,1,1,5,55,4,333,4,5,7,1,1,1,444)
                .qSort()
                .joinToString(
                separator = ", ",
                prefix = "# ",
                postfix = ";",
                transform = { it.toString() })
        )

//        repeat(5) {
//            Log.d(TAG, "Time ${it*2} ")
//        }
//
//        fizzBuzzRightOrder()
//        fizzBuzzInverseOrder()
//
//        printFigures()
//
        startConcurent(textView)

        val users = MockDataProvider().data


        //initListApply(users)

        setUpRecyclerView(users)

        bottomNavigation.setOnNavigationItemReselectedListener {
            item: MenuItem ->
            when (item.itemId) {
                R.id.sortName -> {
                    users.sortBy { it.name }
                    updateAdapter(users)

                }
                R.id.sortAge -> {
                    users.sortBy { it.age }
                    setUpRecyclerView(users)
                    updateAdapter(users)
                }
                R.id.delEven -> {}
            }
            true
        }

        val delhi = Destination(name = "Delhi", country = "India", code = 0)
        val delhiAsString = JSON.stringify(delhi)
        println(delhiAsString)

        val newYork = JSON.parse<Destination>(
                "{\"name\":\"New York\",\"country\":\"USA\",\"code\":3}"
        )
        println(newYork)

        val paris = JSON.unquoted.parse<Destination>(
                "{name:Paris,country:France,code:10}")
        println(paris)

        val barcelona = JSON.unquoted.parse<Destination>(
                "{name:Barcelona,country:Spain,code:5,isMetro:true}")
        println(barcelona)

        val newYorkAsMap : Map<String,Any> = Mapper.map(newYork) // Mapping
        val newNewYork = Mapper.unmap<Destination>(newYorkAsMap) //Unmapping


        //Person First

        val p = PersonFirst("Aiaksei", 31, 2000)
        p.addPropertyChangeListener(
                PropertyChangeListener { event ->
                    println("Property ${event.propertyName} changed "
                    + "from ${event.oldValue} to ${event.newValue}")
                }
        )
        p.age = 32
        println("Little time has passed...\n")
        p.salary = 2500

        val p2 = PersonSecond("Aliaksei", 31, 2100)
        p2.addPropertyChangeListener(PropertyChangeListener { event ->
            println("Property ${event.propertyName} changed "
                    + "from ${event.oldValue} to ${event.newValue}")
        })
        p2.age = 33
        println("Little time has passed...\n")
        p2.salary = 3000

        val p3 = PersonThird("Aliaksei", 31, 2100)
        p3.addPropertyChangeListener(PropertyChangeListener { event ->
            println("Property ${event.propertyName} changed "
                    + "from ${event.oldValue} to ${event.newValue}")
        })
        p3.age = 30
        println("Little time has passed...\n")
        p3.salary = 3100

        val p4 = PersonFourth("Aliaksei", 31, 2000)
        p4.addPropertyChangeListener(PropertyChangeListener { event ->
            println("Property ${event.propertyName} changed "
                    + "from ${event.oldValue} to ${event.newValue}")
        })
        p4.age = 28
        println("Little time has passed...\n")
        p4.salary = 3400


    }

    private fun foo(l: Lock) {
        println("Before sync")
        synchronized(l) {
            println("Action")
        }
        println("After sync")
    }

    class LockIm : Lock {
        override fun lock() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun tryLock(p0: Long, p1: TimeUnit?): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun unlock() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun lockInterruptibly() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun newCondition(): Condition {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun tryLock(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    private fun updateAdapter(items: List<User>) {
        recyclerAdapter?.update(items) ?: setUpRecyclerView(items)
    }

    private fun setUpRecyclerView(items: List<User>) {
        recyclerAdapter = recyclerView.setUpIm(items, R.layout.item_view, {
            when(it.age) {
                in 0..7 -> shotImage.setImageResource(R.drawable.user12)
                in 8..15 -> shotImage.setImageResource(R.drawable.user9)
                in 16..23 -> shotImage.setImageResource(R.drawable.user11)
                in 24..30 -> shotImage.setImageResource(R.drawable.user3)
                in 31..39 -> shotImage.setImageResource(R.drawable.user7)
                in 40..50 -> shotImage.setImageResource(R.drawable.user8)
                in 51..58 -> shotImage.setImageResource(R.drawable.user4)
                in 59..67 -> shotImage.setImageResource(R.drawable.user1)
                in 68..76 -> shotImage.setImageResource(R.drawable.user6)
                in 77..85 -> shotImage.setImageResource(R.drawable.user2)
                in 85..92 -> shotImage.setImageResource(R.drawable.user10)
                in 93..100 -> shotImage.setImageResource(R.drawable.user5)
            }
            userName.text = it.name
            userAge.text = it.age.toString()
        }, {
            toast("Clicked $id $name $age !!!")
        })
        recyclerView.scheduleLayoutAnimation()

    }


    fun initListLazy(users: ArrayList<User>) = println("Time SetUp() with lazy() = " +
            "${ measureNanoTime { recyclerView.setUp(users, R.layout.item_view, {
            userName.text = it.name
            userAge.text = it.age.toString()
    }, {
        toast("Clicked $id $name $age")
    }) }}")

    fun initListApply(users: ArrayList<User>) = println("Time SetUpIm() with apply() = " +
            "${ measureNanoTime { recyclerView.setUpIm(users, R.layout.item_view, {
            userName.text = it.name
            userAge.text = it.age.toString()

    }, {
        toast("Clicked $id $name $age im")
    }) }}")


    fun printFigures() {
        val list = ArrayList<Int>()
        for (i in 0..99) {
            list.add(i*i)

        }
        for((index, element) in list.withIndex()) {
            println("$index: $element")
        }
    }

    private fun startConcurent(textView: TextView) {

        val cp = startConsumerProducer()

        fab.setOnClickListener { view ->

            val timer = Timer("Schedule", true)
            timer.scheduleAtFixedRate(1000, 1000) {
                Handler(Looper.getMainLooper()).post(Runnable {
                    textView.text = cp.list.joinToString(
                            separator = ", ",
                            prefix = "# ",
                            postfix = ";",
                            transform = { it.toString().toUpperCase() })
                })
            }

        }
    }

    private fun fizzBuzzRightOrder() {
        for (i in 1..100) {
            Log.d(TAG, fizzBuzz(i))
        }
    }

    private fun fizzBuzzInverseOrder() {
        for (i in 100 downTo 1 step 2) {
            Log.d(TAG, fizzBuzz(i))
        }
    }

    private fun fizzBuzz(i: Int) = when {
        i % 15 == 0 -> "FizzBuzz "
        i % 3 == 0 -> "Fizz "
        i % 5 == 0 -> "Buzz "
        else -> "$i "
    }

    private fun startConsumerProducer(): ConsumerProducer {
        val cp = ConsumerProducer()

        val threadProduce = Thread(Runnable {
            try {
                cp.produce()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        })

        val threadConsume = Thread(Runnable {
            try {
                cp.consume()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        })

        threadProduce.start()
        threadConsume.start()
        return cp
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
