package com.linkinone.Lio.service;

import com.linkinone.Lio.domain.entity.BoardEntity;
import com.linkinone.Lio.domain.entity.CommentEntity;
import com.linkinone.Lio.domain.repository.CommentRepository;
import com.linkinone.Lio.dto.BoardDto;
import com.linkinone.Lio.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.tokens.CommentToken;

import javax.transaction.Transactional;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Transactional
    public List<CommentDto> getComment(Long no)
        {
            List<CommentEntity> commentEntities = commentRepository.findAllByPostid(no);
            List<CommentDto> commentDtoList = new ArrayList<>();

            for( CommentEntity commentEntity : commentEntities) {
                CommentDto commentDTO = CommentDto.builder()
                        .commentid(commentEntity.getCommentid())
                        .postid(commentEntity.getPostid())
                        .content(commentEntity.getContent())
                        .writer(commentEntity.getWriter())
                        .createdDate(commentEntity.getCreatedDate())
                        .build();

                commentDtoList.add(commentDTO);
            }

            return commentDtoList;
    }

    @Transactional
    public Long savePost(CommentDto commentDto) {
        return commentRepository.save(commentDto.toEntity()).getCommentid();
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }


}
