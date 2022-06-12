package com.example.coffeeapp.di

import com.example.coffeeapp.di.login.LoginComponent
import dagger.Module

@Module(subcomponents = [LoginComponent::class])
class AppSubComponent