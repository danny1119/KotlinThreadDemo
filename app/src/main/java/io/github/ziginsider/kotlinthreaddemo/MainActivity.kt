package io.github.ziginsider.kotlinthreaddemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.scheduleAtFixedRate

class MainActivity : AppCompatActivity() {

    private val TAG = "Zig"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        repeat(5) {
            Log.d(TAG, "Time ${it*2} ")
        }

        fizzBuzzRightOrder()
        fizzBuzzInverseOrder()

        printFigures()

        //startConcurent()
    }

    fun printFigures() {
        val list = ArrayList<Int>();
        for (i in 0..99) {
            list.add(i*i)
        }
        for((index, element) in list.withIndex()) {
            println("$index: $element")
        }


    }

    private fun startConcurent() {

        val textView : TextView = findViewById(R.id.textView)

        val cp = startConsumerProducer()


        fab.setOnClickListener { view ->

            val timer = Timer("Schedule", true)
            timer.scheduleAtFixedRate(1000, 1000) {
                Handler(Looper.getMainLooper()).post(Runnable {
                    textView.text = "${cp.list}"
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
