package io.github.ziginsider.kotlinthreaddemo

/**
 * Created by zigin on 07.01.2018.
 */

class testJava {

    internal val cp = ConsumerProducer()

    internal fun startThread(): Int {

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

        return 1
    }
}
