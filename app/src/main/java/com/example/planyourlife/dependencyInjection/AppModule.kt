package com.example.planyourlife.dependencyInjection;

import com.example.planyourlife.data.datasource.DailyPlanningDataSource;
import com.example.planyourlife.data.repository.DailyPlanningRepository;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
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
    fun provideDailyPlanningDataSource(): DailyPlanningDataSource {
        return DailyPlanningDataSource();
    }

}


