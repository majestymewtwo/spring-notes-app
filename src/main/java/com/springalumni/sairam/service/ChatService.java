package com.springalumni.sairam.service;

import com.springalumni.sairam.dto.ChatDTO;
import com.springalumni.sairam.dto.DomainDTO;
import com.springalumni.sairam.dto.PostDTO;
import com.springalumni.sairam.dto.RepliesDTO;
import com.springalumni.sairam.exception.ApiException;
import com.springalumni.sairam.mapper.*;
import com.springalumni.sairam.models.*;
import com.springalumni.sairam.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DomainRepository domainRepository;
    private final DomainMapper domainMapper;
    private final ChatSpaceRepository chatSpaceRepository;
    private final ChatMapper chatMapper;
    private final PostsRepository postsRepository;
    private final PostMapper postMapper;
    private final RepliesRepository repliesRepository;
    private final RepliesMapper repliesMapper;
    public Domain createDomain(Domain newDomain) {
        return domainRepository.save(newDomain);
    }
    public List<DomainDTO> getAllDomains() {
        List<Domain> domains = domainRepository.findAll();
        return domains.stream().map(domainMapper::mapToDTO).toList();
    }
    public ChatDTO createNewChat(ChatDTO chatDTO){
        Domain domain = domainRepository.findById(chatDTO.getDomain().getId())
                .orElseThrow(() -> new ApiException("Invalid Domain ID", HttpStatus.BAD_REQUEST));
        ChatSpace chatSpace = chatMapper.mapToEntity(chatDTO);
        chatSpace.setDomain(domain);
        return chatMapper.mapToDTO(chatSpaceRepository.save(chatSpace));
    }
    public List<ChatDTO> getAllChatSpaces() {
        List<ChatSpace> chatSpaces = chatSpaceRepository.findAll();
        return chatSpaces.stream().map(chatMapper::mapToDTO).toList();
    }

    public PostDTO createNewPost(PostDTO postDTO, String chatId) {
        ChatSpace chatSpace = chatSpaceRepository.findById(chatId)
                .orElseThrow(() -> new ApiException("Invalid Chat ID", HttpStatus.BAD_REQUEST));
        User postCreator = userRepository.findByEmail(postDTO.getUser().getEmail())
                .orElseThrow(() -> new ApiException("User not found with email", HttpStatus.BAD_REQUEST));
        Posts newPost = postMapper.mapToEntity(postDTO);
        newPost.setUser(postCreator);
        newPost.setChatSpace(chatSpace);
        newPost.setUpVotedUsers(new HashSet<>());
        newPost.setDownVotedUsers(new HashSet<>());
        PostDTO savedPost = postMapper.mapToDTO(postsRepository.save(newPost));
        savedPost.setUser(userMapper.mapToDTO(postCreator));
        savedPost.setReplies(getAllReplies(savedPost.getId()));
        return savedPost;
    }

    public PostDTO getPostDetails(String postId) {
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new ApiException("Invalid Post ID", HttpStatus.BAD_REQUEST));
        PostDTO postDTO = postMapper.mapToDTO(post);
        postDTO.setUser(userMapper.mapToDTO(post.getUser()));
        postDTO.setReplies(getAllReplies(postId));
        return postDTO;
    }
    public List<RepliesDTO> getAllReplies(String postId) {
        List<Replies> repliesList = repliesRepository.findAllByPosts_Id(postId);
        List<RepliesDTO> repliesDTOS = new ArrayList<>();
        return repliesList.stream().map(repliesMapper::mapToDTO).toList();
    }

    public void addPostComment(RepliesDTO repliesDTO, String postId) {
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new ApiException("Invalid Post ID", HttpStatus.BAD_REQUEST));
        User user = userRepository.findByEmail(repliesDTO.getUser().getEmail())
                .orElseThrow(() -> new ApiException("User not found with email", HttpStatus.BAD_REQUEST));
        Replies replies = repliesMapper.mapToEntity(repliesDTO);
        replies.setPosts(post);
        replies.setUser(user);
        replies.setUpVotedUsers(new HashSet<>());
        replies.setDownVotedUsers(new HashSet<>());
        repliesRepository.save(replies);
    }
    public void upVotePost(String postId, String email) {
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new ApiException("Invalid Post ID", HttpStatus.BAD_REQUEST));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException("User not found with email", HttpStatus.BAD_REQUEST));
        if(post.getUpVotedUsers().contains(user.getId())){
            post.setUpVotes(post.getUpVotes().subtract(new BigDecimal(1)));
            post.getUpVotedUsers().remove(user.getId());
        }else{
            post.setUpVotes(post.getUpVotes().add(new BigDecimal(1)));
            post.getUpVotedUsers().add(user.getId());
            if(post.getDownVotedUsers().contains(user.getId())){
                post.setDownVotes(post.getDownVotes().subtract(new BigDecimal(1)));
                post.getDownVotedUsers().remove(user.getId());
            }
        }
        postsRepository.save(post);
    }
    public void downVotePost(String postId, String email) {
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new ApiException("Invalid Post ID", HttpStatus.BAD_REQUEST));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException("User not found with email", HttpStatus.BAD_REQUEST));
        if(post.getDownVotedUsers().contains(user.getId())){
            post.setDownVotes(post.getDownVotes().subtract(new BigDecimal(1)));
            post.getDownVotedUsers().remove(user.getId());
        }else{
            post.setDownVotes(post.getDownVotes().add(new BigDecimal(1)));
            post.getDownVotedUsers().add(user.getId());
            if(post.getUpVotedUsers().contains(user.getId())){
                post.setUpVotes(post.getUpVotes().subtract(new BigDecimal(1)));
                post.getUpVotedUsers().remove(user.getId());
            }
        }
        postsRepository.save(post);
    }
    public void upVoteReply(String replyId, String email) {
        Replies comment = repliesRepository.findById(replyId)
                .orElseThrow(() -> new ApiException("Invalid Reply ID", HttpStatus.BAD_REQUEST));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException("User not found with email", HttpStatus.BAD_REQUEST));
        if(comment.getUpVotedUsers().contains(user.getId())){
            comment.setUpVotes(comment.getUpVotes().subtract(new BigDecimal(1)));
            comment.getUpVotedUsers().remove(user.getId());
        }else{
            comment.setUpVotes(comment.getUpVotes().add(new BigDecimal(1)));
            comment.getUpVotedUsers().add(user.getId());
            if(comment.getDownVotedUsers().contains(user.getId())){
                comment.setDownVotes(comment.getDownVotes().subtract(new BigDecimal(1)));
                comment.getDownVotedUsers().remove(user.getId());
            }
        }
        repliesRepository.save(comment);
    }
    public void downVoteReply(String replyId, String email) {
        Replies comment = repliesRepository.findById(replyId)
                .orElseThrow(() -> new ApiException("Invalid Reply ID", HttpStatus.BAD_REQUEST));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException("User not found with email", HttpStatus.BAD_REQUEST));
        if(comment.getDownVotedUsers().contains(user.getId())){
            comment.setDownVotes(comment.getDownVotes().subtract(new BigDecimal(1)));
            comment.getDownVotedUsers().remove(user.getId());
        }else{
            comment.setDownVotes(comment.getDownVotes().add(new BigDecimal(1)));
            comment.getDownVotedUsers().add(user.getId());
            if(comment.getUpVotedUsers().contains(user.getId())){
                comment.setUpVotes(comment.getUpVotes().subtract(new BigDecimal(1)));
                comment.getUpVotedUsers().remove(user.getId());
            }
        }
        repliesRepository.save(comment);
    }
}
