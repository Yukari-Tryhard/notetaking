package com.cnpmm.notetaking.controller.restcontroller;

import com.cnpmm.notetaking.service.TrashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "api/v1/trash")
public class TrashController {

    @Autowired
    private TrashService trashService;

    public TrashController(TrashService trashService){
        this.trashService = trashService;
    }
}
