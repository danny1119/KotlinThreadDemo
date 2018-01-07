package io.github.ziginsider.kotlinthreaddemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val textView : TextView = findViewById(R.id.textView)

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
//
//                //set in MainThread to avoid CalledFromWrongThreadExeption
//                Handler(Looper.getMainLooper()).post(Runnable {
//                    textView.text = cp.list
//                })

            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        })

        threadProduce.start()
        threadConsume.start()

        fab.setOnClickListener { view ->

            textView.text = cp.list
        }
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
