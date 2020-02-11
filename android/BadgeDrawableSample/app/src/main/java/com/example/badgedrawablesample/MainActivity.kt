package com.example.badgedrawablesample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.badge.BadgeDrawable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    //region TabLayout
    addTabDotBadgeButton.setOnClickListener {
      val badge: BadgeDrawable = tabLayout.getTabAt(0)?.orCreateBadge ?: return@setOnClickListener
      badge.isVisible = true
    }

    increaseTabBadgeCountButton.setOnClickListener {
      tabLayout.getTabAt(1)?.orCreateBadge?.apply {
        number += 1
        isVisible = true
      }

      tabLayout.getTabAt(2)?.orCreateBadge?.apply {
        // バッジの上限数は桁数のみ指定可能、具体的な数値の指定は不可
        maxCharacterCount = 2
        number += 1
        isVisible = true
      }
    }

    removeAllTabBadgesButton.setOnClickListener {
      repeat(tabLayout.tabCount) {
        tabLayout.getTabAt(it)?.removeBadge()
      }
    }
    //endregion

    //region BottomNavigationView
    addBottomNavDotBadgeButton.setOnClickListener {
      val badge: BadgeDrawable = bottomNavigation.getOrCreateBadge(R.id.menu1)
      badge.isVisible = true
    }

    increaseBottomNavBadgeCountButton.setOnClickListener {
      bottomNavigation.getOrCreateBadge(R.id.menu2).apply {
        number += 1
        isVisible = true
      }

      bottomNavigation.getOrCreateBadge(R.id.menu3).apply {
        // バッジの上限数は桁数のみ指定可能、具体的な数値の指定は不可
        maxCharacterCount = 2
        number += 1
        isVisible = true
      }
    }

    removeAllBottomNavBadgesButton.setOnClickListener {
      bottomNavigation.removeBadge(R.id.menu1)
      bottomNavigation.removeBadge(R.id.menu2)
      bottomNavigation.removeBadge(R.id.menu3)
    }
    //endregion
  }
}
