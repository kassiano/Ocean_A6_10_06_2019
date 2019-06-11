package br.com.ocean_android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btBuscar.setOnClickListener {

            val language = editBusca.text.toString()

            efetuarBuscar(language)

        }


    }

    fun efetuarBuscar(language:String){

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        val call = service.buscarRepositorios("language:${language}")

        call.enqueue( object: Callback<GitHubResult> {
            override fun onFailure(call: Call<GitHubResult>, t: Throwable) {

            }

            override fun onResponse(call: Call<GitHubResult>, response: Response<GitHubResult>) {

                val result = response.body()

                if(result != null){

                    var nomes = ""
                    result.items.forEach {
                        nomes += "Nome: ${it.name} \tlogin:${it.owner.login}\n"
                    }

                    tvRepositorios.text = nomes

                }

            }

        } )
    }

}
