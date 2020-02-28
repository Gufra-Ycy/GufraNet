package com.gufra.net.example

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gufra.net.base.utils.FileManager
import com.gufra.net.rxnetimp.NetManager
import com.gufra.ui.activiity.GufraPayActivity
import com.gufra.ui.activiity.TabActivity
import com.gufra.ui.view.FloatActionView
import com.gufra.ui.view.VerifyCodeView

class MainActivity : AppCompatActivity() {
    var url = "https://api.lovelive.tools/api/SweetNothings/Serialization/:serializationType"
    var txtResult: TextView? = null
    var codeView: VerifyCodeView? = null
    var floatView: FloatActionView? = null
    var str:String = "s"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtResult = findViewById<View>(R.id.txt_result) as TextView
        codeView = findViewById<View>(R.id.view_code) as VerifyCodeView
        codeView!!.setOnClickReFresh(true)
        floatView = findViewById<View>(R.id.view_float) as FloatActionView
        floatView!!.bringToFront()
        FileManager.writeFile(this, "gufra.txt", "This is gufra's Test!!!")
    }

    /**存储 */
    fun OnQ(view: View?) {
        txtResult!!.text = FileManager.readFile(this, "gufra.txt")
    }

    override fun onStop() {
        super.onStop()
        NetManager.getInstance(this).onStop()
    }

    fun OnTab(view: View?) {
        startActivity(Intent(this@MainActivity, TabActivity::class.java))
    }

    /**支付 */
    fun onPay(view: View?) {
        startActivity(Intent(this@MainActivity, GufraPayActivity::class.java))
    }
}