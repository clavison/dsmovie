package com.devsuperior.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.Movie;
import com.devsuperior.dsmovie.entities.Score;
import com.devsuperior.dsmovie.entities.User;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {

	@Autowired
	MovieRepository movieRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ScoreRepository scoreRepository;
	
	@Transactional
	public MovieDTO saveScore(ScoreDTO dto) {
		User user = userRepository.findByEmail(dto.getEmail());
		if(user == null) {
			user = new User(null, dto.getEmail());
			user = userRepository.saveAndFlush(user);
		}
		Movie movie = movieRepository.findById(dto.getMovieId()).get();
//		ScorePk pk = new ScorePk(user, movie);
//		Score score = new Score(pk, dto.getScore());
		
		Score score = new Score();
		score.setUser(user);
		score.setMovie(movie);
		score.setValue(dto.getScore());
		score = scoreRepository.saveAndFlush(score);
		
		double sum = 0;
		
		for(Score s : movie.getScores()) {
			sum += s.getValue();
		}
		
		double avg = sum / movie.getScores().size();
		
		movie.setScore(avg);
		movie.setCount( movie.getScores().size());
		
		movie = movieRepository.save(movie);
		
		return new MovieDTO(movie);
	}

}
