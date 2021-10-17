package com.linkinone.Lio.service;

import com.linkinone.Lio.domain.entity.CommentEntity;
import com.linkinone.Lio.domain.repository.CommentRepository;
import com.linkinone.Lio.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Transactional
    public CommentDto getComment(Long post_id) {
        {
            Optional<CommentEntity> commentEntityWrapper = commentRepository.findById(post_id);
            CommentEntity commentEntity = commentEntityWrapper.get();

            CommentDto commentDTO = CommentDto.builder()
                    .comment_id(commentEntity.getComment_id())
                    .post_id(commentEntity.getPost_id())
                    .content(commentEntity.getContent())
                    .writer(commentEntity.getWriter())
                    .createdDate(commentEntity.getCreatedDate())
                    .build();

            return commentDTO;
        }
    }

    @Transactional
    public Long savePost(CommentDto commentDto) {
        return commentRepository.save(commentDto.toEntity()).getComment_id();
    }



}
