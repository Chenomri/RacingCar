package com.example.racingcar

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.racingcar.fragments.MapsFragment
import com.example.racingcar.fragments.ScoreListFragment
import com.example.racingcar.utilities.CarRacingSharedPreferences

class MainActivity3 : AppCompatActivity() {

    private lateinit var  FRAME_score_list: FrameLayout
    private lateinit var  FRAME_map: FrameLayout
    private lateinit var  score_list_fragment: ScoreListFragment
    private lateinit var  map_fragment: MapsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        findViews()
        initViews()

    }


    private fun findViews() {
        FRAME_score_list= findViewById(R.id.FRAME_score_list)
        FRAME_map=findViewById(R.id.FRAME_map)
    }

    private fun initViews() {

        score_list_fragment= ScoreListFragment()
        supportFragmentManager.beginTransaction().add(R.id.FRAME_score_list,score_list_fragment).commit()

        map_fragment= MapsFragment()
        supportFragmentManager.beginTransaction().add(R.id.FRAME_map,map_fragment).commit()
    }



}