package com.springalumni.sairam.controller;

import com.springalumni.sairam.dto.ChatDTO;
import com.springalumni.sairam.dto.DomainDTO;
import com.springalumni.sairam.dto.PostDTO;
import com.springalumni.sairam.dto.RepliesDTO;
import com.springalumni.sairam.models.Domain;
import com.springalumni.sairam.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats/")
public class ChatSpaceController {
    private final ChatService chatService;
    @PostMapping("/newDomain")
    public Domain createDomain(@RequestBody Domain newDomain) {
        return chatService.createDomain(newDomain);
    }
    @GetMapping("/allDomains")
    public Page<DomainDTO> getDomains(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "100") int pageSize
    ) {
        return chatService.getAllDomains(pageNo, pageSize);
    }
    @PostMapping("/newChat")
    public ChatDTO newChat(@RequestBody ChatDTO chatDTO){
        return chatService.createNewChat(chatDTO);
    }
    @GetMapping("/allChats")
    public List<ChatDTO> getALlChats() {
        return chatService.getAllChatSpaces();
    }
    @PostMapping("/newPost/{chatId}")
    public PostDTO newPost(@PathVariable("chatId") String chatId, @RequestBody PostDTO newPost){
        return chatService.createNewPost(newPost, chatId);
    }
    @GetMapping("/viewPost/{postId}")
    public PostDTO viewPost(@PathVariable("postId") String postId) {
        return chatService.getPostDetails(postId);
    }
    @PostMapping("/addComment/{postId}")
    public ResponseEntity<String> addComment(@PathVariable("postId") String postId, @RequestBody RepliesDTO repliesDTO) {
        chatService.addPostComment(repliesDTO, postId);
        return ResponseEntity.ok("Added comment");
    }
    @PutMapping("/upVotePost/{postId}")
    public ResponseEntity<String> upVotePost(@PathVariable("postId") String postId, @RequestParam(name = "email") String email){
        chatService.upVotePost(postId, email);
        return ResponseEntity.ok("Up-voted Post");
    }
    @PutMapping("/downVotePost/{postId}")
    public ResponseEntity<String> downVotePost(@PathVariable("postId") String postId, @RequestParam(name = "email") String email){
        chatService.downVotePost(postId, email);
        return ResponseEntity.ok("Down-voted Post");
    }
    @PutMapping("/upVoteReply/{replyId}")
    public ResponseEntity<String> upVoteReply(@PathVariable("replyId") String replyId, @RequestParam(name = "email") String email){
        chatService.upVoteReply(replyId, email);
        return ResponseEntity.ok("Up-voted Reply");
    }
    @PutMapping("/downVoteReply/{replyId}")
    public ResponseEntity<String> downVoteReply(@PathVariable("replyId") String replyId, @RequestParam(name = "email") String email){
        chatService.downVoteReply(replyId, email);
        return ResponseEntity.ok("Down-voted Reply");
    }
}
