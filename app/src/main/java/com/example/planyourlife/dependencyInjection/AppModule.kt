package com.example.planyourlife.dependencyInjection;

import android.content.Context
import androidx.room.Room
import com.example.planyourlife.data.datasource.DailyPlanningDataSource;
import com.example.planyourlife.data.repository.DailyPlanningRepository;
import com.example.planyourlife.room.Database
import com.example.planyourlife.room.TimeLineTaskDAO
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideDailyPlanningRepository(dataSource: DailyPlanningDataSource): DailyPlanningRepository{
        return DailyPlanningRepository(dataSource);
    }

    @Provides
    @Singleton
    fun provideDailyPlanningDataSource(timeLineTaskDAO: TimeLineTaskDAO): DailyPlanningDataSource {
        return DailyPlanningDataSource(timeLineTaskDAO);
    }

    @Provides
    @Singleton
    fun provideTimeLineTaskDAO(@ApplicationContext context: Context): TimeLineTaskDAO
    {
        val database = Room.databaseBuilder(context, Database::class.java, "planyourlife.db")
            .createFromAsset("planyourlife.db").build()
        return database.getTimeLineTaskDAO()
    }
}


