package com.example.alejandro.app1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.alejandro.app1.models.Account;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import static java.security.AccessController.getContext;

/**
 * Created by Kartik on 4/24/2017.
 */

public class IntroActivity extends AppIntro {

    Account account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle extras = getIntent().getExtras();
        account = new Account(extras.getInt("id"), extras.getString("email"), extras.getString("username"), extras.getString("password"));

        addSlide(AppIntroFragment.newInstance("Welcome!", "Welcome to Rags To Riches!\n A game designed to help you get acclimated with the stock market.", R.drawable.ragstoricheslogo, Color.parseColor("#4caf50")));
        addSlide(AppIntroFragment.newInstance("Game Navigation", "Use one of the game buttons to start playing!", R.drawable.intro2, Color.parseColor("#4caf50")));
        addSlide(AppIntroFragment.newInstance("Options", "Track your achievement progress\n or modify your game settings.", R.drawable.intro3, Color.parseColor("#4caf50")));
        addSlide(AppIntroFragment.newInstance("Achievements", "Try to obtain achievements to measure your progress.", R.drawable.achievements_page, Color.parseColor("#4caf50")));
        addSlide(AppIntroFragment.newInstance("Game Overlay", "Balance: The money you have to spend\n\nPortfolio: The stocks you own\n\nNext Turn: Advance one week in the game", R.drawable.intro4, Color.parseColor("#4caf50")));
        addSlide(AppIntroFragment.newInstance("Company Options", "Invest in a company by buying a piece of stock.\n\nSell a company's stock to get a monetary return.\n\nUse the available information to aid your decision.", R.drawable.intro5, Color.parseColor("#4caf50")));
        addSlide(AppIntroFragment.newInstance("Trends", "Use each company's stock price trends to infer the future of the company.", R.drawable.intro6, Color.parseColor("#4caf50")));
        addSlide(AppIntroFragment.newInstance("Goal", "You have 25 turns to make as much money as possible.\nAll stocks owned at the end of the game will be sold.", R.drawable.intro7, Color.parseColor("#4caf50")));
        addSlide(AppIntroFragment.newInstance("Results", "Congratulations (if you have won)!\n\nHave fun!", R.drawable.intro8, Color.parseColor("#4caf50")));

        setBarColor(Color.parseColor("#29702c"));
        setSeparatorColor(Color.parseColor("#388e3c"));

        showSkipButton(true);
        showStatusBar(false);
        setVibrate(false);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
        i.putExtra("id", account.getId());
        i.putExtra("email", account.getEmail());
        i.putExtra("username", account.getUsername());
        i.putExtra("password", account.getPassword());
        startActivity(i);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
        i.putExtra("id", account.getId());
        i.putExtra("email", account.getEmail());
        i.putExtra("username", account.getUsername());
        i.putExtra("password", account.getPassword());
        startActivity(i);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}
