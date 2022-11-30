package com.cnpmm.notetaking.controller.restcontroller;

import com.cnpmm.notetaking.dto.tag.TagAddDTO;
import com.cnpmm.notetaking.dto.tag.TagDeleteDTO;
import com.cnpmm.notetaking.dto.tag.TagUpdateDTO;
import com.cnpmm.notetaking.model.Tag;
import com.cnpmm.notetaking.service.TagService;
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
    public ResponseEntity<Tag> addNewTag(@RequestBody TagAddDTO tagAddDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
        return ResponseEntity.created(uri).body(tagService.AddTagByUser(new Tag(tagAddDTO.getTagName()), tagAddDTO.getUserId()));
    }

    @DeleteMapping(path = "/delete")
    public  ResponseEntity<String> deleteTag(@RequestBody TagDeleteDTO tagDeleteDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/delete").toUriString());
        return ResponseEntity.created(uri).body(tagService.deleteById(tagDeleteDTO.getTagId()));
    }

    @GetMapping(path = "/get-all")
    public  ResponseEntity<Collection<Tag>> getAllTag(@RequestParam Integer userId){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get").toUriString());
        return ResponseEntity.created(uri).body(tagService.findAllTagByUser(userId));
    }

    @PutMapping(path = "/update")
    public  ResponseEntity<String> updateTag(@RequestBody TagUpdateDTO tagUpdateDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get").toUriString());
        Tag updateTag = new Tag(tagUpdateDTO.getTagName());
        updateTag.setTagId(tagUpdateDTO.getTagId());
        return ResponseEntity.created(uri).body(tagService.updateTag(updateTag));
    }

}
