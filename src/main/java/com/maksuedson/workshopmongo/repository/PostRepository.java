package com.maksuedson.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.maksuedson.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle (String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ $and: [{date: {$gte: ?1}} ,{date: {$lte: ?2}}, {$or: [{ 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?1, $options: 'i' } }, { 'comments.text': { $regex: ?2, $options: 'i' } }]} ]}")
	List<Post> fulSearch(String text, Date minDate, Date maxDate);
}
