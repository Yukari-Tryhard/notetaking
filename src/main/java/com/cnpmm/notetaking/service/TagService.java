package com.cnpmm.notetaking.service;

import com.cnpmm.notetaking.model.Tag;
import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.repository.TagRepository;
import com.cnpmm.notetaking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    public TagService(TagRepository tagRepository, UserRepository userRepository){
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    public Tag AddTagByUser(Tag tag, Integer userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User Id: "+ userId + "not found"));
        if (user != null){
            if (!(tagRepository.findTagByTagNameWithUserId(tag.getTagName(), userId) != null)){
                tag.setUser(user);
                Tag saveTag = tagRepository.save(tag);
                return saveTag;
            }

            return null;
        }
        return null;
    }

    public String deleteById(Long tagId){
        try{
            Tag tag = tagRepository.findById(tagId).orElse(null);
            if (tag != null){
                tagRepository.deleteById(tagId);
                return "Tag with id " + tagId + " is deleted";
            }
            return "Tag with id " + tagId + " is not exists";
        }
        catch (Exception ex){
            return ex.getMessage();
        }
    }

    public Collection<Tag>findAllTagByUser(Integer userId){
        try{
            User user = userRepository.findById(userId).orElse(null);
            if (user != null){
                return tagRepository.findAllTagByUserId(userId);
            }
            return null;
        }catch (Exception ex){
            return null;
        }
    }

    public String updateTag(Tag tag){
        try{
            Tag existTag = tagRepository.findById(tag.getTagId()).orElse(null);
            if (existTag != null){
                tagRepository.UpdateTag(tag.getTagId(),tag.getTagName());
                return "updated tag has id " + tag.getTagId();
            }
            return "tag has id " + tag.getTagId() + " is not exist";
        }catch (Exception ex){
            return ex.getMessage();
        }
    }
}
