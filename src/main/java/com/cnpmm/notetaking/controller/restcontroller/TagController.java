package com.cnpmm.notetaking.controller.restcontroller;

import com.cnpmm.notetaking.dto.tag.TagAddDTO;
import com.cnpmm.notetaking.dto.tag.TagDeleteDTO;
import com.cnpmm.notetaking.dto.tag.TagUpdateDTO;
import com.cnpmm.notetaking.model.Tag;
import com.cnpmm.notetaking.service.TagService;
import com.cnpmm.notetaking.util.GenericResponse;
import com.cnpmm.notetaking.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController()
@RequestMapping(path = "api/v1/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<GenericResponse<Tag>> addNewTag(@RequestBody TagAddDTO tagAddDTO){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
            Tag tag = new Tag(tagAddDTO.getTagName());
            ServiceResponse tagServiceResponse = tagService.AddTagByUser(tag, tagAddDTO.getUserId());
            return ResponseEntity.created(uri).body(new GenericResponse<Tag>(tagServiceResponse,tag));
        }catch (Exception ex){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<>(ex.getMessage(), 500,null));
        }
    }

    @DeleteMapping(path = "/delete")
    public  ResponseEntity<GenericResponse<Tag>> deleteTag(@RequestBody TagDeleteDTO tagDeleteDTO){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/delete").toUriString());
            ServiceResponse tagServiceResponse =  tagService.deleteById(tagDeleteDTO.getTagId());
            return ResponseEntity.created(uri).body(new GenericResponse<Tag>(tagServiceResponse,null));
        }catch (Exception ex){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/delete").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<>(ex.getMessage(), 500,null));
        }
    }

    @GetMapping(path = "/get-all")
    public  ResponseEntity<GenericResponse<Collection<Tag>>> getAllTag(@RequestParam Integer userId){
        try {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get-all").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<>("get all succesfully", 200,tagService.findAllTagByUser(userId)));

        }catch (Exception ex){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get-all").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<>(ex.getMessage(), 500,null));
        }
    }

    @PutMapping(path = "/update")
    public  ResponseEntity<GenericResponse<Tag>> updateTag(@RequestBody TagUpdateDTO tagUpdateDTO){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
            Tag updateTag = new Tag(tagUpdateDTO.getTagName());
            updateTag.setTagId(tagUpdateDTO.getTagId());
            ServiceResponse tagServiceResponse =  tagService.updateTag(updateTag);
            return ResponseEntity.created(uri).body(new GenericResponse<Tag>(tagServiceResponse,updateTag));
        }
        catch (Exception ex){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<>(ex.getMessage(), 500,null));
        }
    }

}
