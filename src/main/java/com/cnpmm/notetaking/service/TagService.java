package com.cnpmm.notetaking.service;

import com.cnpmm.notetaking.model.Tag;
import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.repository.TagRepository;
import com.cnpmm.notetaking.repository.UserRepository;
import com.cnpmm.notetaking.util.ServiceResponse;
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

    public ServiceResponse AddTagByUser(Tag tag, Integer userId){
        try{
            User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User Id: "+ userId + "not found"));
            if (user != null){
                if (!(tagRepository.findTagByTagNameWithUserId(tag.getTagName(), userId) != null)){
                    tag.setUser(user);
                    Tag saveTag = tagRepository.save(tag);
                    return new ServiceResponse(200,"save tag successfully");
                }
                return new ServiceResponse(200,"tag already exits");
            }
            return new ServiceResponse(409,"user with id "+userId + " not found");
        }
        catch (Exception ex){
            return new ServiceResponse(500,ex.getMessage());
        }
    }

    public ServiceResponse deleteById(Long tagId){
        try{
            Tag tag = tagRepository.findById(tagId).orElse(null);
            if (tag != null){
                tagRepository.deleteById(tagId);
                return new ServiceResponse(200,"Tag with id " + tagId + " is deleted");
            }
            return new ServiceResponse(409,"Tag with id " + tagId + " is not exists");
        }
        catch (Exception ex){
            return new ServiceResponse(500,ex.getMessage());
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

    public ServiceResponse updateTag(Tag tag){
        try{
            Tag existTag = tagRepository.findByTagName(tag.getTagName());
            if (existTag != null){
                tagRepository.UpdateTag(tag.getTagId(),tag.getTagName());
                return new ServiceResponse(200,"updated tag has id " + tag.getTagId());
            }
            return new ServiceResponse(409,"tag has id " + tag.getTagId() + " is not exist");
        }catch (Exception ex){
            return new ServiceResponse(500,ex.getMessage());
        }
    }

    public Tag saveTag(Tag tag) {
        Tag existTag = tagRepository.findByTagName(tag.getTagName());
        if (existTag != null){
            updateTag(tag);
            return tag;
        }
        return tagRepository.save(tag);
    }
}
