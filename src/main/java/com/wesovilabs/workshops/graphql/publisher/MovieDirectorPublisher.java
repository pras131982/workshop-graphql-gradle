package com.wesovilabs.workshops.graphql.publisher;

import com.wesovilabs.workshops.graphql.converter.MovieEntityToMovieConverter;
import com.wesovilabs.workshops.graphql.database.model.MovieEntity;
import com.wesovilabs.workshops.graphql.domain.Movie;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MovieDirectorPublisher {


    @Autowired
    private MovieEntityToMovieConverter converter;

    private final Flowable<MovieEntity> publisher;

    private ObservableEmitter<MovieEntity> emitter;

    public MovieDirectorPublisher() {
        Observable<MovieEntity> movieUpdateObservable = Observable.create(emitter -> {
            this.emitter = emitter;
        });
        ConnectableObservable<MovieEntity> connectableObservable = movieUpdateObservable.share().publish();
        connectableObservable.connect();
        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }


    public void publish(final MovieEntity movie) {
        emitter.onNext(movie);
    }

    public Flowable<Movie> getPublisher(Integer directorId) {
        return publisher.filter(movie -> check(movie, directorId))
                .map(converter::convert);
    }

    private boolean check(MovieEntity movie, Integer directorId) {
        if (movie != null) {
            return movie.getDirector().getId() == directorId;
        }
        return false;
    }
}
