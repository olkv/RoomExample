package com.example.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.roomexample.databinding.ActivityMainBinding
import com.example.roomexample.db.AppDatabase
import com.example.roomexample.db.User
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    var db: AppDatabase? = null

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,"roomdb2").build()

        binding.addUser.setOnClickListener {
            addUser()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        db?.close()
    }


    fun addUser() {
        val user = User(5, "Peter", "Ivanov")

        //Чтобы не падало в ошибку нужно запускать в отдельном потоке
        val job = thread(false) {
            db?.userDao()?.insertUser(user)
            Log.d("ROOMDB", "Add User...")
        }
        job.start()

    }

}