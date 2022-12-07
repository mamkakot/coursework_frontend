package com.example.courseworkgum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import java.nio.ByteBuffer
import java.util.concurrent.Executor
import java.util.concurrent.Executors

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private var myBtn: Button? = null
    private var choreRecyclerView: RecyclerView? = null
    var choreList: ArrayList<ChoreModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myBtn = findViewById(R.id.button)
        choreRecyclerView = findViewById(R.id.choreRecyclerView)


        val myBuilder = CronetEngine.Builder(this)
        val cronetEngine: CronetEngine = myBuilder.build()
        val executor: Executor = Executors.newSingleThreadExecutor()

        val requestBuilder = cronetEngine.newUrlRequestBuilder(
            "http://10.0.2.2:8000/api-auth/rooms/",
            ChoreUrlRequestCallback(),
            executor
        )

        val request: UrlRequest = requestBuilder.build()


        setUpChoreModels()

        choreRecyclerView?.adapter = ChoreModelAdapter(context = this, choreModels = choreList)
        choreRecyclerView?.layoutManager = LinearLayoutManager(this)

        myBtn?.setOnClickListener {
            Toast.makeText(this, "sas", Toast.LENGTH_SHORT).show()
            request.start()
            if (request.isDone) {
                val sas = ByteBuffer.allocate(20)
                request.read(sas)
                Log.i(TAG, "I made a request!! $sas")
            }
        }
    }

    private fun setUpChoreModels() {
        for (i in 1..10) {
            choreList.add(ChoreModel("sas$i"))
        }
    }
}