package com.example.calculatorapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.calculatorapp.data.locale.OperationDao
import com.example.calculatorapp.data.locale.OperationDatabase
import com.example.calculatorapp.data.repository.OperationRepositoryImpl
import com.example.calculatorapp.domain.repository.OperationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : OperationDatabase {
        return Room.databaseBuilder(
            context,
            OperationDatabase::class.java,
            "OperationDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db : OperationDatabase) : OperationDao {
        return db.dao()
    }

    @Provides
    @Singleton
    fun provideRepository(db : OperationDatabase) : OperationRepository {
        return OperationRepositoryImpl(db.dao())
    }

}