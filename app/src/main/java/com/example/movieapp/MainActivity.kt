package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.movieapp.R
import com.example.movieapp.ui.main.MainFragment
import io.vertx.core.cli.impl.ReflectionUtils.newInstance
import java.lang.reflect.Array.newInstance
import java.net.URLClassLoader.newInstance
import javax.xml.datatype.DatatypeFactory.newInstance
import javax.xml.parsers.DocumentBuilderFactory.newInstance

class MainActivity : AppCompatActivity() {
    var toolbarLayout: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarLayout = findViewById(R.id.teams_upcoming_bar)
        setSupportActionBar(toolbarLayout)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commitNow()
        }
    }
}