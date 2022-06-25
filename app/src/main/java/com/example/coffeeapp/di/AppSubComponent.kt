package com.example.coffeeapp.di

import com.example.coffeeapp.di.login.LoginComponent
import com.example.coffeeapp.di.main.MainComponent
import dagger.Module

@Module(subcomponents = [LoginComponent::class, MainComponent::class])
class AppSubComponent