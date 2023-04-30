package com.example.mytodoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.mytodoapp.Task.Add_Task
import com.example.mytodoapp.Task.TaskList
import com.example.mytodoapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMainBinding.inflate(layoutInflater)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(binding.root)
        binding.addPlan.setOnClickListener {
            startActivity(Intent(this,Add_Task::class.java))
        }
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.todolist, TaskList())
            commit()
        }
        val animation: Animation? = AnimationUtils.loadAnimation(this, R.anim.animation)
        binding.content.animation=animation
    }
}