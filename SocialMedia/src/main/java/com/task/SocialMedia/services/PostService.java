package com.task.SocialMedia.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.SocialMedia.customexception.APIException;
import com.task.SocialMedia.customexception.ResourceNotFoundException;
import com.task.SocialMedia.dto.PostDTO;
import com.task.SocialMedia.models.Post;
import com.task.SocialMedia.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) {
            throw new APIException("No posts are present");
        }
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }

    public PostDTO findPostById(int id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return modelMapper.map(post, PostDTO.class);
    }

    public PostDTO createPost(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDTO.class);
    }

    public PostDTO updatePost(int id, PostDTO postDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setMessage(postDTO.getMessage()); // update necessary fields
        post = postRepository.save(post);
        return modelMapper.map(post, PostDTO.class);
    }

    public void deletePost(int id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }
}
