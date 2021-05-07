package com.emirgazikopar.stopwatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var sayac2 = 0
    var number = 0
    //var result : Int? = null
    var sayac : Int = 0
    var handler : Handler = Handler(Looper.getMainLooper()) //(en) We will use it to specify how long the 'run ()' function will complete unit frequency.
    //(tr) Handler'ı run() fonksiyonunun ne kadar süre sonra sonlanacağını belirtmek için kullanacağız bu bizim zamanlayıcımız olacak.

    var runnable : Runnable = Runnable {  } //(en)Since this class 'Runnable' is an interface class , we define it this way.
    //(tr)Bu 'interface' yanı soyut bir sınıf oldığı için bu şekilde tanımlıyoruz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sayac==0
    }

    fun start(view: View){
        if(sayac==0){
            handler.post(runnable)//direk kontrolu görmeden içeri dalar o yüzden bununda kontrol edilmesi lazım
        }
        //result = 0
        sayac+=1
        //onResume()
        sayac2+=1
        if(sayac == 1){ //bununda faydası varmış

            runnable = object  : Runnable{

                override fun run() {
                    //(en)What is written into 'run' runs periodically in the background
                    //(tr)'run' kısmına yazılanlar periyodik olarak arka planda çalışır

                    number = number + 1
                    textView.text = "Time: ${number}"
                    handler.postDelayed(this, 1000) //'runnable' ve 'gecikme'(delayMillis) miktarını istiyor. Runnable'yi nasıl verebiliriz ? this ile tabi ki .
                    //this context'i ne yapıyordu çoğu zaman activty'e referans veriyordu fakat bazı özel durumlarda içinde bulunduğu fonksiyona veya listenera referans verebildiği anlar da oluyordu işte bu o anlardan.

                }

            }
            handler.post(runnable) //Runnable başlar
        }

        //onResume() buraya yazınca 2-4-6 diye gider çünkü fonksiyon iki kere çağırılır demekki onResumeyi başa yazdığımızda sadece bu çalışıyormuş diğeri değil çünü rakamlar 1-2-3 şeklindeydi

    }
    fun stop(view: View){//stop result değeri 1 olsun
        sayac == 0
        //result = 0
        handler.removeCallbacks(runnable)
        textView.text = "Time: ${number}"

    }

    fun restart(view: View){//restart result değeri 2 olsun
        sayac==0
        //result = 2
        handler.removeCallbacks(runnable)

        number = 0
        textView.text = "Time: ${number}"
        handler.post(runnable)

    }
    /* override fun onResume() {//On resume her çağırılıdığında burası çalışacak
         super.onResume()

         if(result==0 && sayac==1){//Buda çalışıyor denendi //Buradaki kontrol çok değerli. start fonksiyonu içersinde asla control yapılmıyor işi burada çözmemiz lazım. Her bir butona bir değer verelim gelen değerlere göre if ile seçilim yapalım

             handler.post(runnable)

         }
         else if (result==1){
             sayac = 0
         }
         else if(result==2){
             sayac = 0

         }

     }*/

}